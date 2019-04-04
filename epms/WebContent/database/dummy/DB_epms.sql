

-- ※ sql문을 실행하기 전에 읽어볼것

-- 1. DB char type에 맞춰서 입력해야 오류가 안남

-- 2. OracleDB의 경우 sql문 파일이 ansi로 입력해야 정상처리됨 (UTF-8 : 한글깨짐)

-- 3. 이 파일의 경우 UTF-8로 설정되어있으므로 (이클립스에서 한글출력을 볼수있게)

--    업데이트와 주석을 이 DB_epms.sql 파일에다 처리한후에

--    text 메모장등으로 이 파일을 열어 파일인코딩을 ansi로 변경해서

--    DB_empsAnsi.sql에 덮어씌우고

--    [ @ 파일경로/DB_empsAnsi.sql ] 로 sql 콘솔에서 실행할것을 추천함  


-- 계정정보 관련 -----------------------------------------

-- 사용자 계정정보
	-- 제약조건(외래키) 무시 삭제
drop table account cascade CONSTRAINTS;
create table account(
	ac_id varchar2(50) not null,  --크기수정
	ac_pw varchar2(50) not null,  --크기수정
	ac_name varchar2(50) not null, --크기수정
	ac_phone varchar2(20) not null,
	ac_addr1 varchar2(200) not null, --크기수정
	ac_addr2 varchar2(200) not null, --크기수정
	ac_email varchar2(40) not null, --크기수정
	ac_date date not null,
	ac_type varchar2(10) null,
	ac_point number default 0,
	ac_area varchar2(40) null,	--크기수정
	primary key(ac_id)
);
commit;

-- 관리자 계정정보
drop table admin;
create table admin(
	ad_id varchar2(20) not null,
	ad_pw varchar2(20) not null,
	primary key(ad_id)
);
commit;

-- 로그인 정보
drop table login;
create table login(
	lg_id varchar2(20),
	lg_name varchar2(20),
	primary key(lg_id)
);
commit;





-- 메신저 관련 ---------------------------------------

-- 메신저 시퀸스
drop sequence  mss_seq;
create sequence mss_seq
 start with 1
 increment by 1;
commit;

-- 메신저 테이블
drop table messenger;
create table messenger(
	mss_no number(7) not null, 
	mss_receiver varchar2(20) not null,
	mss_sender varchar2(20) not null,
	mss_content varchar2(40) null,
	mss_date date not null,
	primary key(mss_no)
);
commit;

-- 공지 시퀸스 
drop sequence  nofi_seq;
create sequence nofi_seq
 start with 1
 increment by 1;
commit;

-- 공지사항 테이블
drop table board_nofi;
create table board_nofi(
	nofi_no number(7) not null,
	nofi_id varchar2(20) not null,
	nofi_title varchar2(20) not null,
	nofi_content varchar2(100) not null,
	nofi_date date not null,
	primary key(nofi_no)
);
commit;


-- 자유게시판 시퀸스
drop sequence  fr_seq;
create sequence fr_seq
 start with 1
 increment by 1;
commit;

-- 자유게시판 테이블
drop table board_fr;
create table board_fr(
	fr_no number(7) not null,
	fr_id varchar2(20) not null,
	fr_name varchar2(20) not null,
	fr_title varchar2(100) not null,
	fr_content varchar2(200) not null,
	fr_count number(7) default 0,
	fr_date date not null,	
	primary key(fr_no)
);
commit;

-- 자유게시판 댓글 시퀸스
drop sequence  fr_reply_seq;
create sequence fr_reply_seq
 start with 1
 increment by 1;
commit;

-- 자유게시판 댓글 테이블
drop table reply_fr;
create table reply_fr(
	rfr_no number(7) not null,
	rfr_id varchar2(20) not null,
	rfr_content varchar2(100) not null,
	rfr_date date not null,
	rfr_board_no number(7) not null,
	primary key(rfr_no)
);
commit;


-- FAQ 게시판 시퀸스
drop sequence  faq_seq;
create sequence faq_seq
 start with 1
 increment by 1;
commit;

-- FAQ 게시판
drop table board_faq;
create table board_faq(
	faq_no number(7) not null primary key,
	faq_id varchar2(50) not null,
	faq_name varchar2(50) not null,
	faq_title varchar2(50) not null,
	faq_content varchar2(200) not null,
	faq_count number(7) default 0,
	faq_date date not null,
	faq_level number,
	faq_parent number,
	faq_group number,
	faq_re_seq number	
);	
commit;



-- 주차시퀸스
drop sequence pa_seq;
create sequence pa_seq
 start with 1
 increment by 1;


-- 주차지역의 위치
drop table parking_area cascade CONSTRAINTS;
create table parking_area (
   pa_no number not null,
   pa_si varchar2(20) ,
   pa_gu varchar2(20) ,
   pa_address varchar2(100) not null,
   pa_lat number not null,
   pa_lon number not null,
   primary key(pa_address)   
);


-- 주차공간 하나당 정보
drop table parking_area_personal;
create table parking_area_personal (
   pap_address varchar2(100) not null,
   pap_area varchar2(100) not null,
   pap_host varchar2(100) not null,
   primary key(pap_area)
);

-- 주차공간정보 <-> 주차내역위치 제약사항
alter table parking_area_personal add constraint pap_fk
foreign key(pap_address) references parking_area(pa_address) on delete cascade;
commit;

-- 대여내역 시퀸스
drop sequence  r_seq;
create sequence r_seq
start with 1
increment by 1;
commit;

-- 대여 테이블(예약됨, 예약중, 예약취소, 사용완료)
drop table reservation;
create table reservation(
   r_no number(7) not null,
   r_guest varchar2(20) not null,
   r_host varchar2(20) not null,
   r_address varchar2(100) not null,
   r_area varchar2(100) not null,
   r_content varchar2(300) null,
   r_from date not null,
   r_to date not null,
   r_request date not null,
   r_agree date,
   r_statusdate date,
   r_readcheck varchar2(10),
   r_status varchar2(20) not null,
   primary key(r_no)
);

commit;

-- account<->login 제약사항
alter table login add constraint login_id_fk
foreign key(lg_id) references  account(ac_id) on delete cascade;
commit ;  



	


