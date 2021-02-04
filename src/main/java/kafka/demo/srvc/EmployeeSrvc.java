package kafka.demo.srvc;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import kafka.demo.model.Department;
import kafka.demo.model.Employee;
import kafka.demo.repo.EmployeeRepo;

@Service
public class EmployeeSrvc {
	@Autowired
	EmployeeRepo employeeRepo;
	@Autowired
	ProducerSrvc producerSrvc;

	public List<Employee> getAll() {
		// TODO Auto-generated method stub
		List<Employee> lst = employeeRepo.getAll();
		producerSrvc.produceEmp(lst);
		return lst;
	}

	public Optional<Employee> getById(Long id) {
		// TODO Auto-generated method stub
		Optional<Employee> emp = employeeRepo.findById(id);
		producerSrvc.produce(emp.isPresent()?emp.get():new Employee());
		return emp;
	}

	public void save(Employee employee) {
		employeeRepo.save(employee);

	}

	public Employee update(Employee employee) {

		Employee employeeDb = employeeRepo.findById(employee.getEmpId()).orElseThrow(
				() -> new ResourceNotFoundException("Depatment not found for the id :: " + employee.getEmpId()));

		employeeDb.setEmpName(employee.getEmpName());
		employeeDb.setDepartment(employee.getDepartment());
		return employeeRepo.save(employeeDb);

	}

	public void delete(Long id) {
		employeeRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Depatment not found for the id :: " + id));
		employeeRepo.deleteById(id);

	}

}
