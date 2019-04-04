package com.epms.Model.User;

import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class GlobalVariable {
   Connection CN;
   Statement ST, ST3 ; //ST=CN.createStatement()
   PreparedStatement PST; //PST=CN.prepareStatment(sql)
   CallableStatement CS; //CS=CN.callableStatement(sql)
   ResultSet RS, RS3; //  RS.next( )
 
   String msg, msg3; 
   int a,Gsabun,  d, Gpay ; 
   String b, Gname, c , Gtitle; 
   java.util.Date Gnalja;
   int Gtotal=27, Stotal=7, Rtotal=0; 
   
   int Rnum ; 
   int Rrn ; 
   String Rdata, Rwriter, Rcontent; //guestreply.jsp문서에서 사용
   int Rsabun;
   boolean flag=false;
   
   //페이징, 검색에 필요한 전역변수 ==> 
   int start, end ;  //[7클릭] start=61, end=70
   int pagecount;  //316레코드갯수 페이지갯수 7페이지
   int pageNUM ;   //[7문자] =>숫자화 pageNUM=Integer.parsetInt("7")
   int startpage, endpage; //[14클릭]  startpage=11, endpage=20
   String pnum;  //pnum=request.getParamenter("pageNum")
   int temp; //시작페이지를 구하기위해서 사용하는변수  
   int num; //행번호 역순출력 
   
   String sqry; 
   String skey, sval; //검색필드, sky="title필드" sval="%p%"
   String returnpage;
}
