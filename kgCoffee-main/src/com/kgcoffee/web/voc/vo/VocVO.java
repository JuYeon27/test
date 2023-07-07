package com.kgcoffee.web.voc.vo;

import java.util.Date;

public class VocVO {

	private int bunho;
	private String jemok;
	private String writer;
	private String content;
	private Date date;
	private int count;
	private String filename;
	private int ref;
	private int re_step;
	private int re_level;
	private String user_id;
	
	
	
	public VocVO() {}
	
	public VocVO(String writer) {
		this.writer = writer;
	}
	
	public VocVO(int bunho, String jemok, String writer, String content, Date date, int count, String filename, int ref,
			int re_step, int re_level,String user_id) {
		super();
		this.bunho = bunho;
		this.jemok = jemok;
		this.writer = writer;
		this.content = content;
		this.date = date;
		this.count = count;
		this.filename = filename;
		this.ref = ref;
		this.re_step = re_step;
		this.re_level = re_level;
		this.user_id = user_id;
	}
	
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getBunho() {
		return bunho;
	}
	public void setBunho(int bunho) {
		this.bunho = bunho;
	}
	public String getJemok() {
		return jemok;
	}
	public void setJemok(String jemok) {
		this.jemok = jemok;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getRe_step() {
		return re_step;
	}
	public void setRe_step(int re_step) {
		this.re_step = re_step;
	}
	public int getRe_level() {
		return re_level;
	}
	public void setRe_level(int re_level) {
		this.re_level = re_level;
	}
	
	

	
}
