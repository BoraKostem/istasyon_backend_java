package com.istasyon.backend.controllers.company;

import com.istasyon.backend.dataObjects.EmployeeSkillDTO;
import com.istasyon.backend.dataObjects.JobAddDTO;
import com.istasyon.backend.dataObjects.JobSkillDTO;
import com.istasyon.backend.entities.*;
import com.istasyon.backend.repositories.CompanyRepo;
import com.istasyon.backend.repositories.JobAddRepo;
import com.istasyon.backend.repositories.RequiresSkillsRepo;
import com.istasyon.backend.repositories.SkillsRepo;
import com.istasyon.backend.repositories.compositeIds.HasSkillsId;
import com.istasyon.backend.repositories.compositeIds.RequiresSkillsId;
import com.istasyon.backend.utilities.CustomJson;
import com.istasyon.backend.utilities.JsonCreator;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@PreAuthorize("hasRole('ROLE_COMPANY')")
@RequestMapping("/company/jobAdd")
public class JobAddsController {
    private final JobAddRepo jobAddRepo;
    private final JsonCreator jsonCreator;
    private final CompanyRepo companyRepo;
    private final SkillsRepo skillsRepo;
    private final RequiresSkillsRepo requiresSkillsRepo;
    public JobAddsController(JobAddRepo jobAddRepo, JsonCreator jsonCreator, CompanyRepo companyRepo, SkillsRepo skillsRepo, RequiresSkillsRepo requiresSkillsRepo) {
        this.jobAddRepo = jobAddRepo;
        this.jsonCreator = jsonCreator;
        this.companyRepo = companyRepo;
        this.skillsRepo = skillsRepo;
        this.requiresSkillsRepo = requiresSkillsRepo;
    }

    /**
     * This method is responsible for creating a new job advertisement.
     * It first retrieves the current authenticated user and finds the associated company.
     * Then, it creates a new job advertisement using the provided DTO and the company.
     * If the creation is successful, it saves the job advertisement to the repository.
     * If an exception occurs during the creation, it returns a response with a 500 status code and a failure message.
     * If the creation is successful, it returns a response with a 200 status code and a success message.
     *
     * @param jobAddDTO The data transfer object containing the details of the job advertisement to be created.
     * @return A ResponseEntity containing a CustomJson object. The object contains a message indicating the result of the operation and, in case of success, the created job advertisement.
     * @throws Exception If an error occurs during the creation of the job advertisement.
     */
    @PostMapping("/create")
    public ResponseEntity<CustomJson<Object>> createJobAdd(@RequestBody JobAddDTO jobAddDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        Company company = companyRepo.findBycUserNo(currentUser.getUserId());
        try {
            CompPostsAds jobAdd = getJobAdd(jobAddDTO, company);
            jobAddRepo.save(jobAdd);
        } catch (Exception e) {
            return jsonCreator.create(new HashMap<String,Object>(){{
                put("Message","Job Add Creation Failed: " + e);
            }}, 500);
        }
        return jsonCreator.create(new HashMap<String,Object>(){{
            put("Message","Job Add Created Successfully");
            put("Item",jobAddDTO);
        }}, 200);
    }

    /**
     * This method is responsible for creating a new job advertisement from a JobAddDTO and a Company.
     * It creates a new job advertisement and sets its properties using the values from the DTO.
     * It also sets the company of the job advertisement.
     *
     * @param jobAddDTO The data transfer object containing the details of the job advertisement to be created.
     * @param company The company associated with the job advertisement.
     * @return The created job advertisement.
     */
    private CompPostsAds getJobAdd(JobAddDTO jobAddDTO, Company company) {
        CompPostsAds jobAdd = new CompPostsAds();
        jobAdd.setJobName(jobAddDTO.getJobName());
        jobAdd.setJobType(jobAddDTO.getJobType());
        jobAdd.setPublishDate(jobAddDTO.getPublishDate());
        jobAdd.setEstimatedSalary(jobAddDTO.getEstimatedSalary());
        jobAdd.setCurrency(jobAddDTO.getCurrency());
        jobAdd.setWorkDays(jobAddDTO.getWorkDays());
        jobAdd.setWorkHours(jobAddDTO.getWorkHours());
        jobAdd.setBonus(jobAddDTO.getBonus());
        jobAdd.setEduMin(jobAddDTO.getEduMin());
        jobAdd.setExpMin(jobAddDTO.getExpMin());
        jobAdd.setFood(jobAddDTO.getFood());
        jobAdd.setHealthInsurance(jobAddDTO.getHealthInsurance());
        jobAdd.setInsurance(jobAddDTO.getInsurance());
        jobAdd.setTransportation(jobAddDTO.getTransportation());
        jobAdd.setGender(jobAddDTO.getGender());
        jobAdd.setStatus(jobAddDTO.getStatus());
        jobAdd.setCompany(company);
        return jobAdd;
    }

    /**
     * This method is responsible for adding a skill to a job advertisement.
     * It first retrieves the job advertisement using the provided adId.
     * Then, it adds the skill to the job advertisement using the provided DTO.
     * If the skill is already associated with the job advertisement, it returns a response with a 200 status code and a message indicating that the skill is already associated with the job advertisement.
     * If the skill is not associated with the job advertisement, it creates a new RequiresSkills object and saves it to the repository.
     * If an exception occurs during the creation, it returns a response with a 500 status code and a failure message.
     * If the creation is successful, it returns a response with a 200 status code and a success message.
     *
     * @param jobSkillDTO The data transfer object containing the details of the skill to be added to the job advertisement.
     * @return A ResponseEntity containing a CustomJson object. The object contains a message indicating the result of the operation and, in case of success, the added skill.
     */
    @PostMapping("/addSkill")
    public ResponseEntity<CustomJson<Object>> addSkill(@RequestBody JobSkillDTO jobSkillDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        CompPostsAds currentAdd = jobAddRepo.findByadIdAndCompany_cUserNo(jobSkillDTO.getAdId(), currentUser.getUserId());
        if (currentAdd == null) {
            return jsonCreator.create(new HashMap<String,Object>(){{
                put("Message","Job Add with ID " + jobSkillDTO.getAdId() + " does not exist");
            }}, 404);
        }
        Map<String, Object> resultMap = new HashMap<>();
        Map<Integer,String> successSkills = new HashMap<>();
        Map<Integer,String> failSkills = new HashMap<>();
        Map<Integer,String> alreadyHaveSkills = new HashMap<>();
        for(Integer skillId : jobSkillDTO.getSkills()) {
            Map<String, String> result = addSkilltoAdd(skillId, currentAdd);
            if (result.get("status").equals("Success")) {
                successSkills.put(skillId, result.get("skillName"));
            } else if (result.get("status").equals("Fail")) {
                failSkills.put(skillId, result.get("skillName"));
            } else if (result.get("status").equals("AlreadyHave")) {
                alreadyHaveSkills.put(skillId, result.get("skillName"));
            }
        }
        resultMap.put("Success", successSkills);
        resultMap.put("Fail", failSkills);
        resultMap.put("AlreadyHave", alreadyHaveSkills);
        return jsonCreator.create(resultMap, 200);
    }

    private Map<String, String> addSkilltoAdd(Integer skillId, CompPostsAds currentAdd) {
        Map<String, String> result = new HashMap<>();
        Skills skill = skillsRepo.findBySkillId(skillId);
        if (skill == null) {
            result.put("status", "Fail");
            result.put("skillName", "NULL");
            return result;
        }
        RequiresSkillsId requiresSkillsId = new RequiresSkillsId(skillId, currentAdd.getAdId());
        if (requiresSkillsRepo.findById(requiresSkillsId).isPresent()) {
            result.put("status", "AlreadyHave");
            result.put("skillName", skill.getSkillName());
            return result;
        }
        try {
            RequiresSkills reqSkills = new RequiresSkills();
            reqSkills.setSkill(skill);
            reqSkills.setCompPostsAds(currentAdd);
            requiresSkillsRepo.save(reqSkills);
            result.put("status", "Success");
            result.put("skillName", skill.getSkillName());
        } catch (Exception e) {
            result.put("status", "Fail");
            result.put("skillName", skill.getSkillName());
        }
        return result;
    }

    /**
     * This method is responsible for deleting a skill from a job advertisement.
     * It first retrieves the job advertisement using the adId from the provided DTO.
     * If the job advertisement does not exist, it returns a response with a 404 status code and a failure message.
     * Then, it iterates over the skills in the DTO and attempts to delete each one from the job advertisement.
     * The result of each deletion operation is stored in a map with the skillId as the key and the result message as the value.
     * Finally, it returns a response with a 200 status code and the map of results.
     *
     * @param jobSkillDTO The data transfer object containing the adId of the job advertisement and the ids of the skills to be deleted.
     * @return A ResponseEntity containing a CustomJson object. The object contains a map of the results of the deletion operations for each skill.
     */
    @PostMapping("/deleteSkill")
    public ResponseEntity<CustomJson<Object>> deleteSkill(@RequestBody JobSkillDTO jobSkillDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        CompPostsAds currentAdd = jobAddRepo.findByadIdAndCompany_cUserNo(jobSkillDTO.getAdId(), currentUser.getUserId());
        if (currentAdd == null) {
            return jsonCreator.create(new HashMap<String,Object>(){{
                put("Message","Job Add with ID " + jobSkillDTO.getAdId() + " does not exist");
            }}, 404);
        }
        Map<String, Object> resultMap = new HashMap<>();
        Map<Integer,String> skills = new HashMap<>();
        jobSkillDTO.getSkills().forEach(skillId -> skills.put(skillId,deleteSkillfromAdd(skillId, currentAdd)));
        resultMap.put("Skills",skills);
        return jsonCreator.create(resultMap, 200);
    }

    private String deleteSkillfromAdd(Integer skillId, CompPostsAds currentAdd) {
        RequiresSkillsId requiresSkillsId = new RequiresSkillsId(skillId, currentAdd.getAdId());
        if (requiresSkillsRepo.findById(requiresSkillsId).isEmpty()) {
            return "Job Add does not have this skill with ID: " + skillId;
        }
        try {
            requiresSkillsRepo.deleteById(requiresSkillsId);
        } catch (Exception e) {
            return "Skill with ID " + skillId + " could not be deleted: " + e;
        }
        return "Success";
    }

    @GetMapping("/view")
    public ResponseEntity<CustomJson<Object>> viewJobAdd(@RequestParam(required = false) Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        if(id != null){
            CompPostsAds jobAdd = jobAddRepo.findByadIdAndCompany_cUserNo(id, currentUser.getUserId());
            if(jobAdd == null) {
                return jsonCreator.create(new HashMap<String,Object>(){{
                    put("Message","Job Add with ID " + id + " does not exist");
                }}, 404);
            }
            return jsonCreator.create(new HashMap<String,Object>(){{
                put("JobAdd",jobAdd);
            }}, 200);
        }
        List<CompPostsAds> jobAdds = jobAddRepo.findByCompany_cUserNo(currentUser.getUserId());
        return jsonCreator.create(new HashMap<String,Object>(){{
            put("JobAdd",jobAdds);
        }}, 200);
    }
}
