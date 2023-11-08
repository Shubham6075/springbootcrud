package edu.employee.management.translation;

import edu.employee.management.dtos.EmployeeDto;
import edu.employee.management.entity.DepartmentEntity;
import edu.employee.management.entity.EmployeeEntity;
import edu.employee.management.repository.DepartmentRepository;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Component
public class EmployeeTranslation {

    @Autowired
    private DepartmentRepository departmentRepository;
    public EmployeeEntity getEntityFromDto(EmployeeDto employeeDto){
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setFirstName(employeeDto.getFirstName());
        employeeEntity.setLastName(employeeDto.getLastName());
        employeeEntity.setId(employeeDto.getId());
        employeeEntity.setDob(employeeDto.getDob());
        employeeEntity.setGender(employeeDto.getGender());
        employeeEntity.setEmail(employeeDto.getEmail());
        employeeEntity.setDepId(getDepartment(employeeDto.getDepartment()));
        return employeeEntity;
    }

    public EmployeeDto getDtoFromEntity(EmployeeEntity employeeEntity){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName(employeeEntity.getFirstName());
        employeeDto.setLastName(employeeEntity.getLastName());
        employeeDto.setId(employeeEntity.getId());
        employeeDto.setDob(employeeEntity.getDob());
        employeeDto.setGender(employeeEntity.getGender());
        employeeDto.setEmail(employeeEntity.getEmail());
        employeeDto.setDepartment(getDepartmentName(employeeEntity.getDepId()));
        return employeeDto;
    }

    private int getDepartment(String name){
        for(DepartmentEntity departmentEntity : getDepartments()){
            if(departmentEntity.getName().equalsIgnoreCase(name)) {
                return departmentEntity.getId();
            }
        }
        throw new HttpClientErrorException(HttpStatusCode.valueOf(404),"Department id does not exist");
    }

    private String getDepartmentName(int id){
        for(DepartmentEntity departmentEntity : getDepartments()){
            if(departmentEntity.getId() == id) {
                return departmentEntity.getName();
            }
        }
        throw new HttpClientErrorException(HttpStatusCode.valueOf(404),"Department id does not exist");
    }

    private List<DepartmentEntity> getDepartments() {
        return IterableUtils.toList(departmentRepository.findAll());
    }

}
