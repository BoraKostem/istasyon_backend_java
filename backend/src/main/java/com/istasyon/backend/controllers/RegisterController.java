package com.istasyon.backend.controllers;

import com.istasyon.backend.dataObjects.CompanyDTO;
import com.istasyon.backend.dataObjects.EmployeeDTO;
import com.istasyon.backend.dataObjects.UserDTO;
import com.istasyon.backend.entities.Company;
import com.istasyon.backend.entities.Employee;
import com.istasyon.backend.entities.User;
import com.istasyon.backend.repositories.CompanyRepo;
import com.istasyon.backend.repositories.EmployeeRepo;
import com.istasyon.backend.repositories.UserRepo;
import com.istasyon.backend.utilities.CustomJson;
import com.istasyon.backend.utilities.JsonCreator;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class RegisterController {
    private final UserRepo userRepository;
    private final EmployeeRepo employeeRepository;
    private final CompanyRepo companyRepository;
    private final JsonCreator jsonCreator;
    private final BCryptPasswordEncoder passwordEncoder;
    public RegisterController(UserRepo userRepository, EmployeeRepo employeeRepository, CompanyRepo companyRepository, JsonCreator jsonCreator, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
        this.jsonCreator = jsonCreator;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/user/register")
    public ResponseEntity<CustomJson<Object>> register(@ModelAttribute UserDTO userDTO, @ModelAttribute EmployeeDTO employeeDTO) {
        try {
            User user = new User();
            user.setName(userDTO.getName());
            user.setSurname(userDTO.getSurname());
            user.setEmail(userDTO.getEmail());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

            user = userRepository.saveAndFlush(user);

            Employee employee = getEmployee(employeeDTO, user);


            try {
                employeeRepository.save(employee);
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

    private Employee getEmployee(EmployeeDTO employeeDTO, User user) {
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
        employee.setInfoText(employeeDTO.getInfoText());
        return employee;
    }


    //Company Register
    @PostMapping("/company/register")
    public ResponseEntity<CustomJson<Object>> register(@ModelAttribute CompanyDTO companyDTO,@ModelAttribute UserDTO userDTO) {
        try {
            User user = new User();
            user.setName(userDTO.getName());
            user.setSurname(userDTO.getSurname());
            user.setEmail(userDTO.getEmail());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

            user = userRepository.saveAndFlush(user);

            Company company = getCompany(companyDTO, user);
            try {
                companyRepository.save(company);
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

    private Company getCompany(CompanyDTO companyDTO, User user) {
        Company company = new Company();
        company.setUser(user);
        // Set fields from DTO to Company entity
        company.setHasTaxOffice(companyDTO.getHasTaxOffice());
        company.setTaxNo(companyDTO.getTaxNo());
        company.setCompanyName(companyDTO.getCompanyName());
        company.setPhoneNo(companyDTO.getPhoneNo());
        company.setAddress(companyDTO.getAddress());
        company.setxCoor(companyDTO.getxCoor());
        company.setyCoor(companyDTO.getyCoor());
        company.setConfirmationCode(companyDTO.getConfirmationCode());
        company.setConfirmationTime(companyDTO.getConfirmationTime());
        return company;
    }
}
