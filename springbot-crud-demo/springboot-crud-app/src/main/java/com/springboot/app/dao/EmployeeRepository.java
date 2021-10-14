package com.springboot.app.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.app.entity.Employee;

// defines the crud meth for free......
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// adding custom meth to emp repo
	// spring data jpa will parse the meth name and looks for specific format/pattern
	// creates appt query... BTS!
	
	/// add a method to sort by last name
	public List<Employee> findAllByOrderByLastNameAsc();
	
	// search by name
	public List<Employee> findByFirstNameContainsOrLastNameContainsAllIgnoreCase(String name, String lName);
	
}
