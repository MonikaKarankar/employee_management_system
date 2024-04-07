package com.qsp.sb_employee_management_system.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.qsp.sb_employee_management_system.dao.EmployeeDao;
import com.qsp.sb_employee_management_system.dto.Employee;
import com.qsp.sb_employee_management_system.exception.EmailNotFound;
import com.qsp.sb_employee_management_system.exception.IdNotFound;
import com.qsp.sb_employee_management_system.exception.NoDataAvailable;
import com.qsp.sb_employee_management_system.exception.PhoneNumberNotFound;
import com.qsp.sb_employee_management_system.util.ResponseStructure;
@Service // it can have only bussiness logic
public class EmployeeService {
	@Autowired
	EmployeeDao dao;

	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(Employee employee) {
		double sal = employee.getSalary();
		if (sal < 10000) {
			employee.setGrade('D');
		} else if (sal >= 10000 && sal < 20000) {
			employee.setGrade('C');
		} else if (sal >= 20000 && sal < 40000) {
			employee.setGrade('B');
		} else {
			employee.setGrade('A');
		}
		// by using response structure we are setting the response to the client
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		structure.setMessage("SAVE SUCCESSFUL");
		structure.setStatus(HttpStatus.CREATED.value());// 201 status codefor created
		structure.setData(dao.saveEmployee(employee));
//		return structure;
		// here we are sending  responseEntity Object because of status mismatch 
		return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.CREATED);
	}
	public ResponseEntity< ResponseStructure<Employee>> findById(int id) {
		Employee employee = dao.findEmployee(id);
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		if (employee != null) {
			structure.setMessage("Found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employee);
			//return structure
			return new ResponseEntity< ResponseStructure<Employee>>(structure,HttpStatus.FOUND);

		} else {
//			structure.setMessage("Not Found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(employee);
//			return structure;
			throw new IdNotFound("Employee is not found with given Id ");
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> findAll() {
		List<Employee> employees = dao.findAll();
		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
		if (employees.isEmpty()) {
			structure.setMessage("Not Found");
			structure.setData(employees);
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			throw new NoDataAvailable("Data Is Not Present In Your Database");
			
		} else {
			structure.setMessage("Found");
			structure.setData(employees);
			structure.setStatus(HttpStatus.FOUND.value());
//			return structure;
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.FOUND);
			
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> deleteEmployeeById(int id) {
		Employee employee = dao.findEmployee(id);
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();

		if (employee != null) {
			structure.setMessage("Deleted");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dao.deleteEmployeeById(employee));
//			return structure;
			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.OK);
		} else {
			structure.setMessage("Not Found");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setData(employee);
//			return structure;
			throw new IdNotFound("Employee is not found with given Id ");
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(int id, Employee employee) {
		// TODO Auto-generated method stub
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		Employee dbEmployee = dao.findEmployee(id);
		if (dbEmployee != null) {
			employee.setId(id);
//		    	 double sal=employee.getSalary();
//		   		if (sal<10000) {
//		   			employee.setGrade('D');
//		   		}
//		   		else if(sal>=10000 && sal<20000) {
//		   			employee.setGrade('C');
//		   		}
//		   		else if(sal>=20000&&sal<40000) {
//		   			employee.setGrade('B');
//		   		}
//		   		else {
//		   			employee.setGrade('A');
//		   		}
//		   		return dao.saveEmployee(employee);
			return saveEmployee(employee);
		} else {
			structure.setMessage("Not found");
			structure.setStatus(HttpStatus.NOT_FOUND.value());
			structure.setData(dbEmployee);
//			return structure;
			throw new IdNotFound("Employee is not found with given Id ");
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> saveAll(List<Employee> employees) {
		// TODO Auto-generated method stub
		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();

		for (Employee employee : employees) {
			double sal = employee.getSalary();
			if (sal < 10000) {
				employee.setGrade('D');
			} else if (sal >= 10000 && sal < 20000) {
				employee.setGrade('C');
			} else if (sal >= 20000 && sal < 40000) {
				employee.setGrade('B');
			} else {
				employee.setGrade('A');
			}
		}
		structure.setMessage("All Employee Saved....!");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dao.saveAll(employees));
		return  new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<Employee>> updatePhone(int id, long phone) {
		// TODO Auto-generated method stub
		Employee employee = dao.findEmployee(id);
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		if (employee != null) {
			employee.setPhone(phone);
			structure.setMessage("Saved Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dao.saveEmployee(employee));
			return new  ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.OK);
		} else {
//			structure.setMessage("Not Found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(employee);
//			return structure;
			throw new IdNotFound("Employee is not found with given Id ");
		}
	}

//	
	public ResponseEntity<ResponseStructure<Employee>> findByPhone(long phone) {
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();

		Employee employee = dao.findByPhone(phone);
		if (employee != null) {
			employee.setPhone(phone);
			structure.setMessage("Found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.FOUND);
		} else {
//			structure.setMessage("Not Found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(employee);
//			return structure;
			throw  new PhoneNumberNotFound("Employee With Given Phone number is not found");
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> finByEmail(String email) {
		// TODO Auto-generated method stub
		Employee employee = dao.finByEmail(email);
		ResponseStructure<Employee> structure = new ResponseStructure<Employee>();
		if (employee != null) {
			structure.setMessage("Found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.FOUND);
		} else {
//			structure.setMessage("Not Found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(employee);
//			return structure;
			throw new EmailNotFound("Employee With given email is not found");
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> updateEmail(int id, String email) {
		// TODO Auto-generated method stub
;  
		ResponseStructure<Employee>structure=new ResponseStructure<Employee>();
//         ResponseEntity<ResponseStructure<Employee>>strucure=findById(id);
		           Employee employee=dao.findEmployee(id);
		           
		if(employee!=null) {
			employee.setEmail(email);
			structure.setMessage("Updated");
			structure.setStatus(HttpStatus.OK.value());
//			ResponseStructure<Employee>entity=saveEmployee(structure.getData()).getBody();
//			structure.setData(saveEmployee(structure.getData()).getBody().getData());
//			structure.setData(entity.getData());
			structure.setData(saveEmployee(employee).getBody().getData());
			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.CREATED);
			
		} 
		else{
//			structure.setMessage("not found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(structure.getData());  
//			return structure;
			throw new IdNotFound("Employee is not found with given Id ");
		}
	}

	public ResponseEntity<ResponseStructure<Employee>> updateSalary(int id, double salary) {
		// TODO Auto-generated method stub
	     Employee  employee= dao.findEmployee(id);
	     ResponseStructure<Employee>structure=new ResponseStructure<Employee>();
//		 
		if (employee!= null) {
			 employee.setSalary(salary);
			structure.setMessage("Found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employee);
			return new ResponseEntity<ResponseStructure<Employee>>(structure,HttpStatus.FOUND);
		} else {
			structure.setMessage("Not found");
			structure.setData(structure.getData());
			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			return structure;
			throw new IdNotFound("Employee is not found with given Id ");
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> findBysal(double salary) {
		// TODO Auto-generated method stub
		List<Employee> employees = dao.findBysal(salary);
		ResponseStructure<List<Employee>> structure = new ResponseStructure<List<Employee>>();
		if (employees.isEmpty()) {
//			structure.setMessage("Not Found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(employees);
//			return structure;
			throw new NoDataAvailable("No Employees present With Given Salary");
		} else {
			structure.setMessage("Found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employees);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.FOUND);
			
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> findByAddress(String address) {
		// TODO Auto-generated method stub
		List<Employee>employees=dao.byAddress(address);
		ResponseStructure<List<Employee>>structure=new ResponseStructure<List<Employee>>();
		if (employees.isEmpty()) {
//			structure.setMessage("Not Found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(employees);
//			return structure;
			throw new NoDataAvailable("Employee Not Found by given Address");
		}else {
			structure.setMessage("Found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employees);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.FOUND);
			
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> lessThan(double sal) {
		// TODO Auto-generated method stub
		List<Employee>employees=dao.lessThan(sal);
		ResponseStructure<List<Employee>>structure=new ResponseStructure<List<Employee>>();
		
		if (employees.isEmpty()) {
//			structure.setMessage("Not found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(employees);
//		  return structure;
			throw new NoDataAvailable("Employees Are not present with given salary");
		}
		else {
			structure.setMessage("Found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employees);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> greaterThan(double salary) {
		// TODO Auto-generated method stub
		List<Employee>employees=dao.greaterThan(salary);
		ResponseStructure<List<Employee>>structure=new ResponseStructure<List<Employee>>();
		
		if (employees.isEmpty()) {
//			structure.setMessage("Not Found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(employees);
//			return structure;
			throw new NoDataAvailable("Employees Are not present with given salary");
		}
		else {
			structure.setMessage("Found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employees);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> salaryBetween(double salary1, double salary2) {
		// TODO Auto-generated method stub
		List<Employee>employees=dao.salaryBetween(salary1, salary2);
		ResponseStructure<List<Employee>>structure=new ResponseStructure<List<Employee>>();
		if (employees.isEmpty()) {
//			structure.setMessage("Not Found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(employees);
//			return structure;
			throw new NoDataAvailable("Employees Are not present with given salary");
		}
		else {
			structure.setMessage("Found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employees);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.FOUND);
		}
	}
	public ResponseEntity<ResponseStructure<List<Employee>>> findByGrade(char grade) {
		// TODO Auto-generated method stub
		List<Employee>employees=dao.findByGrade(grade);
		ResponseStructure<List<Employee>>structure=new ResponseStructure<List<Employee>>();
		if (employees.isEmpty()) {
//			structure.setMessage("Not Found");
//			structure.setStatus(HttpStatus.NOT_FOUND.value());
//			structure.setData(employees);
//			return structure;
			throw new NoDataAvailable("Data Is Not Found By Give Grade");
		}
		else {
			structure.setMessage("Found");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(employees);
			return new ResponseEntity<ResponseStructure<List<Employee>>>(structure,HttpStatus.FOUND); 
		}
	}


}
