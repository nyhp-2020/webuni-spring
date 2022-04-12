package hu.webuni.hr.nyhp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.hr.nyhp.model.Employee;
import hu.webuni.hr.nyhp.repository.EmployeeRepository;

@Service
public abstract class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;

//	public EmployeeService(EmployeeRepository employeeRepository) {
//		super();
//		this.employeeRepository = employeeRepository;
//	}

//	private Map<Long, Employee> employees = new HashMap<>();

	public List<Employee> findAll() {
//		return new ArrayList<>(employees.values());
		return employeeRepository.findAll();
	}

	public Optional<Employee> findById(long id) {
//		return employees.get(id);
		return employeeRepository.findById(id);
	}

	@Transactional
	public Employee save(Employee employee) {
//		employees.put(employee.getId(), employee);
//		return employee;
		return employeeRepository.save(employee);
	}

//	public Employee modify(Employee employee) {
//		employees.put(employee.getId(), employee);
//		return employee;
//	}

	@Transactional
	public void delete(long id) {
//		employees.remove(id);
		employeeRepository.deleteById(id);
	}

	public List<Employee> findOfHigherSalary(int salary) {
//		return (new ArrayList<>(employees.values())).stream().filter(e -> e.getSalary() > salary)
//				.collect(Collectors.toList());
		//return employeeRepository.selectWhenSalaryBiggerThan(salary);
		return employeeRepository.findBySalaryGreaterThan(salary);
	}

	public abstract int getPayRaisePercent(Employee employee);

}
