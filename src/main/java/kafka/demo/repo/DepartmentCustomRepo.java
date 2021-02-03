package kafka.demo.repo;

import java.util.List;

import kafka.demo.model.Department;

interface DepartmentCustomRepo {
	public List<Department> getAll();
}
