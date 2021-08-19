package com.mph.dao;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mph.model.Employee;
import com.mph.model.Details;
import com.mph.util.DBConnection;

public class EmployeeDaoImpl implements EmployeeDao {

	List<Employee>  emplist ;
	Connection con;
	Statement stmt;
	PreparedStatement ps;
	ResultSet rs;
	Employee employee;
	Details det;
	@Override
	public List<Employee> getAllEmployee() {
		emplist = new ArrayList<Employee>();
		try {
			con = DBConnection.getDBConnection();
			System.out.println(con);
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from mphsemp");
			while (rs.next()) {
				employee = new Employee();
				employee.setEid(rs.getInt("eid"));
				employee.setEname(rs.getString("ename"));
				employee.setDept(rs.getString("dept"));
				emplist.add(employee);
				
			}
			System.out.println("From List Dao : " + emplist);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emplist;
	}

	@Override
	public Employee getEmployee(int eno) {
		Employee employee=new Employee();
		try {
			con = DBConnection.getDBConnection();
			
			stmt = con.createStatement();
			rs = stmt.executeQuery("select * from mphsemp where eid="+eno);
			while (rs.next()) {
				employee.setEid(rs.getInt("eid"));
				employee.setEname(rs.getString("ename"));
				employee.setDept(rs.getString("dept"));
		}
		}		
		catch(SQLException e) {
				e.printStackTrace();
			}
	
		return employee;
	}

	@Override
	public boolean save(Employee emp) {
		boolean flag =false;
		try {
			con = DBConnection.getDBConnection();
			ps = con.prepareStatement("insert into mphsemp(eid,ename,dept) values(?,?,?)");
			int eid = emp.getEid();
			String ename = emp.getEname();
			String dept = emp.getDept();
			ps.setInt(1, eid);
			ps.setString(2, ename);
			ps.setString(3, dept);
			int noofrows = ps.executeUpdate();
			System.out.println(noofrows + " inserted Successfully !!!");
			flag=true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean update(Employee emp) {
		
		boolean flag =false;
		try {
			con = DBConnection.getDBConnection();
			System.out.println(emp + "To be updated ");
			ps = con.prepareStatement("update mphsemp set ename='"+emp.getEname() +"' ,dept='"+ emp.getDept() + "' where eid=" + emp.getEid());
								
			int noofrows = ps.executeUpdate();
			
			if(noofrows>=1)
			{
			System.out.println(noofrows + " updated Successfully !!!");
			flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;

	}

	@Override
	public boolean delete(int eno) {
		
		boolean flag =false;
		try {
			con = DBConnection.getDBConnection();
			System.out.println(eno + "To be delete ");
			ps = con.prepareStatement("delete from mphsemp where eid= "+eno);
								
			int noofrows = ps.executeUpdate();
			
			if(noofrows>=1)
			{
			System.out.println(noofrows + " delete Successfully !!!");
			flag=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;

	}
	@Override
	public boolean validate(Details dt) {
		// TODO Auto-generated method stub
		boolean flag =false;
		con = DBConnection.getDBConnection();
		try {
			System.out.println("inside dao validate");
			stmt=con.createStatement();
			rs=stmt.executeQuery("select * from valid where eid="+dt.getEid());
			System.out.println(dt.getEid()+"---"+dt.getPass());
			if(rs.next()) {
				det = new Details();
				System.out.println("inside if dao validate");
				det.setEid(rs.getInt("eid")); 
				det.setPass(rs.getString("pass"));
				System.out.println(det.getPass()+"---"+dt.getPass());
				if(det.getPass().equals(dt.getPass()))
				flag = true;
				
			}
			


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(flag);
		return flag;
	}

	@Override
	public boolean newUser(Details dt) {
		boolean flag = false;
		con = DBConnection.getDBConnection();
		try {
			ps=con.prepareStatement("insert into valid values (?,?,?,?)");
			int eid = dt.getEid();
			String ename =dt.getEname();
			String pass = dt.getPass();
			long phone = dt.getPhone_no();
			
			ps.setInt(1, eid);
			ps.setString(2, ename);
			ps.setString(3, pass);
			ps.setLong(4, phone);
			int no_of_rows = ps.executeUpdate();
			System.out.println("No of Rows Added : "+ no_of_rows);
			flag = true;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}

}

	/*@Override
	public boolean search(int eno) {
		boolean flag=false;
		
		try {
			con=MyDBConnection.getDBConnection();
			System.out.println(eno +" To be searched");
			ps=con.prepareStatement("select * from mphsemp where eid="+eno);
			
			int noofrows=ps.executeUpdate();
			if(noofrows>=1)
			{
				System.out.println(noofrows+" searched successfully.");
				flag=true;
			}
			
				
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;

	}

	*/

	
