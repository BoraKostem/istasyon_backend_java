package com.istasyon.backend.controllers.employee;

import com.istasyon.backend.dataObjects.EmployeeSkillDTO;
import com.istasyon.backend.entities.HasSkills;
import com.istasyon.backend.entities.Skills;
import com.istasyon.backend.entities.User;
import com.istasyon.backend.repositories.EmployeeRepo;
import com.istasyon.backend.repositories.HasSkillsRepo;
import com.istasyon.backend.repositories.SkillsRepo;
import com.istasyon.backend.repositories.compositeIds.HasSkillsId;
import com.istasyon.backend.utilities.CustomJson;
import com.istasyon.backend.utilities.JsonCreator;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user/skill")
@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
public class SkillController {
    private final JsonCreator jsonCreator;
    private final HasSkillsRepo hasSkillsRepo;
    private final SkillsRepo skillsRepo;
    private final EmployeeRepo employeeRepo;
    public SkillController(JsonCreator jsonCreator, HasSkillsRepo hasSkillsRepo, SkillsRepo skillsRepo, EmployeeRepo employeeRepo) {
        this.jsonCreator = jsonCreator;
        this.hasSkillsRepo = hasSkillsRepo;
        this.skillsRepo = skillsRepo;
        this.employeeRepo = employeeRepo;
    }

    @PostMapping("/add")
    public ResponseEntity<CustomJson<Object>> addSkill(@RequestBody EmployeeSkillDTO employeeSkillDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        Map<String, Object> resultMap = new HashMap<>();
        Map<Integer,String> skills = new HashMap<>();
        employeeSkillDTO.getSkills().forEach(skillId -> skills.put(skillId,addSkilltoEmployee(skillId, currentUser)));
        resultMap.put("Skills",skills);
        return jsonCreator.create(resultMap, 200);
    }

    private String addSkilltoEmployee(Integer skillId, User currentUser) {
        Skills skill = skillsRepo.findBySkillId(skillId);
        if (skill == null) {
            return "Skill with ID " + skillId + " does not exist";
        }
        HasSkillsId hasSkillsId = new HasSkillsId(skillId, currentUser.getUserId());
        if (hasSkillsRepo.findById(hasSkillsId).isPresent()) {
            return "Employee Already has this skill: " + skill.getSkillName();
        }
        try {
            HasSkills hasSkills = new HasSkills();
            hasSkills.setSkill(skill);
            hasSkills.setEmployee(employeeRepo.findByeUserNo(currentUser.getUserId()));
            hasSkillsRepo.save(hasSkills);
        } catch (Exception e) {
            return "Skill with ID " + skillId + " could not be added: " + e;
        }
        return "Success";
    }
}
