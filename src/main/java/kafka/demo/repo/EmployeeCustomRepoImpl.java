package kafka.demo.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kafka.demo.model.Employee;

@Repository
public class EmployeeCustomRepoImpl implements EmployeeCustomRepo {
	@Autowired
	private EntityManager entityManager;

	public List<Employee> getAll() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
		Root<Employee> variableRoot = query.from(Employee.class);
		query.select(variableRoot);

		return entityManager.createQuery(query).getResultList();
	}

}
