package kafka.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import kafka.demo.model.Department;

public interface DepartmentRepo extends JpaRepository<Department, Long>, DepartmentCustomRepo {
}
