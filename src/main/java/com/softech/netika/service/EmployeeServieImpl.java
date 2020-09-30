package com.softech.netika.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.softech.netika.model.Employee;
import com.softech.netika.repository.EmployeeRepository;

@Service
public class EmployeeServieImpl implements EmployeeService {
@Autowired
EmployeeRepository employeeRepository;
	@Override
	public List<Employee> getAllEMployees() {
		// TODO Auto-generated method stub
		 return employeeRepository.findAll();
	}
	@Override
	public void saveEmployee(Employee employee) {
		this.employeeRepository.save(employee);
	}
	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> optional=employeeRepository.findById(id);
		Employee employee=null;
		if(optional.isPresent()) {
			employee=optional.get();
		}
		else {
			throw new RuntimeException("Employee not found for id::"+id);
		}
		return employee;
	}
	@Override
	public void deleteEmployeeById(long id) {
 this.employeeRepository.deleteById(id);		
	}
	@Override
	public Page<Employee> findPaginated(int pageNo, int pageSize,String sortield,String sortDirection) {
		
		Sort sort=sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortield).ascending():Sort.by(sortield).descending();
		
		Pageable pageable=PageRequest.of(pageNo - 1, pageSize,sort);
		return this.employeeRepository.findAll(pageable);
	}
 
}
