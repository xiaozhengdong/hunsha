package org.guili.ecshop.bean;

import java.io.Serializable;

import org.apache.http.HttpStatus;

public  class ResultBean implements Serializable {
    private static final long serialVersionUID = -4258041557869765737L;

    private int    code = HttpStatus.SC_OK;

    private String msg;

    private Object data;

    public ResultBean() {
    }

    public ResultBean(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
