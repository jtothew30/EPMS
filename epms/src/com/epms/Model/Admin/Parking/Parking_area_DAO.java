package com.epms.Model.Admin.Parking;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServlet;

import com.epms.Model.Admin.Account.Account_Bean;
import com.epms.Model.DBConnection.DBConnection;

public class Parking_area_DAO extends HttpServlet {	

	private static final long serialVersionUID = 1L;

	DBConnection db = new DBConnection();
	
	PreparedStatement pstmt = null;
	Connection conn = null;
	ResultSet rs;
	/*
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
	*/	
	
	public boolean parkingInsert(Parking_area_Bean bean) {
		boolean result = false;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			
			StringBuffer sql = new StringBuffer();
			sql.append(" insert into parking_area");			
			sql.append(" values(pa_seq.nextval,?,?,?,?,?)");
						//pa_no, pa_si, pa_gu, pa_address, pa_lat, pa_lon
						// N	  S		S		S			N		N
						
			pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, bean.getSi());
				pstmt.setString(2, bean.getGu());
				pstmt.setString(3, bean.getAddress());
				pstmt.setDouble(4, bean.getLat());
				pstmt.setDouble(5, bean.getLon());			

			sql.delete(0, sql.toString().length());
				
			int flag = pstmt.executeUpdate();
			if(flag > 0) {
				result = true;
				conn.commit();
			}		
			return true;
		} catch (Exception e) {
			try {conn.rollback();}
			catch (SQLException sqle) {sqle.printStackTrace();}
			e.printStackTrace();
			return result;
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
	}
	
	public boolean parkingPInsert(ParkingP_Bean bean) {
		boolean result = false;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			
			StringBuffer sql = new StringBuffer(); 
			sql.append(" insert into parking_area_personal ");			
			sql.append(" values(?,?,?)");
			
			if(!updateAccountArea(bean)) {
				return false;
			};	
			
			pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, bean.getAddress());
				pstmt.setString(2, bean.getArea());
				pstmt.setString(3, bean.getHost());					

			sql.delete(0, sql.toString().length());
				
			int flag = pstmt.executeUpdate();
			if(flag > 0) {
				result = true;
				conn.commit();
			}		
		} catch (Exception e) {
			try {conn.rollback();return false;}
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
	
	public boolean updateAccountArea(ParkingP_Bean bean) {
		boolean result = false;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			
			StringBuffer sql = new StringBuffer();
			sql.append(" update account set");
			sql.append(" ac_area = ? ");
			sql.append(" where ac_id = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, bean.getArea());
			
			int flag = pstmt.executeUpdate();
			if(flag > 0){
				result = true;
				conn.commit(); 
			}	
		}catch (Exception e) {
			try {conn.rollback(); return false;}
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
	
	public ArrayList<Parking_area_Bean> getParkingList(HashMap<String, Object> list){
		ArrayList<Parking_area_Bean> qlist = new ArrayList<Parking_area_Bean>();
		
		String skey = (String)list.get("skey");
		String sval = (String)list.get("sval");
		int start = (Integer)list.get("start");
		
		// 글목록 전체를 보여줄 때
		try {
			conn = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			
			if(skey == null) {				
				sql.append(" select * from");
				sql.append("  (select rownum as rnum, p.* from parking_area p) ");				
				sql.append(" where rnum>=? and rnum<=?");
				
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setInt(1, start);
				pstmt.setInt(2, start+9);
				
				sql.delete(0, sql.toString().length());
			
			}else if(skey.equals("0")) {
				sql.append(" select * from");
				sql.append(" (select rownum as rnum, p.* from ");
				sql.append("  (select * from parking_area ");
				sql.append("  where pa_address like ? ) p )");
				sql.append(" where rnum>=? and rnum<=?");
				
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, '%'+sval+'%');
				pstmt.setInt(2, start);
				pstmt.setInt(3, start+9);
				
				sql.delete(0, sql.toString().length());
				
			}else if(skey.equals("1")) {
				sql.append(" select * from");
				sql.append(" (select rownum as rnum, p.* from ");
				sql.append("  (select * from parking_area ");
				sql.append("  where pa_gu like ? ) p )");
				sql.append(" where rnum>=? and rnum<=?");
				
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, '%'+sval+'%');
				pstmt.setInt(2, start);
				pstmt.setInt(3, start+9);
				
				sql.delete(0, sql.toString().length());
			}

			rs = pstmt.executeQuery();
			while(rs.next()) {
				Parking_area_Bean bean = new Parking_area_Bean();
				bean.setNo(rs.getInt("pa_no"));				
				bean.setSi(rs.getString("pa_si"));
				bean.setGu(rs.getString("pa_gu"));
				bean.setAddress(rs.getString("pa_address"));
				bean.setLat(rs.getDouble("pa_lat"));
				bean.setLon(rs.getDouble("pa_lon"));
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
	public int getParkingListCount(HashMap<String,Object> list) {		
		int result = 0;
		String skey = (String)list.get("skey");
		String sval = (String)list.get("sval");
		try {
			conn = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			if(skey==null) {
				sql.append("select count(*) from parking_area");
				pstmt = conn.prepareStatement(sql.toString());
				sql.delete(0, sql.toString().length());				
			}else if(skey.equals("0")) {
				sql.append("select count(*) from parking_area where pa_address like ?");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, '%'+sval+'%');
				sql.delete(0, sql.toString().length());	
			}else if(skey.equals("1")) {
				sql.append("select count(*) from parking_area where pa_gu like ?");
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
	public Parking_area_Bean getDetail(String address) {
		Parking_area_Bean bean = null;
		try {			
			conn = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * from parking_area where pa_address = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, address);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bean = new Parking_area_Bean();
				bean.setNo(rs.getInt("pa_no"));			
				bean.setSi(rs.getString("pa_si"));
				bean.setGu(rs.getString("pa_gu"));
				bean.setAddress(rs.getString("pa_address"));
				bean.setLat(rs.getDouble("pa_lat"));
				bean.setLon(rs.getDouble("pa_lon"));
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
	public ArrayList<ParkingP_Bean> getDetailAreaPList(String address) {
		ArrayList<ParkingP_Bean> list = new ArrayList<>();
		try {			
			conn = DBConnection.getConnection();
			String msg = "select * from parking_area_personal where pap_address =?";
			pstmt = conn.prepareStatement(msg);			
			pstmt.setString(1, address);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ParkingP_Bean bean = new ParkingP_Bean();
				bean.setAddress(rs.getString("pap_address"));
				bean.setArea(rs.getString("pap_Area"));
				bean.setHost(rs.getString("pap_Host"));	
				System.out.println("rs go");
				list.add(bean);
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
		return list;		
	}
	
	public ParkingP_Bean getDetailAreaP(String address) {
		ParkingP_Bean bean = null;
		try {			
			conn = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * from parking_area_personal where pap_address = ? ");
			pstmt = conn.prepareStatement(sql.toString());			
			pstmt.setString(1, address);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bean = new ParkingP_Bean();
				bean.setAddress(rs.getString("pap_address"));
				bean.setArea(rs.getString("pap_Area"));
				bean.setHost(rs.getString("pap_Host"));	
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

	public boolean deleteParking(String id) {
		boolean result = false;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			
			StringBuffer sql = new StringBuffer();
			sql.append(" delete from parking_area");
			sql.append(" where pa_address = ?");			
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, id);
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
	public boolean updateParking(Parking_area_Bean bean) {
		boolean result = false;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			
			StringBuffer sql = new StringBuffer();
			sql.append(" update parking_area set");
			sql.append(" pa_si = ?");
			sql.append(" ,pa_gu = ?");
			sql.append(" ,pa_address = ? ");
			sql.append(" ,pa_lat = ? ");
			sql.append(" ,pa_lon = ? ");
			sql.append(" where pa_no = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, bean.getSi());
			pstmt.setString(2, bean.getGu());			
			pstmt.setString(3, bean.getAddress());
			pstmt.setDouble(4, bean.getLat());
			pstmt.setDouble(5, bean.getLon());
			pstmt.setInt(6, bean.getNo());
			
			
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
	public boolean updateParkingP(ParkingP_Bean bean) {
		boolean result = false;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			
			StringBuffer sql = new StringBuffer();
			sql.append(" update parking_area_personal set");
			sql.append(" pap_area = ?");
			sql.append(" ,pap_host = ?");
			sql.append(" where pap_address = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, bean.getArea());
			pstmt.setString(2, bean.getHost());			
			pstmt.setString(3, bean.getAddress());	
			
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
	
	public int areaCheck(String area) {
		int result = 0;
		try {
			conn = DBConnection.getConnection();
			
			String sql = "select count(*) as cnt from parking_area_personal where pap_area='"+area+"'";
			pstmt = conn.prepareStatement(sql);				
			rs = pstmt.executeQuery(sql);
			if(rs.next()) {
				result = rs.getInt("cnt");
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
		return result;
	}	
	
	public boolean deleteParkingP(String area) {
		boolean result = false;
		try {
			conn = DBConnection.getConnection();
			System.out.println("d " + area);
			StringBuffer sql = new StringBuffer();
			sql.append(" delete from parking_area_personal");
			sql.append(" where pap_area = ?");			
			
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, area);
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
}
