package hu.webuni.hr.nyhp.mapper;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.webuni.hr.nyhp.dto.EmployeeDto;
import hu.webuni.hr.nyhp.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
	List<EmployeeDto> employeesToDtos(List<Employee> employees);
	
	@Mapping(source = "pos.name", target = "position")
	@Mapping(source = "company.name", target = "companyName")
	@Mapping(target = "company.employees", ignore = true)
	@Mapping(target = "pos.employees", ignore = true)
	EmployeeDto employeeToDto(Employee employee);

	@InheritInverseConfiguration
	Employee dtoToEmployee(EmployeeDto employeeDto);

	List<Employee> dtosToEmployees(List<EmployeeDto> newemployees);
	
//	Set<EmployeeDto> employeesSetToDtosSet(Set<Employee> employees);
//	Set<Employee> dtosSetToEmployeesSet(Set<EmployeeDto> employees);
}
