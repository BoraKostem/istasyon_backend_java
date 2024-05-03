package com.istasyon.backend.controllers.company;

import com.istasyon.backend.dataObjects.CommentDTO;
import com.istasyon.backend.entities.EmployeePrevJobs;
import com.istasyon.backend.entities.User;
import com.istasyon.backend.repositories.EmployeePrevJobsRepo;
import com.istasyon.backend.utilities.CustomJson;
import com.istasyon.backend.utilities.JsonCreator;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasRole('ROLE_COMPANY')")
@RequestMapping("/comment")
public class CompanyComment {
    private final JsonCreator jsonCreator;
    private final EmployeePrevJobsRepo employeePrevJobsRepo;
    public CompanyComment(JsonCreator jsonCreator, EmployeePrevJobsRepo employeePrevJobsRepo) {
        this.jsonCreator = jsonCreator;
        this.employeePrevJobsRepo = employeePrevJobsRepo;
    }

    @PostMapping("/add")
    public ResponseEntity<CustomJson<Object>>  addComment(@RequestBody CommentDTO commentDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        EmployeePrevJobs job = employeePrevJobsRepo.findByid(commentDTO.getId());
        if(job == null || !job.getCompanyId().equals(currentUser.getUserId()))
            return jsonCreator.create("Job not found", 404);
        job.setEmployerComment(commentDTO.getEmployerComment());
        job.setEfficiency(commentDTO.getEfficiency());
        job.setCommunication(commentDTO.getCommunication());
        job.setTeam_work(commentDTO.getTeam_work());
        job.setResponsibility(commentDTO.getResponsibility());
        job.setPersonal_growth(commentDTO.getPersonal_growth());
        try {
            employeePrevJobsRepo.save(job);
            return jsonCreator.create(job);
        } catch (Exception e) {
            return jsonCreator.create("Something unexpected happened", 500);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<CustomJson<Object>> getComments(@RequestParam(required = false) Boolean all){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        if(all != null && all)
            return jsonCreator.create(employeePrevJobsRepo.findBycompanyId(currentUser.getUserId()));
        return jsonCreator.create(employeePrevJobsRepo.findBycompanyIdAndEmployerCommentIsNull(currentUser.getUserId()));
    }
}
