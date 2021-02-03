package kafka.demo.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kafka.demo.model.Department;

@Repository
public class DepartmentCustomRepoImpl implements DepartmentCustomRepo {
	@Autowired
	private EntityManager entityManager;

	public List<Department> getAll() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Department> query = cb.createQuery(Department.class);
		Root<Department> variableRoot = query.from(Department.class);
		query.select(variableRoot);

		return entityManager.createQuery(query).getResultList();
	}

}
