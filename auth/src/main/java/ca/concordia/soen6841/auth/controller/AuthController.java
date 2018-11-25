package ca.concordia.soen6841.auth.controller;

import ca.concordia.soen6841.auth.exception.AppException;
import ca.concordia.soen6841.auth.model.Employee;
import ca.concordia.soen6841.auth.model.Role;
import ca.concordia.soen6841.auth.model.RoleName;
import ca.concordia.soen6841.auth.payloads.ApiResponse;
import ca.concordia.soen6841.auth.payloads.SignUpRequest;
import ca.concordia.soen6841.auth.repository.EmployeeRepository;
import ca.concordia.soen6841.auth.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequestMapping("/")
public class AuthController {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
    PasswordEncoder passwordEncoder;

	@Autowired
	private Environment env;

	@RequestMapping("/")
	public String home() {
		// This is useful for debugging
		// When having multiple instance of gallery service running at different ports.
		// We load balance among them, and display which instance received the request.
		return "Hello from Auth Service running at port: " + env.getProperty("local.server.port");
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		if (employeeRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity(new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
		}

		if (employeeRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
		}

		// Creating employee's account
		Employee employee = new Employee();
        employee.setFirstName(signUpRequest.getFirstName());
        employee.setLastName(signUpRequest.getLastName());
        employee.setDateOfBirth(signUpRequest.getDateOfBirth());
        employee.setHiringDate(signUpRequest.getHiringDate());
        employee.setProvince(signUpRequest.getProvince());
        employee.setPostalCode(signUpRequest.getPostalCode());
        employee.setUsername(signUpRequest.getUsername());
        employee.setEmail(signUpRequest.getEmail());
        employee.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

		Role userRole = roleRepository.findByName(RoleName.ROLE_EMPLOYEE)
				.orElseThrow(() -> new AppException("User Role not set."));

        employee.setRoles(Collections.singleton(userRole));

		employeeRepository.save(employee);

		return ResponseEntity.status(HttpStatus.OK)
                .body("Employee registered successfully");
	}
}
