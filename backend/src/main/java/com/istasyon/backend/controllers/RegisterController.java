package com.istasyon.backend.controllers;

import com.istasyon.backend.dataObjects.EmployeeDTO;
import com.istasyon.backend.dataObjects.UserDTO;
import com.istasyon.backend.entities.Employee;
import com.istasyon.backend.entities.User;
import com.istasyon.backend.repositories.EmployeeRepo;
import com.istasyon.backend.repositories.UserRepo;
import com.istasyon.backend.utilities.CustomJson;
import com.istasyon.backend.utilities.JsonCreator;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    private final UserRepo userRepository;
    private final EmployeeRepo employeeRepository;
    private final JsonCreator jsonCreator;
    private final BCryptPasswordEncoder passwordEncoder;
    public RegisterController(UserRepo userRepository, EmployeeRepo employeeRepository, JsonCreator jsonCreator, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
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


            employeeRepository.save(employee);
            return jsonCreator.create("Registration successful");
        } catch (Exception e) {
            return jsonCreator.create("Error during registration: " + e, 500);
        }
    }

    private Employee getEmployee(EmployeeDTO employeeDTO, User user) {
        Employee employee = new Employee();
        employee.setUser(user);
        // Set fields from DTO to Employee entity
        //employee.setEUserNo(user.getUserId());
        employee.setPhoneNo(employeeDTO.getPhoneNo());
        employee.setAddress(employeeDTO.getAddress());
        //employee.setxCoor(employeeDTO.getyCoor());
        //employee.setyCoor(employeeDTO.getyCoor());
        //employee.setConfirmationCode(employeeDTO.getConfirmationCode());
        //employee.setConfirmationTime(employeeDTO.getConfirmationTime());
        employee.setGender(employeeDTO.getGender());
        employee.setBirthDate(employeeDTO.getBirthDate());
        employee.setMilitaryServiceInfo(employeeDTO.getMilitaryServiceInfo());
        //employee.setDriversLicence(employeeDTO.getDriversLicence());
        employee.setInfoText(employeeDTO.getInfoText());
        return employee;
    }
}
