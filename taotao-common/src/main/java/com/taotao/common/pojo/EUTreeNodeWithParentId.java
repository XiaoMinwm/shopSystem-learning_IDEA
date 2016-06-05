package com.taotao.common.pojo;

/**
 * 节点格式
 * @author Administrator
 *
 */

public class EUTreeNodeWithParentId {
	private long id;
	private String text;
	private String state;
	private long parentId;
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}
