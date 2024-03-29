package com.istasyon.backend.controllers.employee;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.istasyon.backend.dataObjects.EmployeeJobDTO;
import com.istasyon.backend.entities.Company;
import com.istasyon.backend.entities.Employee;
import com.istasyon.backend.entities.EmployeePrevJobs;
import com.istasyon.backend.entities.User;
import com.istasyon.backend.repositories.CompanyRepo;
import com.istasyon.backend.repositories.EmployeePrevJobsRepo;
import com.istasyon.backend.repositories.EmployeeRepo;
import com.istasyon.backend.services.EmailService;
import com.istasyon.backend.utilities.CustomJson;
import com.istasyon.backend.utilities.JsonCreator;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/user/employment")
@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
public class EmploymentController {
    private final JsonCreator jsonCreator;
    private final EmployeeRepo employeeRepo;
    private final CompanyRepo companyRepo;
    private final EmployeePrevJobsRepo employeePrevJobsRepo;
    private final EmailService emailService;
    public EmploymentController(JsonCreator jsonCreator, EmployeeRepo employeeRepo, CompanyRepo companyRepo, EmployeePrevJobsRepo employeePrevJobsRepo, EmailService emailService) {
        this.jsonCreator = jsonCreator;
        this.employeeRepo = employeeRepo;
        this.companyRepo = companyRepo;
        this.employeePrevJobsRepo = employeePrevJobsRepo;
        this.emailService = emailService;
    }

    /**
     * Endpoint to add a previous employment record for the authenticated user.
     *
     * @param employeeJobDTO A data transfer object containing the details of the previous employment record to be added.
     * @return A ResponseEntity containing a CustomJson object. The body of the CustomJson object contains the added previous employment record if successful, or an error message if not.
     *
     * The method works as follows:
     * 1. It retrieves the authentication object from the security context holder.
     * 2. It retrieves the authenticated user from the authentication object.
     * 3. It attempts to find a company and an employee record associated with the authenticated user.
     * 4. If no company is found, it returns a 404 error with a message "Company not found".
     * 5. It creates a new EmployeePrevJobs object and sets its fields based on the provided DTO and the found company and employee.
     * 6. It saves the EmployeePrevJobs object.
     * 7. If an error occurs during the process, it returns a 500 error with a message "Error occurred: " followed by the error message.
     * 8. If successful, it returns a 200 status with the EmployeePrevJobs object.
     */
    @PostMapping("previous/add")
    public ResponseEntity<CustomJson<Object>> addPrevEmployment(@RequestBody EmployeeJobDTO employeeJobDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        Employee employee = employeeRepo.findByeUserNo(currentUser.getUserId());
        Company company = companyRepo.findBycUserNo(employeeJobDTO.getCompanyId());
        EmployeePrevJobs employeePrevJobs = new EmployeePrevJobs();
        if(company == null){
            return jsonCreator.create("Company not found",404);
        }
        try {
            employeePrevJobs.setCompany(company);
            employeePrevJobs.setEmployee(employee);
            employeePrevJobs.setCompanyId(company.getcUserNo());
            employeePrevJobs.setEmployeeId(employee.getEUserNo());
            employeePrevJobs.setCompanyName(employeeJobDTO.getCompanyName());
            employeePrevJobs.setStartDate(employeeJobDTO.getStartDate());
            employeePrevJobs.setEndDate(employeeJobDTO.getEndDate());
            employeePrevJobs.setJobType(employeeJobDTO.getJobType());
            employeePrevJobsRepo.save(employeePrevJobs);
        }catch (Exception e) {
            return jsonCreator.create("Error occurred: " + e, 500);
        }
        if(company.getcUserNo() != -1){
            //sendCompanyNotification(company.getUser().getEmail(), "İstasyon: Eski çalışanın için bir yorum yaz.", employee.getUser().getName() + " " + employee.getUser().getSurname() + " eski çalışma hayatına şirketinizi ekledi. \nÇalışanını değerlendirmek için aşağıdaki linke tıklayarak yorum yazabilirsin!", employee.getEUserNo());
        }
        return jsonCreator.create(employeePrevJobs,200);
    }

    /**
     * Endpoint to add an employment record for the authenticated user.
     *
     * @param employeeJobDTO A data transfer object containing the details of the employment record to be added.
     * @return A ResponseEntity containing a CustomJson object. The body of the CustomJson object contains the added employment record if successful, or an error message if not.
     *
     * The method works as follows:
     * 1. It retrieves the authentication object from the security context holder.
     * 2. It retrieves the authenticated user from the authentication object.
     * 3. It attempts to find a company and an employee record associated with the authenticated user.
     * 4. If no company is found, it returns a 404 error with a message "Company Does Not Exist".
     * 5. If the companyId in the provided DTO is -1, it checks if a companyName is provided in the DTO.
     * 6. If no companyName is provided, it returns a 400 error with a message "Company Name is required".
     * 7. It sets the company and companyName of the employee record based on the provided DTO.
     * 8. If the companyId in the provided DTO is not -1, it sets the company and companyName of the employee record based on the found company.
     * 9. It sets the startDate of the employee record based on the provided DTO.
     * 10. It saves the updated employee record.
     * 11. It returns a 200 status with the updated employee record.
     */
    @PostMapping("/add")
    public ResponseEntity<CustomJson<Object>> addEmployment(@RequestBody EmployeeJobDTO employeeJobDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        Company company = companyRepo.findBycUserNo(employeeJobDTO.getCompanyId());
        Employee employee = employeeRepo.findByeUserNo(currentUser.getUserId());
        if (company == null) {
            return jsonCreator.create("Company Does Not Exist", 404);
        }
        if(employeeJobDTO.getCompanyId() == -1){
            if(employeeJobDTO.getCompanyName() == null){
                return jsonCreator.create("Company Name is required", 400);
            }
            employee.setCompany(company);
            employee.setCompanyName(employeeJobDTO.getCompanyName());
        }
        else{
            employee.setCompany(company);
            employee.setCompanyName(company.getCompanyName());
        }
        employee.setJobType(employeeJobDTO.getJobType());
        employee.setStartDate(employeeJobDTO.getStartDate());
        employeeRepo.save(employee);
        return jsonCreator.create(employee, 200);
    }


    /**
     * Endpoint to retire an employee.
     *
     * @param retirementDate The date of retirement. If not provided, the current date is used.
     * @return A ResponseEntity containing a CustomJson object. The body of the CustomJson object contains the added previous employment record if successful, or an error message if not.
     *
     * The method works as follows:
     * 1. It retrieves the authentication object from the security context holder.
     * 2. It retrieves the authenticated user from the authentication object.
     * 3. It attempts to find an employee record associated with the authenticated user.
     * 4. It retrieves the company associated with the employee.
     * 5. If no company is found, it returns a 404 error with a message "Employee is not working".
     * 6. If no retirementDate is provided, it sets the retirementDate to the current date.
     * 7. It creates a new EmployeePrevJobs object and sets its fields based on the employee's current job.
     * 8. It sets the endDate of the EmployeePrevJobs object to the retirementDate.
     * 9. It saves the EmployeePrevJobs object.
     * 10. It sets the company, companyName, startDate, and jobType of the employee record to null.
     * 11. It saves the updated employee record.
     * 12. It returns a 200 status with the EmployeePrevJobs object.
     */
    @PostMapping("/retire")
    public ResponseEntity<CustomJson<Object>> retire(@RequestBody(required = false) @JsonFormat(pattern = "yyyy-MM-dd") LocalDate retirementDate) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        Employee employee = employeeRepo.findByeUserNo(currentUser.getUserId());
        Company company = employee.getCompany();
        EmployeePrevJobs employeePrevJobs = new EmployeePrevJobs();
        if(company == null){
            return jsonCreator.create("Employee is not working",404);
        }
        if (retirementDate == null){
            retirementDate = LocalDate.now();
        }
        try {
            employeePrevJobs.setCompany(company);
            employeePrevJobs.setEmployee(employee);
            employeePrevJobs.setCompanyId(company.getcUserNo());
            employeePrevJobs.setEmployeeId(employee.getEUserNo());
            employeePrevJobs.setCompanyName(employee.getCompanyName());
            employeePrevJobs.setStartDate(employee.getStartDate());
            employeePrevJobs.setEndDate(retirementDate);
            employeePrevJobs.setJobType(employee.getJobType());
            employeePrevJobsRepo.save(employeePrevJobs);
        }catch (Exception e) {
            return jsonCreator.create("Error occurred: " + e, 500);
        }
        employee.setCompany(null);
        employee.setCompanyName(null);
        employee.setStartDate(null);
        employee.setJobType(null);
        employeeRepo.save(employee);
        if(company.getcUserNo() != -1){
            //sendCompanyNotification(company.getUser().getEmail(), "İstasyon: Eski çalışanın için bir yorum yaz.", "" + employee.getUser().getName() + " " + employee.getUser().getSurname() + " eski çalışma hayatına şirketinizi ekledi. \nÇalışanını değerlendirmek için aşağıdaki linke tıklayarak yorum yazabilirsin!", employee.getEUserNo());
        }
        return jsonCreator.create(employeePrevJobs,200);
    }

    @DeleteMapping("/previous/delete/{id}")
    public ResponseEntity<CustomJson<Object>> deletePrevEmployment(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        Employee employee = employeeRepo.findByeUserNo(currentUser.getUserId());
        EmployeePrevJobs employeePrevJobs = employeePrevJobsRepo.findByid(id);
        if(employeePrevJobs == null){
            return jsonCreator.create("Employment record not found", 404);
        }
        if(employeePrevJobs.getEmployee().getEUserNo() != employee.getEUserNo()){
            return jsonCreator.create("You are not authorized to delete this record", 403);
        }
        try {
            employeePrevJobsRepo.delete(employeePrevJobs);
        }catch (Exception e) {
            return jsonCreator.create("Error occurred: " + e, 500);
        }
        return jsonCreator.create(employeePrevJobs,200);
    }

    private void sendCompanyNotification(String companyMail, String subject, String text, Long employeeId) {
        emailService.sendCommentMessage(companyMail, subject, text, employeeId);
    }

}
