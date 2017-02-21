package com.ovs.beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ovs.db.JDBC_Connection;
import com.ovs.model.*;

/*
 * @ author: adidas
 * 这是一个业务逻辑Bean 用来 创建一些查询投票事件数据的方法
 */
public class VoteInfoBean {
	String pageName = "VoteInfoBean：---------------------------------------------------------";

	public VoteInfoBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<VoteEvent> getVoteEvent(String VoteID) throws SQLException {
		ArrayList<VoteEvent> temp=new ArrayList<VoteEvent>();
		
		String QuerySQL = "SELECT * FROM voteevent WHERE VoteID ='" + VoteID+ "';";
		//System.out.println(pageName + "\ngetVoteEvent查找投票的SQL语句为：" + QuerySQL);
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			conn = JDBC_Connection.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(QuerySQL);
						
				while(rs.next()){
					VoteEvent ve=new VoteEvent();
					ve.setOptionSequence(rs.getString("OptionSequence").toString());
					//System.out.println(rs.getString("OptionSequence").toString());
					ve.setOptionContent(rs.getString("OptionContent").toString());
					//System.out.println(rs.getString("OptionContent").toString());
					ve.setVoteID(VoteID);
					//System.out.println(VoteID);
					temp.add(ve);					
				}
				return temp;
			
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally{
			JDBC_Connection.free(rs, conn, stmt);
		}
	}

	/*
	 * @ author:adidas 参数： 事件名VoteID 返回值：Vote
	 */
	public Vote getVote(String VoteID) throws SQLException {
		Vote temp = new Vote();

		// SQL 语句
		String QuerySQL = "SELECT * FROM createvote WHERE VoteID ='" + VoteID
				+ "';";

		System.out.println(pageName + "\ngetVote查找投票的SQL语句为：" + QuerySQL);
		// 执行SQL

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			conn = JDBC_Connection.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(QuerySQL);

			if (rs == null) {			
				return null;
			} else {
				rs.next();
				temp.setAdminID(rs.getString("AdminID").toString());
				temp.setVoteID(VoteID);
				temp.setStatus(rs.getString("Status").toString());
				temp.setVoteContent(rs.getString("VoteContent").toString());
				temp.setStart(rs.getDate("Start"));
				temp.setEnd(rs.getDate("End"));
				temp.setVoteName(rs.getString("VoteName"));

				System.out.println(pageName + "\ngetVote查找到的事件编号VoteID为："
						+ temp.getVoteID());
				return temp;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally{
			JDBC_Connection.free(rs, conn, stmt);
		}
	}

	/*
	 * @ author: adidas 得到createvote表中的所有课程
	 */
	public ArrayList<Vote> getallVoteFromcreatevote() throws SQLException {

		// SQL 语句
		String QuerySQL = "SELECT * FROM createvote order by Start desc";

		System.out.println(pageName
				+ "\n1:getallVoteFromcreatevote的查找所有课程编号的SQL语句为：" + QuerySQL);
		// 执行SQL
		ArrayList<Vote> allVote = new ArrayList<Vote>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			conn = JDBC_Connection.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(QuerySQL);

			if(rs!=null){
				rs.next();
				while (!rs.isAfterLast()) {
					Vote temp = new Vote(rs.getString("VoteID").toString(), rs
							.getString("AdminID").toString(),
							rs.getString("VoteContent").toString(),rs.getString("VoteName").toString(),
							rs.getDate("Start"),
							rs.getDate("End"), rs.getString("Status").toString());

					allVote.add(temp);
					rs.next();
				}
				return allVote;
			}else{
				System.out.println("无投票事件");
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally{
			JDBC_Connection.free(rs, conn, stmt);
		}
		
	}

	public ArrayList<Vote> searchVote(String identifier,String searchItem) throws SQLException{
		ArrayList<Vote> resultVote = new ArrayList<Vote>(); 
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		// SQL 语句
		String QuerySQL = null;
		
		System.out.println(pageName+"searchVote查找的标识为identifier：  "+identifier);
		System.out.println(pageName+"searchVote查找的信息参数为searchItem：  "+searchItem);
				
		if("byName".equals(identifier)){
			QuerySQL = "select * from createvote where VoteName like '%"+searchItem+"%' or VoteContent like '%"+
					searchItem+"%';";
		}else if("byID".equals(identifier)){
			QuerySQL = "select * from createvote where VoteID like '%"+searchItem+"%';";
		}
		
		// 执行SQL
		System.out.println("查询课程的SQL语句为：  "+QuerySQL);
		
		try{
			conn=JDBC_Connection.getConnection();
			stmt=conn.createStatement();
			rs=stmt.executeQuery(QuerySQL);
			
			while(rs.next()){
				Vote temp=new Vote();
				temp.setAdminID(rs.getString("AdminID").toString());
				temp.setVoteID(rs.getString("VoteID").toString());
				temp.setStart(rs.getDate("Start"));
				temp.setEnd(rs.getDate("End"));
				temp.setStatus(rs.getString("Status").toString());
				temp.setVoteContent(rs.getString("VoteContent").toString());
				temp.setVoteName(rs.getString("VoteName").toString());
				resultVote.add(temp);
			}
			return resultVote;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally{
			JDBC_Connection.free(rs, conn, stmt);
		}
		
	}

	/*
	 * @ author: adidas 由 发布事件的管理员编号，得到所有的事件
	 */
	public ArrayList<Vote> getVoteFromAdmin(String AdminID) throws SQLException {

		// SQL 语句
		String QuerySQL = "SELECT * FROM createvote WHERE AdminID ='" + AdminID
				+ "';";

		System.out.println(pageName
				+ "\n；管理员创建信息中 getVoteFromAdmin 的查找投票事件编号的SQL语句为：" + QuerySQL);

		// 执行SQL
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		
		try{
			conn = JDBC_Connection.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(QuerySQL);

			ArrayList<Vote> allVote = new ArrayList<Vote>();

			if (rs == null) {
				return null;
			}
			while (!rs.isAfterLast()) {
				Vote temp = new Vote(rs.getString("VoteID").toString(), rs
						.getString("AdminID").toString(),
						rs.getString("VoteContent").toString(),rs.getString("VoteName").toString(),
						rs.getDate("Start"),
						rs.getDate("End"), rs.getString("Status").toString());
				allVote.add(temp);
				rs.next();
			}
			System.out.println(pageName + "\n；getVoteFromAdmin得到的事件个数 为："
					+ allVote.size());
			return allVote;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}finally{
			JDBC_Connection.free(rs, conn, stmt);			
		}

	}
	
	
	

}
