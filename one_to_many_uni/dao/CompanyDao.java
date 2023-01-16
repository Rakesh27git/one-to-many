package one_to_many_uni.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import one_to_many_uni.dto.Employee;
import one_to_many_uni.dto.Company;

public class CompanyDao {
	
	
	public EntityManager getEntityManager()
	{
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("vinod");
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		
		return entityManager;
	}

	public void saveCompany(Company company)
	{
		EntityManager entityManager=getEntityManager();
		EntityTransaction entityTransaction=entityManager.getTransaction();
		
		entityTransaction.begin();
		List<Employee>list=company.getList();
		for(Employee employee:list) {
			
		entityManager.persist(employee);
		}
		entityTransaction.commit();
		
	}
	public void updateCompany(int id,Company company)
	{
		EntityManager entityManager=getEntityManager();
		EntityTransaction entityTransaction=entityManager.getTransaction();
		entityTransaction.begin();
		Company receivedCompany=entityManager.find(Company.class, id);
		if(receivedCompany!=null)
		{
			company.setId(id);
			company.setList(receivedCompany.getList());
		
		
			entityManager.merge(company);
			entityTransaction.commit();
		}else {
			System.out.println("Company does not exist");
		}
	}
	public void deleteCompany(int id)
	{
		EntityManager entityManager=getEntityManager();
		EntityTransaction entityTransaction=entityManager.getTransaction();
		entityTransaction.begin();
		Company company=entityManager.find(Company.class, id);
		if(company!=null)
		{
		
		entityManager.remove(company);
		entityManager.remove(company.getList());
		entityTransaction.commit();
		}
	}
	public void findById(int id)
	{
		EntityManager entityManager=getEntityManager();
		
		Company company=entityManager.find(Company.class, id);
		System.out.println(company);
		
	}
	public List<Employee> findAll()
	{
		EntityManager entityManager=getEntityManager();
		 Query query=entityManager.createQuery("select c from Company c");
		 List<Employee>list=query.getResultList();
		 return list;
		 
	}
	
	
		
		
	
	
}
