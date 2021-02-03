package kafka.demo.cntrlr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kafka.demo.model.Department;
import kafka.demo.model.Employee;
import kafka.demo.respojo.Status;
import kafka.demo.srvc.EmployeeSrvc;

@RestController
@RequestMapping("/employees")
public class EmployeeCntrlr {
	@Autowired
	EmployeeSrvc employeeSrvc;

	@GetMapping("")
	public ResponseEntity<List<Employee>> getAll() {
		return new ResponseEntity<List<Employee>>(employeeSrvc.getAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> find(@PathVariable("id") Long id) {
		Employee employee = employeeSrvc.getById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id : " + id));
		return ResponseEntity.ok().body(employee);
	}

	@PostMapping("/")
	public ResponseEntity<Status> save(@RequestBody Employee employee) {
		employeeSrvc.save(employee);
		Status s = new Status(200, "saved");
		return ResponseEntity.ok(s);
	}

	@PutMapping("/")
	public ResponseEntity<Status> update(@RequestBody Employee employee) {
		employeeSrvc.update(employee);
		return ResponseEntity.ok(new Status(200, "updated"));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Status> delete(@PathVariable("id") Long id) {
		employeeSrvc.delete(id);
		return ResponseEntity.ok(new Status(200, "deleted"));
	}
}
