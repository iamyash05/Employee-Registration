package org.framework.EmployeeRegApp.Service;

import java.util.Random;


import org.framework.EmployeeRegApp.dao.EmployeeDao;
import org.framework.EmployeeRegApp.dto.Employee;

public class EmployeeService 
{
	public Employee registerEmployee(Employee emp)
	{
		System.out.println("Inside Service");
		if(emp!=null)
		{
			Random ran = new Random();
			int id = ran.nextInt();
			String emp_id = "XXX-"+id;
			emp.setEmp_id(emp_id);
		}
		System.out.println("Pass emp object from empserv to employeedao");
		EmployeeDao dao = new EmployeeDao();
		Employee retemp = dao.saveEmployee(emp);
		System.out.println("Return emp object from empdao to empserv");
		return retemp;
	}
}
