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
 * ����һ��ҵ���߼�Bean ���� ����һЩ��ѯͶƱ�¼����ݵķ���
 */
public class VoteInfoBean {
	String pageName = "VoteInfoBean��---------------------------------------------------------";

	public VoteInfoBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<VoteEvent> getVoteEvent(String VoteID) throws SQLException {
		ArrayList<VoteEvent> temp=new ArrayList<VoteEvent>();
		
		String QuerySQL = "SELECT * FROM voteevent WHERE VoteID ='" + VoteID+ "';";
		//System.out.println(pageName + "\ngetVoteEvent����ͶƱ��SQL���Ϊ��" + QuerySQL);
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
	 * @ author:adidas ������ �¼���VoteID ����ֵ��Vote
	 */
	public Vote getVote(String VoteID) throws SQLException {
		Vote temp = new Vote();

		// SQL ���
		String QuerySQL = "SELECT * FROM createvote WHERE VoteID ='" + VoteID
				+ "';";

		System.out.println(pageName + "\ngetVote����ͶƱ��SQL���Ϊ��" + QuerySQL);
		// ִ��SQL

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

				System.out.println(pageName + "\ngetVote���ҵ����¼����VoteIDΪ��"
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
	 * @ author: adidas �õ�createvote���е����пγ�
	 */
	public ArrayList<Vote> getallVoteFromcreatevote() throws SQLException {

		// SQL ���
		String QuerySQL = "SELECT * FROM createvote order by Start desc";

		System.out.println(pageName
				+ "\n1:getallVoteFromcreatevote�Ĳ������пγ̱�ŵ�SQL���Ϊ��" + QuerySQL);
		// ִ��SQL
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
				System.out.println("��ͶƱ�¼�");
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
		// SQL ���
		String QuerySQL = null;
		
		System.out.println(pageName+"searchVote���ҵı�ʶΪidentifier��  "+identifier);
		System.out.println(pageName+"searchVote���ҵ���Ϣ����ΪsearchItem��  "+searchItem);
				
		if("byName".equals(identifier)){
			QuerySQL = "select * from createvote where VoteName like '%"+searchItem+"%' or VoteContent like '%"+
					searchItem+"%';";
		}else if("byID".equals(identifier)){
			QuerySQL = "select * from createvote where VoteID like '%"+searchItem+"%';";
		}
		
		// ִ��SQL
		System.out.println("��ѯ�γ̵�SQL���Ϊ��  "+QuerySQL);
		
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
	 * @ author: adidas �� �����¼��Ĺ���Ա��ţ��õ����е��¼�
	 */
	public ArrayList<Vote> getVoteFromAdmin(String AdminID) throws SQLException {

		// SQL ���
		String QuerySQL = "SELECT * FROM createvote WHERE AdminID ='" + AdminID
				+ "';";

		System.out.println(pageName
				+ "\n������Ա������Ϣ�� getVoteFromAdmin �Ĳ���ͶƱ�¼���ŵ�SQL���Ϊ��" + QuerySQL);

		// ִ��SQL
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
			System.out.println(pageName + "\n��getVoteFromAdmin�õ����¼����� Ϊ��"
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
