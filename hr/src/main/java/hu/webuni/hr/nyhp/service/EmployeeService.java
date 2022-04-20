package hu.webuni.hr.nyhp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	public List<Employee> findOfHigherSalary(int salary, Pageable pageable) {
//		return (new ArrayList<>(employees.values())).stream().filter(e -> e.getSalary() > salary)
//				.collect(Collectors.toList());
		//return employeeRepository.selectWhenSalaryBiggerThan(salary);
		
		//return employeeRepository.findBySalaryGreaterThan(salary);
		Page<Employee> page= employeeRepository.findBySalaryGreaterThan(salary, pageable);
		List<Employee> employees = page.getContent();
		
		System.out.println(page.getTotalElements());
		System.out.println(page.getTotalPages());
		System.out.println(page.getSize());
		System.out.println(page.isFirst());
		System.out.println(page.isLast());
		System.out.println(page.hasNext());
		System.out.println(page.hasPrevious());
		
		return employees;
	}

	public abstract int getPayRaisePercent(Employee employee);
	
}
