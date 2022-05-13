package hu.webuni.hr.nyhp.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hu.webuni.hr.nyhp.model.Employee;
import hu.webuni.hr.nyhp.repository.EmployeeRepository;

@Service
public class HrUserDetailsService implements UserDetailsService {
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee employee = employeeRepository.findByUsername(username)
				.orElseThrow(()-> new UsernameNotFoundException(username));
		
		return new User(username,employee.getPassword(),
				employee.getRoles().stream().map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList()));

	}

}
