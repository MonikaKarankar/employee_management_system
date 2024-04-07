	package com.qsp.sb_employee_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.sb_employee_management_system.dao.EmployeeDao;
import com.qsp.sb_employee_management_system.dto.Employee;
import com.qsp.sb_employee_management_system.service.EmployeeService;
import com.qsp.sb_employee_management_system.util.ResponseStructure;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
public class EmployeeController {
//	     @Autowired
//        private EmployeeDao dao;
	@Autowired
	private EmployeeService service;
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(@RequestBody @Valid Employee employee) {
		
		return service.saveEmployee(employee);
	}
	// WHEN WE USING QUERY STRING
	@GetMapping("/fetch")
	public ResponseEntity<ResponseStructure<Employee>> fetchById(@RequestParam int id) {
		return service.findById(id);
	}
	@GetMapping("/findall")
	public ResponseEntity<ResponseStructure<List<Employee>>> findAll() {
		return service.findAll();
	}
	// WHEN WE ARE USING PATH VERIABLE 
	@DeleteMapping("delete/{id}")
	public ResponseEntity<ResponseStructure<Employee>> deleteEmployee(@PathVariable int id) {
	       return service.deleteEmployeeById(id);	
	}
	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(@RequestParam int id,@RequestBody Employee employee) {
		return service.updateEmployee (id,employee);
	}
	@PostMapping("/saveall")
	public ResponseEntity<ResponseStructure<List<Employee>>> saveAll(@RequestBody List<Employee>employees) {
		return service.saveAll(employees);
	}
	@PatchMapping("/updatephone")
	public ResponseEntity<ResponseStructure<Employee>> updatePhone(int id,long phone) {
		return service.updatePhone(id,phone);
	}
	@PatchMapping("/updateemail")
	public ResponseEntity<ResponseStructure<Employee>> updateEmail(int id,@RequestParam String email) {
		return service.updateEmail(id,email);
	}
	@PatchMapping("/updatesalary")
	public ResponseEntity<ResponseStructure<Employee>> UpdateSalary(@RequestParam int id,@RequestParam double salary) {
		return service.updateSalary(id,salary);
	}
	@GetMapping("/findbyphone")
	public ResponseEntity<ResponseStructure<Employee>> findByPhone(@RequestParam long phone) {
		return service.findByPhone(phone);
	}
	@GetMapping("/findbyemail")
	public ResponseEntity<ResponseStructure<Employee>> findByEmail(@RequestParam String email) {
		return service.finByEmail(email);
	}
	@GetMapping("/findbysalary")
	public ResponseEntity<ResponseStructure<List<Employee>>> findBySalary(@RequestParam double salary) {
		return service.findBysal(salary);
	}
	@GetMapping("/byaddress")
	public ResponseEntity<ResponseStructure<List<Employee>>> byAddress(@RequestParam String address) {
		return service.findByAddress(address);
	}
	@GetMapping("/lessthan")
	public ResponseEntity<ResponseStructure<List<Employee>>> lessThan(@RequestParam double sal) {
		return service.lessThan(sal);
	}
	@GetMapping("/greaterthan")
	public ResponseEntity<ResponseStructure<List<Employee>>> greaterThan(double salary) {
		return service.greaterThan(salary);
	}
	@GetMapping("/salarybetween")
	public ResponseEntity<ResponseStructure<List<Employee>>> salaryBetween(double salary1,double salary2) {	
		return service.salaryBetween(salary1,salary2);
	}
	@GetMapping("/findbygrade")
	public ResponseEntity<ResponseStructure<List<Employee>>> findByGrade( @RequestParam char grade ) {
		return  service.findByGrade(grade);
	}
}
