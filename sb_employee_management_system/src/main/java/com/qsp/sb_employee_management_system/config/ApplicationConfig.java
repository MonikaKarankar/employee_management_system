package com.qsp.sb_employee_management_system.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@Configuration
@EnableSwagger2
//SWAGGER DOCUMENTATION
//to check swagger documentation on google-->http://localhost:8080/swagger-ui.html#
public class ApplicationConfig {
	@Bean //it is mandatory to write if we forget it will not work
    public Docket getDocket() {
		Contact contact=new Contact("Qspiders","EmployeeManagementSystem","qspiders@gmail.com");//springfox.documentation.service.Contact;
		List<VendorExtension>extensions=new ArrayList<VendorExtension>();//for showing the customer
		ApiInfo apiInfo=new ApiInfo("EmployeeManagementSystem","To manage the Employees","Version 1.0","www.emps.com", contact,"QSP001","www.qsp.com", extensions);
		
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.qsp.sb_employee_management_system")).build().apiInfo(apiInfo).useDefaultResponseMessages(false);
		
	}
}
