package com.ace.domain;

import java.io.Serializable;
import java.util.List;

public class QueryConfigReq implements Serializable {
	private static final long serialVersionUID = 1L;
	private String sessionId;
	private String attrCode;
	//查询多个枚举值时的key值
	private List codeList;

	public List getCodeList() {
		return codeList;
	}

	public void setCodeList(List codeList) {
		this.codeList = codeList;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getAttrCode() {
		return attrCode;
	}

	public void setAttrCode(String attrCode) {
		this.attrCode = attrCode;
	}

}
