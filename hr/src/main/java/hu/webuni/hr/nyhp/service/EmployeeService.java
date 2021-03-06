package hu.webuni.hr.nyhp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import hu.webuni.hr.nyhp.model.Company;
import hu.webuni.hr.nyhp.model.Employee;
import hu.webuni.hr.nyhp.model.Position;
import hu.webuni.hr.nyhp.repository.CompanyRepository;
import hu.webuni.hr.nyhp.repository.EmployeeRepository;

@Service
public abstract class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	PositionService positionService;

	@Autowired
	CompanyService companyService;
	
	@Autowired
	CompanyRepository companyRepository;

//	public EmployeeService(EmployeeRepository employeeRepository) {
//		super();
//		this.employeeRepository = employeeRepository;
//	}

//	private Map<Long, Employee> employees = new HashMap<>();

	@Transactional
	public Employee modifyEmployee(long id, Employee employee) {
		Employee emp = findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		employee.setId(id);
		positionService.setPositionForEmployee(employee);
		return save(employee);
	}

	@Transactional
	public void deleteById(long id) {
		Employee employee = findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		delete(id);
	}

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
		positionService.setPositionForEmployee(employee);
		setCompanyForEmployee(employee);
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
		// return employeeRepository.selectWhenSalaryBiggerThan(salary);

		// return employeeRepository.findBySalaryGreaterThan(salary);
		Page<Employee> page = employeeRepository.findBySalaryGreaterThan(salary, pageable);
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

	public List<Employee> findEmployeeByExample(Employee example) {

		long id = example.getId();
		String name = example.getName();
		Position pos = example.getPos();
		int salary = example.getSalary();
		LocalDateTime startd = example.getStartd();
		Company company = example.getCompany();

		Specification<Employee> spec = Specification.where(null);

		if (id > 0) {
			spec = spec.and(EmployeeSpecifications.hasId(id));
		}

		if (StringUtils.hasText(name)) {
			spec = spec.and(EmployeeSpecifications.hasName(name));
		}

		if (pos != null && pos.getName() != null && !pos.getName().equals("")) {
			spec = spec.and(EmployeeSpecifications.hasPos(pos));
		}

		if (salary > 0) {
			spec = spec.and(EmployeeSpecifications.hasSalary(salary));
		}

		if (startd != null) {
			spec = spec.and(EmployeeSpecifications.hasStartd(startd));
		}

		if (company != null && company.getName() != null && !company.getName().equals("")) {
			spec = spec.and(EmployeeSpecifications.hasCompany(company));
		}

		return employeeRepository.findAll(spec, Sort.by("id"));
	}

	public void setCompanyForEmployee(Employee employee) {
		Company company = employee.getCompany();
		if (company != null) {
			if(!companyRepository.existsById(company.getId()))
				company = null;
		}
		employee.setCompany(company);
	}

	public abstract int getPayRaisePercent(Employee employee);

}
