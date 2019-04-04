package com.epms.Model.Admin.Account;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServlet;

import com.epms.Model.DBConnection.DBConnection;

public class Account_DAO extends HttpServlet {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DBConnection db = new DBConnection();
	
	PreparedStatement pstmt = null;
	Connection conn = null;
	ResultSet rs;
	/*
	create table account(
	
	ac_id varchar2(20) not null,
	ac_pw varchar2(20) not null,
	ac_name varchar2(15) not null,
	ac_phone varchar2(20) not null,
	ac_addr1 varchar2(100) not null,
	ac_addr2 varchar2(100) not null,
	ac_email varchar2(30) not null,
	ac_date date not null,
	ac_type varchar2(10) null,
	ac_point number default 0,
	ac_area varchar2(20) null,
	primary key(ac_id)
	
	);
	*/	
		
	
	public ArrayList<Account_Bean> getAcountList(HashMap<String, Object> list){
		ArrayList<Account_Bean> qlist = new ArrayList<Account_Bean>();
		
		String skey = (String)list.get("skey");
		String sval = (String)list.get("sval");
		int start = (Integer)list.get("start");
		
		// 글목록 전체를 보여줄 때
		try {
			conn = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();			
			if(skey ==null) {
				sql.append(" select * from ");
				sql.append(" (select rownum as rnum, a.* from ");
				sql.append(" (select * from account) a)");
				sql.append(" where rnum>=? and rnum<=?");
				
				pstmt = conn.prepareStatement(sql.toString());				
				pstmt.setInt(1, start);
				pstmt.setInt(2, start+9);
				
				sql.delete(0, sql.toString().length());
			}else if(skey.equals("0")) {
				sql.append(" select * from ");
				sql.append(" (select rownum as rnum, a.* from ");
				sql.append(" (select * from account where ac_id like ?) a)");
				sql.append(" where rnum>=? and rnum<=?");
				
				pstmt = conn.prepareStatement(sql.toString());	
				pstmt.setString(1, '%'+sval+'%');
				pstmt.setInt(2, start);
				pstmt.setInt(3, start+9);
				
				sql.delete(0, sql.toString().length());
			}else if(skey.equals("1")) {
				sql.append(" select * from ");
				sql.append(" (select rownum as rnum, a.* from ");
				sql.append(" (select * from account where ac_name like ?) a)");
				sql.append(" where rnum>=? and rnum<=?");
				
				pstmt = conn.prepareStatement(sql.toString());	
				pstmt.setString(1, '%'+sval+'%');
				pstmt.setInt(2, start);
				pstmt.setInt(3, start+9);
				
				sql.delete(0, sql.toString().length());
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Account_Bean bean = new Account_Bean();
				bean.setId(rs.getString("ac_id"));
				bean.setPw(rs.getString("ac_pw"));
				bean.setName(rs.getString("ac_name"));
				bean.setPhone(rs.getString("ac_phone"));
				bean.setAddr1(rs.getString("ac_addr1"));
				bean.setAddr2(rs.getString("ac_addr2"));
				bean.setEmail(rs.getString("ac_email"));
				bean.setDate(rs.getDate("ac_date"));
				bean.setType(rs.getString("ac_type"));
				bean.setPoint(rs.getInt("ac_point"));
				bean.setArea(rs.getString("ac_area"));
				qlist.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if(conn != null) {
					conn.close();
					conn = null;
				}
			}catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return qlist;
	}
	// 총 글 갯수
	public int getAccountListCount(HashMap<String,Object> list) {		
		int result = 0;
		String skey = (String)list.get("skey");
		String sval = (String)list.get("sval");
		try {
			conn = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			if(skey==null) {
				sql.append("select count(*) from account");
				pstmt = conn.prepareStatement(sql.toString());
				sql.delete(0, sql.toString().length());				
			}else if(skey.equals("0")) {
				sql.append("select count(*) from account where ac_id like ?");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, '%'+sval+'%');
				sql.delete(0, sql.toString().length());	
			}else if(skey.equals("1")) {
				sql.append("select count(*) from account where ac_name like ?");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, '%'+sval+'%');
				sql.delete(0, sql.toString().length());	
			}
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if(conn != null) {
					conn.close();
					conn = null;
				}
			}catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return result;
	}
	public Account_Bean getDetail(String id) {
		Account_Bean bean = null;
		try {
			
			conn = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * from account where ac_id = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bean = new Account_Bean();
				bean.setId(rs.getString("ac_id"));
				bean.setPw(rs.getString("ac_pw"));
				bean.setName(rs.getString("ac_name"));
				bean.setPhone(rs.getString("ac_phone"));
				bean.setAddr1(rs.getString("ac_addr1"));
				bean.setAddr2(rs.getString("ac_addr2"));
				bean.setEmail(rs.getString("ac_email"));
				bean.setDate(rs.getDate("ac_date"));
				bean.setType(rs.getString("ac_type"));
				bean.setPoint(rs.getInt("ac_point"));
				bean.setArea(rs.getString("ac_area"));
				
			}
			
		}catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if(conn != null) {
					conn.close();
					conn = null;
				}
			}catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return bean;		
	}
	
	
	public boolean deleteAccount(String id) {
		boolean result = false;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			
			StringBuffer sql = new StringBuffer();
			sql.append(" delete from account");
			sql.append(" where ac_id = ?");			
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			
			int flag = pstmt.executeUpdate();
			if(flag > 0){
				result = true;
				conn.commit(); 
			}	
		}catch (Exception e) {
			try {conn.rollback();}
			catch (SQLException sqle) {sqle.printStackTrace();}
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if(conn != null) {
					conn.close();
					conn = null;
				}
			}catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return result;
	}
	public boolean accountInsert(Account_Bean bean) {
		boolean result = false;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			
			StringBuffer sql = new StringBuffer();
			sql.append(" insert into account values(");
			sql.append(" ?, ?, ?, ?, ?, ?,");
					  //id, pw, name, phone, addr1, addr2
			sql.append(" ?, sysdate, ?, ?, ?)");
					  //email, date, type, point, area
			
			pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, bean.getId());
				pstmt.setString(2, bean.getPw());
				pstmt.setString(3, bean.getName());
				pstmt.setString(4, bean.getPhone());
				pstmt.setString(5, bean.getAddr1());
				pstmt.setString(6, bean.getAddr2());
				pstmt.setString(7, bean.getEmail());
				pstmt.setString(8, bean.getType());
				pstmt.setInt(9, bean.getPoint());
				pstmt.setString(10, bean.getArea());
			
			sql.delete(0, sql.toString().length());	
			
			int flag = pstmt.executeUpdate();
			if(flag > 0){
				result = true;
				conn.commit(); 
			}	
		}catch (Exception e) {
			try {conn.rollback();}
			catch (SQLException sqle) {sqle.printStackTrace();}
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if(conn != null) {
					conn.close();
					conn = null;
				}
			}catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return result;		
	}
	
	public boolean accountEdit(Account_Bean bean) {
		boolean result = false;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			
			StringBuffer sql = new StringBuffer();
			sql.append(" update account set");
			sql.append(" ac_pw = ?");
			sql.append(" ,ac_name = ?");
			sql.append(" ,ac_phone = ? ");
			sql.append(" ,ac_addr1 = ? ");
			sql.append(" ,ac_addr2 = ? ");
			sql.append(" ,ac_email = ? ");
			sql.append(" ,ac_type  = ? ");
			sql.append(" ,ac_point = ? ");
			sql.append(" ,ac_area = ? ");
			sql.append(" where ac_id = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, bean.getPw());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getPhone());
			pstmt.setString(4, bean.getAddr1());
			pstmt.setString(5, bean.getAddr2());
			pstmt.setString(6, bean.getEmail());
			pstmt.setString(7, bean.getType());
			pstmt.setInt(8, bean.getPoint());
			pstmt.setString(9, bean.getArea());
			pstmt.setString(10, bean.getId());
			
			int flag = pstmt.executeUpdate();
			if(flag > 0){
				result = true;
				conn.commit(); 
			}	
		}catch (Exception e) {
			try {conn.rollback();}
			catch (SQLException sqle) {sqle.printStackTrace();}
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if(conn != null) {
					conn.close();
					conn = null;
				}
			}catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return result;		
	}
	
	public int idCheck(String id) {
		int result = 0;
		try {
			conn = DBConnection.getConnection();
			
			String sql = "select count(*) as cnt from account where ac_id='"+id+"'";
			pstmt = conn.prepareStatement(sql);				
			rs = pstmt.executeQuery(sql);
			if(rs.next()) {
				result = rs.getInt("cnt");
			}
		}catch(Exception e) {
			System.out.println("idCheck error : "+ e.toString());
		}
		return result;
	}
	
}
