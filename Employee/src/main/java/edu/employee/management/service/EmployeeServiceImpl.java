package edu.employee.management.service;

import edu.employee.management.dtos.EmployeeDto;
import edu.employee.management.entity.DepartmentEntity;
import edu.employee.management.entity.EmployeeEntity;
import edu.employee.management.repository.DepartmentRepository;
import edu.employee.management.repository.EmployeeRepository;

import edu.employee.management.translation.EmployeeTranslation;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeTranslation employeeTranslation;

	@Override
	public EmployeeDto addNewEmployee(EmployeeDto emp) {
		EmployeeEntity employeeEntity = employeeRepository.save(employeeTranslation.getEntityFromDto(emp));
		return employeeTranslation.getDtoFromEntity(employeeEntity);
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
		List<EmployeeEntity> employeeEntities = IterableUtils.toList(employeeRepository.findAll());
		List<EmployeeDto> employeeDtos = new ArrayList<>();
		for (EmployeeEntity employeeEntity : employeeEntities){
			employeeDtos.add(employeeTranslation.getDtoFromEntity(employeeEntity));
		}
		return employeeDtos;
	}

	@Override
	public EmployeeDto getEmployeeById(int id) {
		return employeeTranslation.getDtoFromEntity(employeeRepository.findById(id).orElse(null));
	}

	@Override
	public EmployeeDto updateEmployee(EmployeeDto emp) {
		return employeeTranslation.getDtoFromEntity(employeeRepository.save(employeeTranslation.getEntityFromDto(emp)));
	}

	@Override
	public void deleteEmployee(int id) {
		employeeRepository.deleteById(id);
	}

	@Override
	public List<DepartmentEntity> getDepartments() {
		return IterableUtils.toList(departmentRepository.findAll());
	}
}
