package com.epms.Model.Admin.Reservation;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServlet;

import com.epms.Model.DBConnection.DBConnection;

public class Reservation_DAO extends HttpServlet {	

	private static final long serialVersionUID = 1L;

	DBConnection db = new DBConnection();
	
	PreparedStatement pstmt = null;
	Connection conn = null;
	ResultSet rs;	
	
	public boolean reservationInsert(Reservation_Bean bean) {
		boolean result = false;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			
			StringBuffer sql = new StringBuffer();
			sql.append(" insert into reservation ");			
			sql.append(" values(r_seq.nextval, ?, ?, ?, ?, ?,");
								//no,guest,host,address,area,content
			sql.append(" to_date(?, 'YYYY-MM-DD hh24:mi:ss'), to_date(?,'YYYY-MM-DD hh24:mi:ss'),");
			sql.append(" to_date(?, 'YYYY-MM-DD hh24:mi:ss'), to_date(?,'YYYY-MM-DD hh24:mi:ss'),");
			sql.append(" to_date(?, 'YYYY-MM-DD hh24:mi:ss'), ?, ?)");
					//from,to //request,agree //statusdate,readcheck,status
						
			pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1,bean.getGuest() );
				pstmt.setString(2,bean.getHost() );
				pstmt.setString(3,bean.getAddress() );
				pstmt.setString(4,bean.getArea() );
				pstmt.setString(5,bean.getContent() );
				
				pstmt.setString(6,bean.getFrom() );
				pstmt.setString(7,bean.getTo() );
				pstmt.setString(8,bean.getRequest() );
				pstmt.setString(9,bean.getAgree() );
				pstmt.setString(10,bean.getStatusdate() );
				
				pstmt.setString(11,bean.getReadcheck());
				pstmt.setString(12,bean.getStatus());
				System.out.println(bean.getFrom());
			sql.delete(0, sql.toString().length());
				
			int flag = pstmt.executeUpdate();
			if(flag > 0) {
				result = true;
				conn.commit();
			}		
		} catch (Exception e) {
			try {conn.rollback();}
			catch (SQLException sqle) {sqle.printStackTrace();}
			e.printStackTrace();
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
	
	public ArrayList<Reservation_Bean> getReservationList(HashMap<String, Object> list){
		ArrayList<Reservation_Bean> qlist = new ArrayList<Reservation_Bean>();
		
		String skey = (String)list.get("skey");
		String sval = (String)list.get("sval");
		int start = (Integer)list.get("start");
		
		// 글목록 전체를 보여줄 때
		try {
			conn = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			
			if(skey == null) {				
				sql.append(" select * from");
				sql.append("  (select rownum as rnum, p.* from reservation p) ");				
				sql.append(" where rnum>=? and rnum<=?");
				
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setInt(1, start);
				pstmt.setInt(2, start+9);
				
				sql.delete(0, sql.toString().length());
			
			}else if(skey.equals("0")) {
				sql.append(" select * from");
				sql.append(" (select rownum as rnum, p.* from ");
				sql.append("  (select * from reservation ");
				sql.append("  where r_guest like ? ) p )");
				sql.append(" where rnum>=? and rnum<=?");
				
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, '%'+sval+'%');
				pstmt.setInt(2, start);
				pstmt.setInt(3, start+9);
				
				sql.delete(0, sql.toString().length());
				
			}else if(skey.equals("1")) {
				sql.append(" select * from");
				sql.append(" (select rownum as rnum, p.* from ");
				sql.append("  (select * from reservation ");
				sql.append("  where r_host like ? ) p )");
				sql.append(" where rnum>=? and rnum<=?");
				
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, '%'+sval+'%');
				pstmt.setInt(2, start);
				pstmt.setInt(3, start+9);
				
				sql.delete(0, sql.toString().length());
			}else if(skey.equals("2")) {
				sql.append(" select * from");
				sql.append(" (select rownum as rnum, p.* from ");
				sql.append("  (select * from reservation ");
				sql.append("  where r_address like ? ) p )");
				sql.append(" where rnum>=? and rnum<=?");
				
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, '%'+sval+'%');
				pstmt.setInt(2, start);
				pstmt.setInt(3, start+9);
				
				sql.delete(0, sql.toString().length());
			}

			rs = pstmt.executeQuery();
			while(rs.next()) {
				Reservation_Bean bean = new Reservation_Bean();
				bean.setNo(rs.getInt("r_no"));				
				bean.setGuest(rs.getString("r_guest"));
				bean.setHost(rs.getString("r_host"));
				bean.setAddress(rs.getString("r_address"));
				bean.setArea(rs.getString("r_area"));
				bean.setContent(rs.getString("r_content"));
				bean.setFrom(rs.getString("r_from"));
				bean.setTo(rs.getString("r_to"));
				bean.setRequest(rs.getString("r_request"));
				bean.setAgree(rs.getString("r_agree"));
				bean.setStatusdate(rs.getString("r_statusdate"));
				bean.setStatus(rs.getString("r_status"));
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
	public int getReservationListCount(HashMap<String,Object> list) {		
		int result = 0;
		String skey = (String)list.get("skey");
		String sval = (String)list.get("sval");
		try {
			conn = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			if(skey==null) {
				sql.append("select count(*) from reservation");
				pstmt = conn.prepareStatement(sql.toString());
				sql.delete(0, sql.toString().length());				
			}else if(skey.equals("0")) {
				sql.append("select count(*) from reservation where r_guest like ?");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, '%'+sval+'%');
				sql.delete(0, sql.toString().length());	
			}else if(skey.equals("1")) {
				sql.append("select count(*) from reservation where r_host like ?");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, '%'+sval+'%');
				sql.delete(0, sql.toString().length());	
			}else if(skey.equals("2")) {
				sql.append("select count(*) from reservation where r_address like ?");
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
		System.out.println(result);
		return result;
	}
	public Reservation_Bean getDetail(int no) {
		Reservation_Bean bean = null;
		try {			
			conn = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * from reservation where r_no = ? ");
			pstmt = conn.prepareStatement(sql.toString());
				pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bean = new Reservation_Bean();
				bean.setNo(rs.getInt("r_no"));				
				bean.setGuest(rs.getString("r_guest"));
				bean.setHost(rs.getString("r_host"));
				bean.setAddress(rs.getString("r_address"));
				bean.setArea(rs.getString("r_area"));
				bean.setContent(rs.getString("r_content"));
				bean.setFrom(rs.getString("r_from"));
				bean.setTo(rs.getString("r_to"));
				bean.setRequest(rs.getString("r_request"));
				bean.setAgree(rs.getString("r_agree"));
				bean.setStatusdate(rs.getString("r_statusdate"));
				bean.setReadcheck(rs.getString("r_readcheck"));
				bean.setStatus(rs.getString("r_status"));
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
	
	public boolean reservationDelete(int no) {
		boolean result = false;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			
			StringBuffer sql = new StringBuffer();
			sql.append(" delete from reservation");
			sql.append(" where r_no = ?");			
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setInt(1, no);
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
	public boolean reservationUpdate(Reservation_Bean bean) {
		boolean result = false;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			
			StringBuffer sql = new StringBuffer();
			sql.append(" update reservation set");
			sql.append(" r_guest = ?");
			sql.append(" ,r_host = ?");
			sql.append(" ,r_address = ? ");
			sql.append(" ,r_area = ? ");
			sql.append(" ,r_content = ? ");
			sql.append(" ,r_from = to_date(?, 'YYYY-MM-DD hh24:mi:ss') ");
			sql.append(" ,r_to = to_date(?, 'YYYY-MM-DD hh24:mi:ss') ");
			sql.append(" ,r_request = to_date(?, 'YYYY-MM-DD hh24:mi:ss') ");
			sql.append(" ,r_agree = to_date(?, 'YYYY-MM-DD hh24:mi:ss') ");
			sql.append(" ,r_statusdate = to_date(?, 'YYYY-MM-DD hh24:mi:ss') ");
			sql.append(" ,r_readcheck = ? ");
			sql.append(" ,r_status = ? ");
			sql.append(" where r_no = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1,bean.getGuest() );
			pstmt.setString(2,bean.getHost() );
			pstmt.setString(3,bean.getAddress() );
			pstmt.setString(4,bean.getArea() );
			pstmt.setString(5,bean.getContent() );
			pstmt.setString(6,bean.getFrom() );
			pstmt.setString(7,bean.getTo() );
			pstmt.setString(8,bean.getRequest() );
			pstmt.setString(9,bean.getAgree() );
			pstmt.setString(10,bean.getStatusdate() );
			pstmt.setString(11,bean.getReadcheck());
			pstmt.setString(12,bean.getStatus());
			pstmt.setInt(13, bean.getNo());
						
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
}
