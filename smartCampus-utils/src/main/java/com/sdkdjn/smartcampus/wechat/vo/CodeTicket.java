package com.sdkdjn.smartcampus.wechat.vo;

public class CodeTicket {
	private String ticket;
	private String expire_seconds;
	private String url;
	public CodeTicket() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param ticket
	 * @param expire_seconds
	 * @param url
	 */
	public CodeTicket(String ticket, String expire_seconds, String url) {
		super();
		this.ticket = ticket;
		this.expire_seconds = expire_seconds;
		this.url = url;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public String getExpire_seconds() {
		return expire_seconds;
	}
	public void setExpire_seconds(String expire_seconds) {
		this.expire_seconds = expire_seconds;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "CodeTicket [ticket=" + ticket + ", expire_seconds=" + expire_seconds + ", url=" + url + "]";
	}
	
}
