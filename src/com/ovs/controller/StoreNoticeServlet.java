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
		Boolean flag=false;//若插入数据成功则为true
		
		//要有以下两句话PageContext才不会报错
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
			//要以另一种方式获得参数
			/*String title = new String(su.getRequest().getParameter("noticeTitle").getBytes("ISO-8859-1"),"gb2312");
			System.out.println(title);
			String contents = new String(su.getRequest().getParameter("noticeContents").getBytes("ISO-8859-1"), "gb2312");
			System.out.println(contents);*/
			
			String InfoTitle = new String(su.getRequest().getParameter("noticeTitle").getBytes("gb2312"),"gb2312");
			System.out.println(InfoTitle);
			String InfoContent = new String(su.getRequest().getParameter("noticeContents").getBytes("gb2312"), "gb2312");
			System.out.println(InfoContent);
			
			
			System.out.println("成功获得公告标题和内容");
			com.jspsmart.upload.File file = su.getFiles().getFile(su.getFiles().getCount()-1);
			System.out.println("得到上传文件");
			
			if(file.isMissing()){
				/**
		    	 * 没有附件开始，attachmentAddress为空，写入数据库
		    	 */
		    	String insertSql = "insert into Info( InfoTitle, AdminID, InfoContent)" +
		    			" values ('"+InfoTitle+"','"+userID+"','"+InfoContent+"') ";
		    	
//		    	log.info("插入评论数据的SQL语句为————"+insertSql);
		    	System.out.println("插入通知的SQL语句为------"+insertSql);
		    	
		    	//int SQLResult = DBInsert.insert(insertSql);//向数据库插入数据
		    	//System.out.println("插入的通知条数为：   "+SQLResult);
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
				String dir="Info";//上传项目所在的文件夹
				
				java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
				java.util.Date currentTime = new java.util.Date();//得到当前系统时间
				String str_date1 = formatter.format(currentTime); //将日期时间格式化 
				
				String attachmentAddress=dir+"/"+str_date1+"."+file.getFileExt();
				file.saveAs(attachmentAddress);
				System.out.println("上传到"+attachmentAddress+"成功");
				
				/**
		    	 * 开始写入数据库
		    	 */
		    	String insertSql = "insert into Info( InfoTitle, AdminID, InfoContent,attachmentAddress)" +
		    			" values ('"+InfoTitle+"','"+userID+"','"+InfoContent+"','"+attachmentAddress+"') ";
		    	
		    	//System.out.println("插入通知的SQL语句为------"+insertSql);		    	
		    	//int SQLResult = DBInsert.insert(insertSql);//向数据库插入数据
		    	
		    	
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
