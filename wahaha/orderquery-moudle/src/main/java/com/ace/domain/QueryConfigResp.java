package com.ace.domain;

import com.ace.entity.BaseResp;

public class QueryConfigResp extends BaseResp {
	private String attrValue;

	private String parentAttrValue;
	private String remark;
	private String atteValueName;

	public String getAtteValueName() {
		return atteValueName;
	}

	public void setAtteValueName(String atteValueName) {
		this.atteValueName = atteValueName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	public String getParentAttrValue() {
		return parentAttrValue;
	}

	public void setParentAttrValue(String parentAttrValue) {
		this.parentAttrValue = parentAttrValue;
	}



}
