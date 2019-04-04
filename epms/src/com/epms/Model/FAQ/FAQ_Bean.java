package com.epms.Model.FAQ;

public class FAQ_Bean {
	/*
	create table board_faq(
		faq_no number(7) not null primary key,
		faq_id varchar2(20) not null,
		faq_name varchar2(20) not null,
		faq_title varchar2(20) not null,
		faq_content varchar2(100) not null,
		faq_count number(7) default 0,
		faq_date date not null,
		faq_level number,
		faq_parent number,
		faq_group number,
		faq_re_seq number	
	);
	commit;
	*/
	private int rn;
	private int no;				//글 번호 -pk
	private String id;			//글 작성자 id
	private String name;		//글작성자 이름

	private String title;		//글 제목
	private String content;		//글 내용
	private int count;			//글 조회수
	private java.sql.Date date; //글 작성날짜
	
	private int level;			//답변글 깊이
	private int parent;			//부모글번호
	
	//추가부분
	private int group;			//글 그룹번호
	private int re_seq;			//답변글 순서
	
	
	
	public int getRn() {
		return rn;
	}
	public void setRn(int rn) {
		this.rn = rn;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public java.sql.Date getDate() {
		return date;
	}
	public void setDate(java.sql.Date date) {
		this.date = date;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public int getGroup() {
		return group;
	}
	public void setGroup(int group) {
		this.group = group;
	}
	public int getRe_seq() {
		return re_seq;
	}
	public void setRe_seq(int re_seq) {
		this.re_seq = re_seq;
	}
	
	

}
