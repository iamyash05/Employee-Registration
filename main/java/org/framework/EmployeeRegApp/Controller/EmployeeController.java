package org.framework.EmployeeRegApp.Controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.framework.EmployeeRegApp.Service.EmployeeService;
import org.framework.EmployeeRegApp.dto.Employee;

@SuppressWarnings("serial")
public class EmployeeController extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		Employee emp = null;
		Employee retemp = null;
		//Data Validation//
		if(!req.getParameter("firstname").isEmpty() && !req.getParameter("lastname").isEmpty() && !req.getParameter("email").isEmpty() && !req.getParameter("password").isEmpty() && !req.getParameter("phoneno").isEmpty())
		{
			emp = new Employee();
			emp.setFirstName(req.getParameter("firstname"));
			emp.setLastName(req.getParameter("lastname"));
			emp.setPhoneno(req.getParameter("phoneno"));
			emp.setEmailID(req.getParameter("email"));
			emp.setPassword(req.getParameter("password"));
			if(emp.getPhoneno().length() == 10) {
				System.out.println("Send Emp Object from Controller to Service");
				EmployeeService empsrv = new EmployeeService();
				retemp = empsrv.registerEmployee(emp);
				System.out.println("Return emp object from service to controller");
			} else {
				emp = null;
				System.out.println("Invalid Data");
			}
		}
		else {
			emp = null;
			System.out.println("Invalid Data");
		}
		
		// Presentation Logic //
		RequestDispatcher dispatch = null;
		if(retemp != null)
		{
			req.setAttribute("UserName", retemp.getFirstName() + retemp.getLastName());
			dispatch = req.getRequestDispatcher("Success.jsp");
		}
		else {
			dispatch = req.getRequestDispatcher("Error.jsp");
		}
		dispatch.forward(req, resp);
	}
}
