package com.kdt.jdbc.board.domain;

public class PostResponseDTO {
	private Integer id;
	private String title, content, writer;
	private Integer view_cnt;
	private Integer notice_yn, delete_yn;
	private String created_date, modified_date;
	
	public PostResponseDTO() {
	}

	public PostResponseDTO(Integer id, String title, String content, String writer, Integer view_cnt, Integer notice_yn,
			Integer delete_yn, String created_date, String modified_date) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.view_cnt = view_cnt;
		this.notice_yn = notice_yn;
		this.delete_yn = delete_yn;
		this.created_date = created_date;
		this.modified_date = modified_date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Integer getView_cnt() {
		return view_cnt;
	}

	public void setView_cnt(Integer view_cnt) {
		this.view_cnt = view_cnt;
	}

	public Integer getNotice_yn() {
		return notice_yn;
	}

	public void setNotice_yn(Integer notice_yn) {
		this.notice_yn = notice_yn;
	}

	public Integer getDelete_yn() {
		return delete_yn;
	}

	public void setDelete_yn(Integer delete_yn) {
		this.delete_yn = delete_yn;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public String getModified_date() {
		return modified_date;
	}

	public void setModified_date(String modified_date) {
		this.modified_date = modified_date;
	}

	@Override
	public String toString() {
		return "PostResponseDTO [id=" + id + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", view_cnt=" + view_cnt + ", notice_yn=" + notice_yn + ", delete_yn=" + delete_yn + ", created_date="
				+ created_date + ", modified_date=" + modified_date + "]";
	}
	
	
}
