package com.ovs.controller;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ovs.model.*;
import com.ovs.db.*;
/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    
	/**
	 * 用户登录信息与数据库中的信息匹配
	 * 
	 * @throws SQLException
	 *             -1 用户不存在 0 密码错误 -2未知错误   
	 */
	@SuppressWarnings("resource")
	private String checkLoginInfo(String userID,String pwd) throws SQLException{
		
		// SQL 语句
		String QuerySQL = "SELECT * FROM login WHERE ID ='"+ userID + "';";
		// 执行SQL
//		System.out.println("Login----------------------------------------\n"+QuerySQL);
				
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
//		String sql="select * from login";
		
		try{
			conn=JDBC_Connection.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(QuerySQL);
			
			if (rs == null) {
				return "-1";//用户不存在
			}
			
			if(rs!=null){
				while(rs.next()){
					String userPwd = rs.getString("pwd");
					String userRole = rs.getString("role");
					
					System.out.println("查找到的      密码    为："+userPwd);
					System.out.println("查找到的用户角色为："+userRole);
					
					System.out.println(pwd);
					if (pwd.equals(userPwd)) {
						System.out.println(userRole);
						return userRole;//返回角色
					}else{
						return "0";//密码错误
					}
	
				}

//				return "0";
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JDBC_Connection.free(rs, conn, stmt);
		}
		
//		log.info("根据用户名查询密码完毕");

/*		if (rs == null) {
			com.ovs.db.DBQuery.closeDB();
			return "-1";
		}

//		log.info("查询结果不为空");

		if (rs != null) {

//			log.info("执行到判断结果是否为空");

			// while (rs.next()) { // 判断是否还有下一个数据

//			log.info("执行到进行密码匹配判断");

			// 根据字段名获取相应的值
			String userPwd = rs.getString("pwd");
			String userRole = rs.getString("role");
			short locked = 0;

			if(userRole.equals("user")){
				QuerySQL = "select * from user where UserID = "+userID+";";
				System.out.println("Login----------------------------------------\n"+QuerySQL);
				rs = com.ovs.db.DBQuery.query(QuerySQL);
				locked = rs.getShort("locked");
			}else if(userRole.equals("tea")){
				QuerySQL = "select * from teacher where teacherID = "+userID+";";
				System.out.println("Login----------------------------------------\n"+QuerySQL);
				rs = com.ovs.db.DBQuery.query(QuerySQL);
				locked = rs.getShort("locked");
			}
			
			System.out.println("查找到的      密码    为："+userPwd);
			System.out.println("查找到的用户角色为："+userRole);
//			System.out.println("查找到的用户状态为："+locked);

//			log.info("数据库中查询到的密码为" + userPwd);
			if(locked == 1){
				System.out.println("用户已被封锁！！！");
				com.ovs.db.DBQuery.closeDB();
				return "-1";
			}
			if (pwd.equals(userPwd)) {

				com.ovs.db.DBQuery.closeDB();
				return userRole;
			}

			com.ovs.db.DBQuery.closeDB();
			return "0" ;
		}*/
		return "-2";//未知错误
	}
    
    
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setCharacterEncoding("utf-8");// 将回复转换为中文字符集
		request.setCharacterEncoding("utf-8");
		
		String userID = java.net.URLDecoder.decode(request
				.getParameter("userID"), "UTF-8");
		String password = java.net.URLDecoder.decode(request
				.getParameter("password"), "UTF-8");
		
//		System.out.println("请求的学号为："+request.getParameter("userID"));
		
//		String userID = null;
//		String password = null;
//		
//		userID = request.getParameter("userID");
//		password = request.getParameter("password");
		
		System.out.println("传入的用户名："+userID);
		System.out.println("传入的密     码："+password);
//		password = MD5.getMD5(password);
		System.out.println("转化后的密     码："+password);
		
		
		String checkResult = null;
		
//		String systemState = (String)this.getServletContext().getAttribute("state");
		

//		System.out.println("系统状态为systemState："+systemState);
		
		try {
			checkResult = checkLoginInfo(userID,password);
			System.out.println(checkResult);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(checkResult.equals("0")){           //密码错误
			System.out.println("密码错误！");
			response.sendRedirect("/OVS/login/login_error.jsp");
			return;
		}else if(checkResult.equals("-1")){
			System.out.println("用户不存在或已被封锁！");
			response.sendRedirect("/OVS/login/login_error.jsp");
			return;
		}else if(checkResult.length() >= 3){
			//if(checkResult.equals("user")&&"open".equals(systemState)){
				if(checkResult.equals("user")){	
				//跳转向老师的界面，暂时不需要实现   2013.11.3
				HttpSession session = request.getSession();
				session.setAttribute("userID", userID);
				session.setAttribute("userPwd", password);
				session.setAttribute("role", "user");

				response.sendRedirect("/OVS/user/user_index.jsp");//跳转向老师界面(默认为课程的主页)
				return;
				
				
			}/*else if(checkResult.equals("stu")&&"open".equals(systemState)){
				
				HttpSession session = request.getSession();
				session.setAttribute("userID", userID);
				session.setAttribute("userPwd", password);
				session.setAttribute("role", "stu");
//				RequestDispatcher rd = getServletContext().getRequestDispatcher("/student/stu_index_stu.jsp");
//				rd.forward(request, response);
				
				System.out.println("session中的的学号为："+session.getAttribute("userID").toString());
//				request.getRequestDispatcher("/student/stu_index_stu.jsp").forward(request, response);//转发
				
				response.sendRedirect("/CSIOSystem/student/stu_index.jsp");
				return;
				
			}*/else if(checkResult.equals("admin")){
				
				HttpSession session = request.getSession();
				session.setAttribute("userID", userID);
				session.setAttribute("userPwd", password);
				session.setAttribute("role", "admin");
				
				response.sendRedirect("/OVS/admin/admin_index.jsp");//跳转向管理员界面（跳转到一个有很多快捷操作的页面）
				return;
			}
			
		}else {
			response.sendRedirect("/OVS/login/login_error.jsp");
			return;
		}

		System.out.println("都  不  匹  配  直  接  到  了  结  尾");
		response.sendRedirect("/OVS/login/login_error.jsp");
		return;
	}
	
	

	
}