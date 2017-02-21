package com.ovs.model;

import java.util.Date;

public class Info {
	
	public static final int PAGE_SIZE = 10;
	private double InfoID = 0;
	private String AdminID = null;
	private String InfoContent = null;
	private String InfoTitle = null;
	private Date InfoTime ;
	private String attachmentAddress = null;
	
	public Info(){}
	public Info(String InfoTitle, double InfoID, String AdminID,
			Date InfoTime,String attachmentAddress,
			String InfoContent) {
		super();
		this.InfoTitle = InfoTitle;
		this.InfoID = InfoID;
		this.AdminID = AdminID;
		this.InfoTime = InfoTime;
		this.attachmentAddress = attachmentAddress;
		this.InfoContent = InfoContent;
	}
	public String getInfoTitle() {
		return InfoTitle;
	}
	public void setInfoTitle(String InfoTitle) {
		this.InfoTitle = InfoTitle;
	}
	public double getInfoID() {
		return InfoID;
	}
	public void setInfoID(double InfoID) {
		this.InfoID = InfoID;
	}
	public String getAdminID() {
		return AdminID;
	}
	public void setAdminID(String AdminID) {
		this.AdminID = AdminID;
	}
	public Date getInfoTime() {
		return InfoTime;
	}
	public void setInfoTime(Date InfoTime) {
		this.InfoTime = InfoTime;
	}
	public String getAttachmentAddress() {
		return attachmentAddress;
	}
	public void setAttachmentAddress(String attachmentAddress) {
		this.attachmentAddress = attachmentAddress;
	}
	public String getInfoContent() {
		return InfoContent;
	}
	public void setInfoContent(String InfoContent) {
		this.InfoContent = InfoContent;
	}
}
