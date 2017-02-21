package com.ovs.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ovs.db.JDBC_Connection;

public class ChangeAdminPWDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeAdminPWDServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		String AdminID = request.getParameter("AdminID");
		String newPWD1 = request.getParameter("newPWD1");
		String oldPWD = request.getParameter("oldPWD");
		String checkOldPWD = request.getParameter("checkOldPWD");
			
		String UpdateSQL = "update login set pwd="+newPWD1+" where ID="+AdminID+";";
		String url="/OVS/admin/admin_info_editPWD.jsp";
		try {
			if(oldPWD.equals(checkOldPWD)){			
				conn = JDBC_Connection.getConnection();
			    stmt = conn.createStatement();
			    stmt.execute(UpdateSQL);
			    System.out.println("修改成功！！！");	
			    url="/OVS/admin/admin_index.jsp";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("修改失败！！！");			
		} finally {
			JDBC_Connection.free(rs, conn, stmt);
			response.sendRedirect(url);
		}
	}

}
