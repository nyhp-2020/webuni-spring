package hu.webuni.hr.nyhp.mapper;

import java.util.List;

import javax.validation.Valid;

import org.mapstruct.Mapper;

import hu.webuni.hr.nyhp.dto.EmployeeDto;
import hu.webuni.hr.nyhp.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
	List<EmployeeDto> employeesToDtos(List<Employee> employees);

	EmployeeDto employeeToDto(Employee employee);

	Employee dtoToEmployee(@Valid EmployeeDto employeeDto);

	List<Employee> dtosToEmployees(List<EmployeeDto> newemployees);
}
