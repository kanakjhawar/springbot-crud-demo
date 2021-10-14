package com.springboot.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.app.entity.Employee;
import com.springboot.app.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService; 
	}
	
	// add mapping for "/list"
	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		
		List<Employee> theEmployees = employeeService.findAll();
		
		// add to model
		theModel.addAttribute("employees" , theEmployees);
		return "employees/list-employees";
	}
	
	// add a new meth for the button ref
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attr to bind data
		Employee theEmployee = new Employee();
		
		// thymeleaf will access this "employee" data for binding form data
		theModel.addAttribute("employee", theEmployee);
		
		return "employees/employee-form";
	}
	
	// pass the model attr for employee, form data passed in using data binding
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		
		// save employee
		employeeService.save(theEmployee);
		
		// use redirect to prevent duplicate submissions 
		// using "Post/Redirect/Get" pattern
		return "redirect:/employees/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId")int id,
										Model theModel) {
		
		// get emp from service/db 
		Employee theEmployee = employeeService.findById(id);
		
		// set emp as model attr to pre-populate the form
		theModel.addAttribute("employee", theEmployee);
		
		// send data to form
		return "employees/employee-form";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId")int id) {
		
		// delete the employee
		employeeService.deleteById(id);
		
		// redirect
		return "redirect:/employees/list";
	}
	
	@GetMapping("/search")
	public String search(@RequestParam("employeeName") String theName,
						 Model theModel) {
		
		// search the employee
		List<Employee> theEmployees = employeeService.searchBy(theName);
		
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);
		
		// send to /employees/list
		return "/employees/list-employees";
		
	}
}













