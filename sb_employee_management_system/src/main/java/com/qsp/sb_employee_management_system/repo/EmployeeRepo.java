package com.qsp.sb_employee_management_system.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qsp.sb_employee_management_system.dto.Employee;
                                           //Here We have to mention  which class object wee need and the datatype of primary key in the form of object
                                           //<className,datatype Of Primary key In the form of Object>
public interface EmployeeRepo extends JpaRepository<Employee,Integer>{
      // we are creating one method to find some data by providing unique values
	     // here spring will give the implementation to this method 
	// in the method signature  find and By are keywords
	// after find keyword we need to pass Entity class name and After By keyword we need to pass Based on (unique attribute)
	Employee findEmployeeByPhone(long phone);
	Employee getEmployeeByEmail(String email);
	List<Employee> findEmployeeBySalary(double salary);
	@Query("SELECT e FROM Employee e Where e.address=?1")
	List<Employee> byAddress(String address);
	List<Employee>  findEmployeesBySalaryLessThan(double sal);
	List<Employee> findEmployeesBySalaryGreaterThan(double sal);
//	@Query("SELECT e FROM Employee e WHERE e.salary BETWEEN ?1 AND ?2")
	@Query("SELECT e FROM Employee e where e.salary>=?1 and e.salary<=?2")
	List<Employee> salaryBetween(double salary1, double salary2);
	@Query("SELECT e FROM Employee e WHERE e.grade like ?1")
	List<Employee> findByGrade(char grade);
}
