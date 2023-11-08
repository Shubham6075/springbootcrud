package edu.employee.management.service;

import edu.employee.management.dtos.EmployeeDto;
import edu.employee.management.entity.DepartmentEntity;

import java.util.List;

public interface EmployeeService {

	public EmployeeDto addNewEmployee(EmployeeDto emp);

	public List<EmployeeDto> getAllEmployees();

	public EmployeeDto getEmployeeById(int id);

	public EmployeeDto updateEmployee(EmployeeDto emp);

	public void deleteEmployee(int id);

	public List<DepartmentEntity> getDepartments();

}
