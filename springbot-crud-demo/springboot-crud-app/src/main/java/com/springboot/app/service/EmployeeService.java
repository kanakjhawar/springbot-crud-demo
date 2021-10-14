package com.springboot.app.service;

import java.util.List;

import com.springboot.app.entity.Employee;

public interface EmployeeService {

	public List<Employee> findAll();
	public Employee findById(int id);
	public void save(Employee theEmployee);
	public void deleteById(int id);
	public List<Employee> searchBy(String theName);
}
