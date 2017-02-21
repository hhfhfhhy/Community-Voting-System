package com.ovs.model;

import java.util.Date;

public class Vote {
	
	private String VoteID = null;
	private String AdminID = null;
	private Date Start = null;
	private Date End = null;
	private String Status = null;
	private String VoteContent = null;
	private String VoteName = null;
	
	public Vote(){}
	
	public Vote(String VoteID, String AdminID ,
			String VoteContent,String VoteName, Date Start, Date End, String Status) {
		super();
		this.VoteID=VoteID;
		this.AdminID=AdminID;
		this.Start=Start;
		this.End=End;
		this.Status=Status;
		this.VoteContent=VoteContent;
		this.VoteName=VoteName;
	}
	public String getVoteID() {
		return VoteID;
	}
	public void setVoteID(String VoteID) {
		this.VoteID = VoteID;
	}
	public String getVoteName() {
		return VoteName;
	}
	public void setVoteName(String VoteName) {
		this.VoteName = VoteName;
	}
	public String getAdminID() {
		return AdminID;
	}
	public void setAdminID(String AdminID) {
		this.AdminID = AdminID;
	}
	public Date getStart() {
		return Start;
	}
	public void setStart(Date Start) {
		this.Start = Start;
	}
	public Date getEnd() {
		return End;
	}
	public void setEnd(Date End) {
		this.End = End;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String Status) {
		this.Status = Status;
	}
	public String getVoteContent() {
		return VoteContent;
	}
	public void setVoteContent(String VoteContent) {
		this.VoteContent = VoteContent;
	}
	

}
