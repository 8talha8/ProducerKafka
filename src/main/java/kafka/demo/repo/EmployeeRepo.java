package kafka.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import kafka.demo.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long>, EmployeeCustomRepo {
}
