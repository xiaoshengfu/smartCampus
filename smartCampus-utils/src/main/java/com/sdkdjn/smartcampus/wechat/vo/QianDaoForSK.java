package com.sdkdjn.smartcampus.wechat.vo;

public class QianDaoForSK {
	private String qdfsk_kc;
	private String[] qdfsk_bj;
	private Integer qdfsk_time;
	private String qdfsk_ms;
	
	public QianDaoForSK() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param qdfsk_kc
	 * @param qdfsk_bj
	 * @param qdfsk_time
	 * @param qdfsk_ms
	 */
	public QianDaoForSK(String qdfsk_kc, String[] qdfsk_bj, Integer qdfsk_time, String qdfsk_ms) {
		super();
		this.qdfsk_kc = qdfsk_kc;
		this.qdfsk_bj = qdfsk_bj;
		this.qdfsk_time = qdfsk_time;
		this.qdfsk_ms = qdfsk_ms;
	}

	public String getQdfsk_kc() {
		return qdfsk_kc;
	}

	public void setQdfsk_kc(String qdfsk_kc) {
		this.qdfsk_kc = qdfsk_kc;
	}

	public String[] getQdfsk_bj() {
		return qdfsk_bj;
	}

	public void setQdfsk_bj(String[] qdfsk_bj) {
		this.qdfsk_bj = qdfsk_bj;
	}

	public Integer getQdfsk_time() {
		return qdfsk_time;
	}

	public void setQdfsk_time(Integer qdfsk_time) {
		this.qdfsk_time = qdfsk_time;
	}

	public String getQdfsk_ms() {
		return qdfsk_ms;
	}

	public void setQdfsk_ms(String qdfsk_ms) {
		this.qdfsk_ms = qdfsk_ms;
	}
	
}
