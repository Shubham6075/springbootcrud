package edu.employee.management.controller;

import edu.employee.management.dtos.EmployeeDto;
import edu.employee.management.entity.DepartmentEntity;
import edu.employee.management.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService empService;

	@RequestMapping(value = "/addnew", method = RequestMethod.GET)
	public String newEmployee(Model model) {
		EmployeeDto employee = new EmployeeDto();
		List<String> options = new ArrayList<>();
		for(DepartmentEntity departmentEntity : empService.getDepartments()) {
			options.add(departmentEntity.getName());
		}
		model.addAttribute("options", options);
		model.addAttribute("employee", employee);
		return "newemployee";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveEmployee(@ModelAttribute("employee") EmployeeDto employee) {
		empService.addNewEmployee(employee);
		return "redirect:/";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String viewEmployees(Model model) {
		model.addAttribute("allemplist", empService.getAllEmployees());
		return "index";
	}

	@RequestMapping(value = "/showFormForUpdate/{id}", method = RequestMethod.GET)
	public String editEmployee(@PathVariable int id, Model model) {
		EmployeeDto employee = empService.getEmployeeById(id);
		List<String> options = new ArrayList<>();
		for(DepartmentEntity departmentEntity : empService.getDepartments()) {
			options.add(departmentEntity.getName());
		}
		model.addAttribute("options", options);
		model.addAttribute("employee", employee);
		return "update";
	}

	@RequestMapping(value = "/deleteEmployee/{id}", method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable int id) {
		empService.deleteEmployee(id);
		return "redirect:/";
	}
}
