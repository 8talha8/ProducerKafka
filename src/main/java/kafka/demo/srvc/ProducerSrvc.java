package kafka.demo.srvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import kafka.demo.model.Department;
import kafka.demo.model.Employee;

@Service
public class ProducerSrvc {
	@Autowired
	private KafkaTemplate<String, Department>  kafkaTemplate;
	@Autowired
	private KafkaTemplate<String, Employee>  kafkaTemplateEmp;
	@Autowired
	private KafkaTemplate<String, List<Department>>  kafkaTemplateLst;
	@Autowired
	private KafkaTemplate<String, List<Employee>>  kafkaTemplateEmpLst;
public void produce(List<Department> lst) {
	kafkaTemplateLst.send("departmentTopicLst",lst);
}

public void produce(Department dep) {
	kafkaTemplate.send("departmentTopic",dep);
}
public void produceEmp(List<Employee> lst) {
	kafkaTemplateEmpLst.send("employeeTopicLst",lst);
}

public void produce(Employee emp) {
	kafkaTemplateEmp.send("employeeTopic",emp);
}

public void produceBatch(Department d) {
	kafkaTemplate.send("departmentTopicLstBatch",d);
}

}
