package com.teamtreehouse.utils;

public class SLOException extends Exception {

	private SLErrorCode mSlErrCd;
	private Object[] mParams;
	private String mMsgTemplate;

	public SLOException(SLErrorCode slErrCd, String msgTemplate, Object... params) {
		super(String.format(msgTemplate, params));
		this.mSlErrCd = slErrCd;
		this.mParams = params;
		this.mMsgTemplate = msgTemplate;
	}

	public SLErrorCode getErrorCode() {
		return this.mSlErrCd;
	}

	public String getErrorMessage() {
		return String.format(
			"%s: %s - %s", "Error", this.mSlErrCd, this.getMessage());
	}

}