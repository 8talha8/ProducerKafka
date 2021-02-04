package kafka.demo.srvc;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import kafka.demo.model.Department;
import kafka.demo.repo.DepartmentRepo;

@Service
public class DepartmentSrvc {
	@Autowired
	DepartmentRepo departmentRepo;
	@Autowired
	ProducerSrvc producerSrvc;

	public List<Department> getAll() {
		// TODO Auto-generated method stub
		List<Department> lst = departmentRepo.getAll();
		producerSrvc.produce(lst);
		return lst;
	}

	public Optional<Department> getById(Long id) {
		// TODO Auto-generated method stub
		Optional<Department> dep = departmentRepo.findById(id);
		producerSrvc.produce(dep.isPresent()?dep.get():new Department());
		return dep;
	}

	public void save(Department department) {
		departmentRepo.save(department);

	}

	public Department update(Department department) {

		Department departmentDb = departmentRepo.findById(department.getId()).orElseThrow(
				() -> new ResourceNotFoundException("Depatment not found for the id :: " + department.getId()));

		departmentDb.setName(department.getName());
		departmentDb.setEmployees(department.getEmployees());
		return departmentRepo.save(departmentDb);

	}

	public void delete(Long id) {
		departmentRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Depatment not found for the id :: " + id));
		departmentRepo.deleteById(id);

	}

}
