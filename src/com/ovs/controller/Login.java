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
	 * �û���¼��Ϣ�����ݿ��е���Ϣƥ��
	 * 
	 * @throws SQLException
	 *             -1 �û������� 0 ������� -2δ֪����   
	 */
	@SuppressWarnings("resource")
	private String checkLoginInfo(String userID,String pwd) throws SQLException{
		
		// SQL ���
		String QuerySQL = "SELECT * FROM login WHERE ID ='"+ userID + "';";
		// ִ��SQL
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
				return "-1";//�û�������
			}
			
			if(rs!=null){
				while(rs.next()){
					String userPwd = rs.getString("pwd");
					String userRole = rs.getString("role");
					
					System.out.println("���ҵ���      ����    Ϊ��"+userPwd);
					System.out.println("���ҵ����û���ɫΪ��"+userRole);
					
					System.out.println(pwd);
					if (pwd.equals(userPwd)) {
						System.out.println(userRole);
						return userRole;//���ؽ�ɫ
					}else{
						return "0";//�������
					}
	
				}

//				return "0";
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JDBC_Connection.free(rs, conn, stmt);
		}
		
//		log.info("�����û�����ѯ�������");

/*		if (rs == null) {
			com.ovs.db.DBQuery.closeDB();
			return "-1";
		}

//		log.info("��ѯ�����Ϊ��");

		if (rs != null) {

//			log.info("ִ�е��жϽ���Ƿ�Ϊ��");

			// while (rs.next()) { // �ж��Ƿ�����һ������

//			log.info("ִ�е���������ƥ���ж�");

			// �����ֶ�����ȡ��Ӧ��ֵ
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
			
			System.out.println("���ҵ���      ����    Ϊ��"+userPwd);
			System.out.println("���ҵ����û���ɫΪ��"+userRole);
//			System.out.println("���ҵ����û�״̬Ϊ��"+locked);

//			log.info("���ݿ��в�ѯ��������Ϊ" + userPwd);
			if(locked == 1){
				System.out.println("�û��ѱ�����������");
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
		return "-2";//δ֪����
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
		
		response.setCharacterEncoding("utf-8");// ���ظ�ת��Ϊ�����ַ���
		request.setCharacterEncoding("utf-8");
		
		String userID = java.net.URLDecoder.decode(request
				.getParameter("userID"), "UTF-8");
		String password = java.net.URLDecoder.decode(request
				.getParameter("password"), "UTF-8");
		
//		System.out.println("�����ѧ��Ϊ��"+request.getParameter("userID"));
		
//		String userID = null;
//		String password = null;
//		
//		userID = request.getParameter("userID");
//		password = request.getParameter("password");
		
		System.out.println("������û�����"+userID);
		System.out.println("�������     �룺"+password);
//		password = MD5.getMD5(password);
		System.out.println("ת�������     �룺"+password);
		
		
		String checkResult = null;
		
//		String systemState = (String)this.getServletContext().getAttribute("state");
		

//		System.out.println("ϵͳ״̬ΪsystemState��"+systemState);
		
		try {
			checkResult = checkLoginInfo(userID,password);
			System.out.println(checkResult);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(checkResult.equals("0")){           //�������
			System.out.println("�������");
			response.sendRedirect("/OVS/login/login_error.jsp");
			return;
		}else if(checkResult.equals("-1")){
			System.out.println("�û������ڻ��ѱ�������");
			response.sendRedirect("/OVS/login/login_error.jsp");
			return;
		}else if(checkResult.length() >= 3){
			//if(checkResult.equals("user")&&"open".equals(systemState)){
				if(checkResult.equals("user")){	
				//��ת����ʦ�Ľ��棬��ʱ����Ҫʵ��   2013.11.3
				HttpSession session = request.getSession();
				session.setAttribute("userID", userID);
				session.setAttribute("userPwd", password);
				session.setAttribute("role", "user");

				response.sendRedirect("/OVS/user/user_index.jsp");//��ת����ʦ����(Ĭ��Ϊ�γ̵���ҳ)
				return;
				
				
			}/*else if(checkResult.equals("stu")&&"open".equals(systemState)){
				
				HttpSession session = request.getSession();
				session.setAttribute("userID", userID);
				session.setAttribute("userPwd", password);
				session.setAttribute("role", "stu");
//				RequestDispatcher rd = getServletContext().getRequestDispatcher("/student/stu_index_stu.jsp");
//				rd.forward(request, response);
				
				System.out.println("session�еĵ�ѧ��Ϊ��"+session.getAttribute("userID").toString());
//				request.getRequestDispatcher("/student/stu_index_stu.jsp").forward(request, response);//ת��
				
				response.sendRedirect("/CSIOSystem/student/stu_index.jsp");
				return;
				
			}*/else if(checkResult.equals("admin")){
				
				HttpSession session = request.getSession();
				session.setAttribute("userID", userID);
				session.setAttribute("userPwd", password);
				session.setAttribute("role", "admin");
				
				response.sendRedirect("/OVS/admin/admin_index.jsp");//��ת�����Ա���棨��ת��һ���кܶ��ݲ�����ҳ�棩
				return;
			}
			
		}else {
			response.sendRedirect("/OVS/login/login_error.jsp");
			return;
		}

		System.out.println("��  ��  ƥ  ��  ֱ  ��  ��  ��  ��  β");
		response.sendRedirect("/OVS/login/login_error.jsp");
		return;
	}
	
	

	
}