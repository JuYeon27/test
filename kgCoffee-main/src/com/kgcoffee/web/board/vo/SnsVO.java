package com.kgcoffee.web.board.vo;

import java.util.Date;

public class SnsVO {

	private int bunho;
	private String jemok;
	private String writer;
	private String content;
	private Date date;
	private int count;
	@Override
	public String toString() {
		return "SnsVO [bunho=" + bunho + ", jemok=" + jemok + ", writer=" + writer + ", content=" + content + ", date="
				+ date + ", count=" + count + ", filename=" + filename + "]";
	}

	private String filename;
	
	
	public SnsVO(int bunho, String jemok, String writer, String content, Date date, int count, String filename) {
		/* super(); */
		this.bunho = bunho;
		this.jemok = jemok;
		this.writer = writer;
		this.content = content;
		this.date = date;
		this.count = count;
		this.filename = filename;
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
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
