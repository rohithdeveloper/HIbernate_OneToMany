package com.example.demo;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;



@SpringBootApplication
public class HibernateMapping2Application {

	public static void main(String[] args) {
		Configuration configuration=new Configuration();
		configuration.configure("hibernate.cfg.xml");
		SessionFactory sessionFactory=configuration.buildSessionFactory();
		Session session= sessionFactory.openSession();
		Transaction transaction=session.beginTransaction(); 
		
		
		Department department = new Department();
		department.setName("HR");
		
		Employee employee1 = new Employee();
		employee1.setName("John");
		employee1.setDepartment(department);
		
		Employee employee2 = new Employee();
		employee2.setName("Alice");
		employee2.setDepartment(department);
		
		department.setEmployees(Arrays.asList(employee1,employee2));
		session.save(department);
		

		Department department2 = new Department();
		department2.setName("Consultant");
		
		Employee employee3 = new Employee();
		employee3.setName("Rohith");
		employee3.setDepartment(department2);
	
		Employee employee4 = new Employee();
		employee4.setName("Shayam");
		employee4.setDepartment(department2);
		
		department2.setEmployees(Arrays.asList(employee3,employee4));
		session.save(department2);
		
		transaction.commit();	
		session.close();
		
		System.out.println("-----------------------------------");
		
		
		session=sessionFactory.openSession();
		transaction=session.beginTransaction();
		Query<Employee> query = session.createQuery("FROM Employee", Employee.class);
		List<Employee> employees = query.list();
		for(Employee emp: employees) {
			
				System.out.println(emp.getName());
				
		
		}
		transaction.commit();
		session.close();
		
		
		System.out.println("-----------------------------------");
		
		
		session=sessionFactory.openSession();
		transaction=session.beginTransaction();
		Query<Department> query1=session.createQuery("From Department", Department.class);
		List<Department> departments=query1.list();
		for(Department emp: departments) {
			
				System.out.println(emp.getName());
				
		
		}
		transaction.commit();
		session.close();
		
		System.out.println("---------------------------");
		session=sessionFactory.openSession();
		transaction=session.beginTransaction();
		Query<Employee> query3 = session.createQuery("FROM Employee e where e.name='Rohith'", Employee.class);
		List<Employee> employees2 = query3.list();
		for(Employee emp: employees2) {
			
				System.out.println(emp.getName());
				
		
		}
		transaction.commit();
		session.close();
		
		System.out.println("-------------------------------");
		
		session=sessionFactory.openSession();
		transaction=session.beginTransaction();
		int departmentId1 = 2;
		Query<Employee> query4=session.createQuery("FROM Employee e WHERE e.department.id = :departmentId", Employee.class);
		query4.setParameter("departmentId", departmentId1);
		List<Employee> employees3=query4.list();
		for(Employee emp:employees3) {
			System.out.println(emp.getId()+" "+emp.getName()+" "+department2.getName());
		}
		transaction.commit();
		session.close();
		
		System.out.println("----------------------------------");
		
		
		session=sessionFactory.openSession();
		transaction=session.beginTransaction();
		String departmentName = "HR";
		Query<Employee> query5=session.createQuery("FROM Employee e WHERE e.department.name = :departmentName", Employee.class);
		query5.setParameter("departmentName", departmentName);
		List<Employee> employees4=query5.list();
		for(Employee emp:employees4) {
			System.out.println(emp.getId()+" "+emp.getName());
		}
		
		transaction.commit();
		session.close();
		
		System.out.println("-----------------------------------");
		
		session=sessionFactory.openSession();
		transaction=session.beginTransaction();
		int departmentId2 = 1;
		Query<Employee> query6=session.createQuery("FROM Employee e WHERE e.department.id = :departmentId", Employee.class);
		query6.setParameter("departmentId", departmentId2);
		List<Employee> employees5=query6.list();
		for(Employee emp:employees4) {
			System.out.println(emp.getId()+" "+emp.getName()+" "+department.getName());
		}
		
		transaction.commit();
		session.close();
		
		
		System.out.println("----------------------------------------");
		
		
	}

}
