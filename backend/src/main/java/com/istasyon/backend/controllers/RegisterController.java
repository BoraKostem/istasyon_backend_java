package com.istasyon.backend.controllers;

import com.istasyon.backend.dataObjects.CompanyRegisterDTO;
import com.istasyon.backend.dataObjects.RegisterDTO;
import com.istasyon.backend.dataObjects.UserDTO;
import com.istasyon.backend.entities.*;
import com.istasyon.backend.repositories.*;
import com.istasyon.backend.services.AWSS3Service;
import com.istasyon.backend.utilities.CustomJson;
import com.istasyon.backend.utilities.JsonCreator;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class RegisterController {
    private final UserRepo userRepository;
    private final EmployeeRepo employeeRepository;
    private final EmployeeProfileRepo employeeProfileRepo;
    private final CompanyRepo companyRepository;
    private final CompanyProfileRepo companyProfileRepo;
    private final AWSS3Service awss3Service;
    private final JsonCreator jsonCreator;
    private final BCryptPasswordEncoder passwordEncoder;
    public RegisterController(UserRepo userRepository, EmployeeRepo employeeRepository, EmployeeProfileRepo employeeProfileRepo, CompanyRepo companyRepository, JsonCreator jsonCreator, BCryptPasswordEncoder passwordEncoder, AWSS3Service awss3Service, CompanyProfileRepo companyProfileRepo) {
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
        this.jsonCreator = jsonCreator;
        this.passwordEncoder = passwordEncoder;
        this.employeeProfileRepo = employeeProfileRepo;
        this.awss3Service = awss3Service;
        this.companyProfileRepo = companyProfileRepo;
    }

    @PostMapping("/user/register")
    public ResponseEntity<CustomJson<Object>> register(@RequestBody RegisterDTO registerDTO) {
        try {
            User user = new User();
            user.setName(registerDTO.getName());
            user.setSurname(registerDTO.getSurname());
            user.setEmail(registerDTO.getEmail());
            user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

            user = userRepository.saveAndFlush(user);
            Employee employee = getEmployee(registerDTO, user);
            try {
                employeeRepository.saveAndFlush(employee);
                EmployeeProfile empProfile = new EmployeeProfile();
                empProfile.setEmployee(employee);
                employeeProfileRepo.save(empProfile);
            } catch (Exception e) {
                userRepository.delete(user);
                return jsonCreator.create("Error during registration: " + e, 500);
            }
            return jsonCreator.create(new HashMap<String,Object>() {{
                put("Message","Employee Created Successfully");
                put("Item",employee);
            }});
        } catch (Exception e) {
            return jsonCreator.create("Error during registration: " + e, 500);
        }
    }

    private Employee getEmployee(RegisterDTO employeeDTO, User user) {
        Employee employee = new Employee();
        employee.setUser(user);
        // Set fields from DTO to Employee entity
        employee.setPhoneNo(employeeDTO.getPhoneNo());
        employee.setAddress(employeeDTO.getAddress());
        employee.setxCoor(employeeDTO.getxCoor());
        employee.setyCoor(employeeDTO.getyCoor());
        employee.setConfirmationCode(employeeDTO.getConfirmationCode());
        employee.setConfirmationTime(employeeDTO.getConfirmationTime());
        employee.setGender(employeeDTO.getGender());
        employee.setBirthDate(employeeDTO.getBirthDate());
        employee.setMilitaryServiceInfo(employeeDTO.getMilitaryServiceInfo());
        employee.setDriversLicence(employeeDTO.getDriversLicence());
        employee.setEduLvl(employeeDTO.getEduLvl());
        employee.setSalaryExpectation(employeeDTO.getSalaryExpectation());
        employee.setSkills(employeeDTO.getSkills());
        employee.setJobName(employeeDTO.getJobName());
        return employee;
    }


    //Company Register
    @PostMapping("/company/register")
    public ResponseEntity<CustomJson<Object>> register(@RequestBody CompanyRegisterDTO companyDTO) {
        try {
            User user = new User();
            user.setName(companyDTO.getName());
            user.setSurname(companyDTO.getSurname());
            user.setEmail(companyDTO.getEmail());
            user.setPassword(passwordEncoder.encode(companyDTO.getPassword()));

            user = userRepository.saveAndFlush(user);

            Company company = getCompany(companyDTO, user);
            try {
                companyRepository.saveAndFlush(company);
                CompanyProfile companyProfile = new CompanyProfile();
                companyProfile.setCompany(company);
                companyProfile.setS3BucketUrl(awss3Service.createFolder(company.getcUserNo()+""));
                companyProfileRepo.save(companyProfile);
            } catch (Exception e) {
                userRepository.delete(user);
                return jsonCreator.create(new HashMap<String, String>() {{
                    put("Error", "500");
                    put("Message", String.valueOf(e));
                }}, 500);
            }
            return jsonCreator.create(new HashMap<String,Object>() {{
                put("Message","Company Created Successfully");
                put("Item",company);
            }});
        } catch (Exception e) {
            return jsonCreator.create(new HashMap<String, String>() {{
                put("Error", "500");
                put("Message", String.valueOf(e));
            }}, 500);
        }
    }

    private Company getCompany(CompanyRegisterDTO companyDTO, User user) {
        Company company = new Company();
        company.setUser(user);
        // Set fields from DTO to Company entity
        company.setTaxNo(companyDTO.getTaxNo());
        company.setCompanyName(companyDTO.getCompanyName());
        company.setPhoneNo(companyDTO.getPhoneNo());
        company.setAddress(companyDTO.getAddress());
        company.setxCoor(companyDTO.getxCoor());
        company.setSector(companyDTO.getSector());
        company.setyCoor(companyDTO.getyCoor());
        company.setConfirmationCode(companyDTO.getConfirmationCode());
        company.setConfirmationTime(companyDTO.getConfirmationTime());
        return company;
    }
}
