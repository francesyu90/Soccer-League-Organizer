package com.teamtreehouse.utils;

public class SLOException extends Exception {

	private SLCode mSlCd;
	private Severity mSeversity;
	private Object[] mParams;
	private String mMsgTemplate;

	public SLOException(SLCode slCd, String msgTemplate, Object... params) {
		super(String.format(msgTemplate, params));
		this.mSlCd = slCd;
		this.mParams = params;
		this.mMsgTemplate = msgTemplate;
		this.mSeversity = Severity.Error;
	}

	public SLOException(SLCode slCd, Severity severity, String msgTemplate, Object... params) {
		super(String.format(msgTemplate, params));
		this.mSlCd = slCd;
		this.mSeversity = severity;
		this.mParams = params;
		this.mMsgTemplate = msgTemplate;
	}

	public SLCode getErrorCode() {
		return this.mSlCd;
	}

	public void setSeverity(Severity severity) {
		if(severity == null) {
			return;
		}
		this.mSeversity = severity;
	}

	public Severity getSeverity() {
		return this.mSeversity;
	}

	public String getErrorMessage() {
		return String.format(
			"%s: %s - %s", this.mSeversity, this.mSlCd, this.getMessage());
	}

}