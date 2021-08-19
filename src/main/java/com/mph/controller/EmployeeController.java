package com.mph.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mph.dao.EmployeeDao;
import com.mph.dao.EmployeeDaoImpl;
import com.mph.model.Employee;

@WebServlet(urlPatterns="/EmployeeController")
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	EmployeeDao dao = new EmployeeDaoImpl();
	RequestDispatcher rd = null;

	public EmployeeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>EMS</title>");
		out.println("</head>");
		out.println("<body>");

		String action = request.getParameter("action");
		switch (action) {
		case "LIST": {
			listEmployee(request, response);
			break;
		}
		case "EDIT": {
			editEmployee(request, response);
			break;
		}
		case "UPDATE": {
			updateEmployee(request, response);
			break;
		}
		case "DELETE": {
			deleteEmployee(request, response);
			break;
		}
		case "SEARCH":
		{
			searchEmployee(request, response);
			break;
		}
		default:
			break;
		}

		out.println("</body>");
		out.println("</html>");

	}

	private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Employee editedemp = new Employee();
		editedemp.setEid(Integer.parseInt(request.getParameter("txteid")));
		editedemp.setEname(request.getParameter("txtename"));
		editedemp.setDept(request.getParameter("txtdept"));

		if (dao.update(editedemp)) {
			request.setAttribute("NOTIFICATION", "Employee Updated Successfully :) ");
			listEmployee(request, response);
		}
	}

	private void editEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		rd = request.getRequestDispatcher("employee-edit.jsp");
		rd.forward(request, response);

	}

	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int eid = Integer.parseInt(request.getParameter("eid"));
		if (dao.delete(eid)) {
			request.setAttribute("NOTIFICATION", "Employee" + eid +" deleted Successfully :) ");
			listEmployee(request, response);
		}
	}
	private void searchEmployee(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int eid=Integer.parseInt(request.getParameter("empsearch"));
		
			request.setAttribute("NOTIFICATION", "Employee "+eid+" Found Successfully.");
			findEmployee(request, response);

		
	}
	
	

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Employee newemp = new Employee();
		newemp.setEid(Integer.parseInt(request.getParameter("txteid")));
		newemp.setEname(request.getParameter("txtename"));
		newemp.setDept(request.getParameter("txtdept"));

		if (dao.save(newemp)) {
			request.setAttribute("NOTIFICATION", "Employee Added Successfully :) ");
			listEmployee(request, response);
		}

	}

	protected void listEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Employee> emplist = dao.getAllEmployee();
		System.out.println("emplist from controller : " + emplist);
		request.setAttribute("allemp", emplist);
		rd = request.getRequestDispatcher("employee-list.jsp");
		rd.forward(request, response);
	}
	protected void findEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int eid = Integer.parseInt(request.getParameter("empsearch"));
		Employee emp=dao.getEmployee(eid);
		if(emp!=null) {
		List <Employee> emplist=new ArrayList<Employee>();
		emplist.add(emp);
		System.out.println("emplist from controller : " + emplist);
		request.setAttribute("allemp", emplist);
		rd = request.getRequestDispatcher("employee-list.jsp");
		rd.forward(request, response);
	}

	}
}
