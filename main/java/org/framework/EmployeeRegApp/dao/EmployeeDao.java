package org.framework.EmployeeRegApp.dao;
import org.framework.EmployeeRegApp.dto.Employee;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class EmployeeDao 
{
	public Employee saveEmployee(Employee emp)
	{
		System.out.println("Inside DAO");
		int primaryKey=0;
		Transaction tran=null;
		try {
			if(emp!= null) {
				Configuration conf=new Configuration();
				conf.configure();
				@SuppressWarnings("deprecation")
				SessionFactory sef=conf.buildSessionFactory();
				Session ses=sef.openSession();
				tran=ses.beginTransaction();
				primaryKey=(int)ses.save(emp);
				tran.commit();
				ses.close();
			}
		}
		catch(HibernateException e) 
		{
			tran.rollback();
			System.out.println("Operation RollBacked!!!");
			return null;
		}
		if(primaryKey!= 0)
		{
			System.out.println("Return emp obj from db to empdao");
			return emp;
		}
		else {
			return null;
		}
	}
}
