package one_to_many_uni.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import one_to_many_uni.dao.CompanyDao;
import one_to_many_uni.dto.Company;
import one_to_many_uni.dto.Employee;

public class CompanyMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		Company company=new Company();
		CompanyDao dao=new CompanyDao();
		boolean repeat=true;
		do {
		
		System.out.println("Enter your choice");
		System.out.println("1.Save Person Details");
		System.out.println("2.Update Person Details");
		System.out.println("3.Delete Person Details");
		System.out.println("4.Fetch Person Details by ID ");
		System.out.println("5.Fetch All Person Details");
		System.out.println("6.Exit");
		
		int choice =sc.nextInt();
		switch (choice) {
		case 1:{
			
			System.out.println("enter the company name");
			String comName=sc.next();
			System.out.println("Enter the GST");
			String gst=sc.next();
			
			company.setGst(gst);
			company.setName(comName);
			
			
			
			System.out.println("enter first employee details");
			System.out.println("enter the name");
			String name1=sc.next();
			System.out.println("enter the address");
			String add1=sc.next();
			System.out.println("Enter the phone number");
			long ph1=sc.nextLong();
			Employee e1=new Employee();
			e1.setName(name1);
			e1.setAddress(add1);
			e1.setPhone(ph1);
			
			
			System.out.println("enter Second employee details");
			System.out.println("enter the name");
			String name2=sc.next();
			System.out.println("enter the address");
			String add2=sc.next();
			System.out.println("Enter the phone number");
			long ph2=sc.nextLong();
			Employee e2=new Employee();
			e2.setName(name2);
			e2.setAddress(add2);
			e2.setPhone(ph2);
			
			
			
			System.out.println("enter third employee details");
			System.out.println("enter the name");
			String name3=sc.next();
			System.out.println("enter the address");
			String add3=sc.next();
			System.out.println("Enter the phone number");
			long ph3=sc.nextLong();
			Employee e3=new Employee();
			e1.setName(name3);
			e1.setAddress(add3);
			e1.setPhone(ph3);
			
			List<Employee>list=new ArrayList<Employee>();
			list.add(e1);
			list.add(e2);
			list.add(e3);
			
			
			EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("vinod");
			EntityManager entityManager=entityManagerFactory.createEntityManager();
			EntityTransaction entityTransaction=entityManager.getTransaction();
			
			
			
			company.setList(list);
			
			entityTransaction.begin();
			entityManager.persist(e1);
			entityManager.persist(e2);
			entityManager.persist(e3);
			entityManager.persist(company);
			
			entityTransaction.commit();
			
			
		}break;

		case 2:
		{
			System.out.println("enter the id to be updated");
			int id=sc.nextInt();
			company.setId(id);
			System.out.println("enter the name to be updated");
			String name=sc.next();
			company.setName(name);
			
			dao.updateCompany(id, company);
			
			
		}break;
		
		case 3:
		{
			System.out.println("enter the id to be deleted");
			int id=sc.nextInt();
			company.setId(id);
			dao.deleteCompany(id);
		}break;
		
		case 4:
		{
			System.out.println("enter the id to display");
			int id=sc.nextInt();
			dao.findById(id);
			
		}break;
		
		case 5:
		{
			List<Employee>list=dao.findAll();
			System.out.println(list);
		}break;
		
		case 6:
		{
			System.out.println("*****Thank You*****");
			repeat=false;
		}
		}

	}while(repeat);

}
}