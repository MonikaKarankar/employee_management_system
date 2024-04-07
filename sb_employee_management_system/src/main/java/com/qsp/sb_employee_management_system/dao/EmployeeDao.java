package com.qsp.sb_employee_management_system.dao;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import com.qsp.sb_employee_management_system.dto.Employee;
import com.qsp.sb_employee_management_system.repo.EmployeeRepo;

@Repository
public class EmployeeDao {
	       @Autowired
         private EmployeeRepo employeeRepo;
	public Employee saveEmployee(Employee employee) {
		return employeeRepo.save(employee);		
	}
	public Employee findEmployee(int id) {
		// TODO Auto-generated method stub
//          return employeeRepo.findById(id).get();
		
//		TO AVOID NoSuchElementException
		Optional<Employee>optional=employeeRepo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	public List<Employee> findAll() {
		List<Employee>employees=employeeRepo.findAll();
		return employees;
	}
//	public Employee deleteEmployee(int id) {
		// TODO Auto-generated method stub
//		   Optional<Employee>optional=employeeRepo.findById(id);
//		    
//		   if (optional.isPresent()) {
//			   //here we are trying to delete by id
//              employeeRepo.deleteById(id);
//			 employeeRepo.delete(optional.get());
//			 return optional.get();		
//		}
//		return null;
//	}
	public Employee deleteEmployeeById(Employee employee) {
		employeeRepo.delete(employee);
		return employee;
	}
//	public Employee updateEmployee(int id, Employee employee) {
//		// TODO Auto-generated method stub
//		  Optional<Employee>optional=employeeRepo.findById(id);
//		  if (optional.isPresent()) {
//			employee.setId(id);
//			return employeeRepo.save(employee);
//		}
//		return null;
//	}
	public List<Employee> saveAll(List<Employee> employees) {
		// TODO Auto-generated method stub
		
		return employeeRepo.saveAll(employees);
	}
//	public Employee updatePhone(int id, long phone) {
//		// TODO Auto-generated method stub
//		Optional<Employee>optional=employeeRepo.findById(id);
//		if (optional.isPresent()) {	 
//			Employee employee=optional.get();
//			employee.setPhone(phone);     			
//			return employeeRepo.save(employee);
//		}
//		return null;
//	}
//	public Employee updateEmail(int id, String email) {
//		// TODO Auto-generated method stub
//		Optional<Employee>optional=employeeRepo.findById(id);
//		if (optional.isPresent()) {
//			Employee employee=optional.get();
//			employee.setEmail(email);
//			return employeeRepo.save(employee);
//		}
//       return null;
//	}
//	public Employee updateSalary(int id,double salary) {
//		// TODO Auto-generated method stub
//		Optional<Employee>optional=employeeRepo.findById(id);
//		if (optional.isPresent()) {	
//			Employee employee=optional.get();
//			employee.setSalary(salary);
//			return employeeRepo.save(employee);
//		}
//		return null;
//	}
	public Employee findByPhone(long phone) {
		// TODO Auto-generated method stub
		return employeeRepo.findEmployeeByPhone(phone);
	}
	public Employee finByEmail(String email) {
		// TODO Auto-generated method stub
		return employeeRepo.getEmployeeByEmail(email);
	}
	public List<Employee> findBysal(double salary) {
		// TODO Auto-generated method stub
		return employeeRepo.findEmployeeBySalary(salary);

	}
	public List<Employee> byAddress(String address) {
		return employeeRepo.byAddress(address);
	}
	public List<Employee> lessThan(double sal) {
		// TODO Auto-generated method stub
		 return employeeRepo.findEmployeesBySalaryLessThan(sal);
	}
	public List<Employee> greaterThan(double sal) {
		// TODO Auto-generated method stub
		return employeeRepo.findEmployeesBySalaryGreaterThan(sal);
		
	}
	public List<Employee> salaryBetween(double salary1,double salary2) {
		
		return employeeRepo.salaryBetween(salary1,salary2);
	}
	public List<Employee> findByGrade(char grade) {
		// TODO Auto-generated method stub
		return employeeRepo.findByGrade(grade);
	}
	
}
