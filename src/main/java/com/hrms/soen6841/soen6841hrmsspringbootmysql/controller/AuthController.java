package com.hrms.soen6841.soen6841hrmsspringbootmysql.controller;

import com.hrms.soen6841.soen6841hrmsspringbootmysql.exception.AppException;
import com.hrms.soen6841.soen6841hrmsspringbootmysql.model.Employee;
import com.hrms.soen6841.soen6841hrmsspringbootmysql.model.Role;
import com.hrms.soen6841.soen6841hrmsspringbootmysql.model.RoleName;
import com.hrms.soen6841.soen6841hrmsspringbootmysql.repository.EmployeeRepository;
import com.hrms.soen6841.soen6841hrmsspringbootmysql.repository.RoleRepository;
import com.hrms.soen6841.soen6841hrmsspringbootmysql.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hrms.soen6841.soen6841hrmsspringbootmysql.payloads.ApiResponse;
import com.hrms.soen6841.soen6841hrmsspringbootmysql.payloads.JwtAuthenticationResponse;
import com.hrms.soen6841.soen6841hrmsspringbootmysql.payloads.LoginRequest;
import com.hrms.soen6841.soen6841hrmsspringbootmysql.payloads.SignUpRequest;
import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
    AuthenticationManager authenticationManager;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
    PasswordEncoder passwordEncoder;

	@Autowired
	JwtTokenProvider tokenProvider;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
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

		Employee result = employeeRepository.save(employee);

		return ResponseEntity.status(HttpStatus.OK)
                .body("Employee registered successfully");
	}
}
