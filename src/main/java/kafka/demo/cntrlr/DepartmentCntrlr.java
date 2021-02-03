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
import kafka.demo.respojo.Status;
import kafka.demo.srvc.DepartmentSrvc;

@RestController
@RequestMapping("/departments")
public class DepartmentCntrlr {
	@Autowired
	DepartmentSrvc departmentSrvc;

	@GetMapping
	public ResponseEntity<List<Department>> getAll() {
		return new ResponseEntity<List<Department>>(departmentSrvc.getAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Department> find(@PathVariable("id") Long id) {
		Department department = departmentSrvc.getById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Department not found for this id : " + id));
		return ResponseEntity.ok().body(department);
	}

	@PostMapping("/")
	public ResponseEntity<Status> save(@RequestBody Department department) {
		departmentSrvc.save(department);
		Status s = new Status(200, "saved");
		return ResponseEntity.ok(s);
	}

	@PutMapping("/")
	public ResponseEntity<Status> update(@RequestBody Department department) {
		departmentSrvc.update(department);
		return ResponseEntity.ok(new Status(200, "updated"));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Status> delete(@PathVariable("id") Long id) {
		departmentSrvc.delete(id);
		return ResponseEntity.ok(new Status(200, "deleted"));
	}
}
