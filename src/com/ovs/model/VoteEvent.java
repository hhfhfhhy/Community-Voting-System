package com.ovs.model;

public class VoteEvent {
	
	private String VoteID = null;
	private String OptionSequence = null;
	private String OptionContent = null;
	
	public VoteEvent(){}
	
	public VoteEvent(String VoteID, String OptionSequence,String OptionContent) {
		super();
		this.VoteID=VoteID;
		this.OptionSequence=OptionSequence;
		this.OptionContent=OptionContent;
	}
	public String getVoteID() {
		return VoteID;
	}
	public void setVoteID(String VoteID) {
		this.VoteID = VoteID;
	}
	public String getOptionSequence() {
		return OptionSequence;
	}
	public void setOptionSequence(String OptionSequence) {
		this.OptionSequence = OptionSequence;
	}
	public String getOptionContent() {
		return OptionContent;
	}
	public void setOptionContent(String OptionContent) {
		this.OptionContent = OptionContent;
	}

}
