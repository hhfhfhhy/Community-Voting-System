package com.ovs.controller;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import com.ovs.db.*;
import com.jspsmart.upload.SmartUpload;

/**
 * Servlet implementation class StoreNoticeServlet
 */
public class StoreNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreNoticeServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		System.out.println("StoreNoticeServlet------------------------------------------\n");
		
		/*String title = new String(request.getParameter("noticeTitle").getBytes("ISO-8859-1"),"UTF-8");
		String contents = new String(request.getParameter("noticeContents").getBytes("ISO-8859-1"), "UTF-8");*/
		
		String userID = request.getSession().getAttribute("userID").toString();
		System.out.println(userID);
		
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		Boolean flag=false;//���������ݳɹ���Ϊtrue
		
		//Ҫ���������仰PageContext�Ų��ᱨ��
		JspFactory fac=JspFactory.getDefaultFactory();
		PageContext pageContext=fac.getPageContext(this, request,response, null, false, JspWriter.DEFAULT_BUFFER, true);
		try{
			SmartUpload su = new SmartUpload();
			su.initialize(pageContext);
			su.setMaxFileSize(10 * 1024 * 1024);
			su.setTotalMaxFileSize(20 * 1024 * 1024);
			su.setAllowedFilesList("doc");
			su.setDeniedFilesList("exe,bat,jsp,htm,html,txt,,");
			su.upload();
			//Ҫ����һ�ַ�ʽ��ò���
			/*String title = new String(su.getRequest().getParameter("noticeTitle").getBytes("ISO-8859-1"),"gb2312");
			System.out.println(title);
			String contents = new String(su.getRequest().getParameter("noticeContents").getBytes("ISO-8859-1"), "gb2312");
			System.out.println(contents);*/
			
			String InfoTitle = new String(su.getRequest().getParameter("noticeTitle").getBytes("gb2312"),"gb2312");
			System.out.println(InfoTitle);
			String InfoContent = new String(su.getRequest().getParameter("noticeContents").getBytes("gb2312"), "gb2312");
			System.out.println(InfoContent);
			
			
			System.out.println("�ɹ���ù�����������");
			com.jspsmart.upload.File file = su.getFiles().getFile(su.getFiles().getCount()-1);
			System.out.println("�õ��ϴ��ļ�");
			
			if(file.isMissing()){
				/**
		    	 * û�и�����ʼ��attachmentAddressΪ�գ�д�����ݿ�
		    	 */
		    	String insertSql = "insert into Info( InfoTitle, AdminID, InfoContent)" +
		    			" values ('"+InfoTitle+"','"+userID+"','"+InfoContent+"') ";
		    	
//		    	log.info("�����������ݵ�SQL���Ϊ��������"+insertSql);
		    	System.out.println("����֪ͨ��SQL���Ϊ------"+insertSql);
		    	
		    	//int SQLResult = DBInsert.insert(insertSql);//�����ݿ��������
		    	//System.out.println("�����֪ͨ����Ϊ��   "+SQLResult);
		    	try{
		    		conn=JDBC_Connection.getConnection();
		    		stmt=conn.createStatement();
		    		flag=stmt.execute(insertSql);
		    	}catch(SQLException e){
		    		e.printStackTrace();
		    		System.out.println(flag);
		    	}finally{
		    		JDBC_Connection.free(rs, conn, stmt);
		    		response.sendRedirect("/OVS/admin/admin_notice_list.jsp");
		    	}
		    	
			}else{
				String dir="Info";//�ϴ���Ŀ���ڵ��ļ���
				
				java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
				java.util.Date currentTime = new java.util.Date();//�õ���ǰϵͳʱ��
				String str_date1 = formatter.format(currentTime); //������ʱ���ʽ�� 
				
				String attachmentAddress=dir+"/"+str_date1+"."+file.getFileExt();
				file.saveAs(attachmentAddress);
				System.out.println("�ϴ���"+attachmentAddress+"�ɹ�");
				
				/**
		    	 * ��ʼд�����ݿ�
		    	 */
		    	String insertSql = "insert into Info( InfoTitle, AdminID, InfoContent,attachmentAddress)" +
		    			" values ('"+InfoTitle+"','"+userID+"','"+InfoContent+"','"+attachmentAddress+"') ";
		    	
		    	//System.out.println("����֪ͨ��SQL���Ϊ------"+insertSql);		    	
		    	//int SQLResult = DBInsert.insert(insertSql);//�����ݿ��������
		    	
		    	
		    	try{
		    		conn=JDBC_Connection.getConnection();
		    		stmt=conn.createStatement();
		    		flag=stmt.execute(insertSql);
		    	}catch(SQLException e){
		    		e.printStackTrace();
		    		System.out.println(insertSql);
		    	}finally{
		    		JDBC_Connection.free(rs, conn, stmt);
		    		response.sendRedirect("/OVS/admin/admin_notice_list.jsp");
		    	}		    			    
			}
		}catch(Exception e){
			e.printStackTrace();
			response.sendRedirect("/OVS/admin/admin_notice_list.jsp");
		}	
	}

}
