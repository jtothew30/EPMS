package com.epms.Model.FAQ;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServlet;

import com.epms.Model.DBConnection.DBConnection;	

public class FAQ_DAO extends HttpServlet {	
	
	private static final long serialVersionUID = 1L;
	
	DBConnection db = new DBConnection();
	
	PreparedStatement pstmt = null;
	Connection conn = null;
	ResultSet rs;
	
	public int getSeq() {
		int result = 1;
		try {
			conn = DBConnection.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("select faq_seq.nextval from dual");
			
			pstmt = conn.prepareStatement(sql.toString());			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}catch (Exception e) {
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
	
	
	public boolean FAQInsert(FAQ_Bean bean) {
		boolean result = false;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			
			StringBuffer sql = new StringBuffer();
			sql.append(" Insert into board_faq");
			sql.append(" (faq_no, faq_id, faq_name, faq_title, faq_content, faq_count");
			sql.append(" , faq_date, faq_level, faq_parent, faq_group, faq_re_seq)");
			sql.append(" values(?,?,?,?,?,?,sysdate,?,?,?,?)");
			
			int num = bean.getNo();			// 글번호 시퀸스
			int group = bean.getGroup(); 	// 그룹번호
			int parent = bean.getParent();  // 부모글번호
			
			if(parent==0) {group = num;}	
			// 부모글(최상위레벨)글일경우 => (그룹번호 == 글번호)
			
			pstmt = conn.prepareStatement(sql.toString());
				pstmt.setInt(1, num);
				pstmt.setString(2, bean.getId());
				pstmt.setString(3, bean.getName());
				pstmt.setString(4, bean.getTitle());
				pstmt.setString(5, bean.getContent());
				pstmt.setInt(6, bean.getCount());
				//sysdate
				pstmt.setInt(7, bean.getLevel());
				pstmt.setInt(8, parent);
				pstmt.setInt(9, group);
				pstmt.setInt(10, bean.getRe_seq());
				
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
	
	public ArrayList<FAQ_Bean> getFAQList(HashMap<String, Object> list){
		ArrayList<FAQ_Bean> qlist = new ArrayList<FAQ_Bean>();
		
		String skey = (String)list.get("skey");
		String sval = (String)list.get("sval");
		int start = (Integer)list.get("start");
		
		// 글목록 전체를 보여줄 때
		try {
			conn = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			
			if(skey == null) {
				/*
				 * SELECT * FROM
				 *			(SELECT  ROWNUM AS rnum,
                 *  				 f.*
            	 *			 FROM
                 *   				(SELECT FAQ_LEVEL,
                 *             			 	FAQ_NO,
                 *             				FAQ_ID,
                 *            				FAQ_NAME,
                 *             				FAQ_CONTENT,
                 *             				FAQ_GROUP,
                 *             				FAQ_RE_SEQ,
                 *             				FAQ_PARENT,
                 *             				FAQ_DATE
                 *   				 FROM BOARD_FAQ
                 *      			 START WITH FAQ_PARENT = 0
                 *      			 CONNECT BY PRIOR FAQ_NO = FAQ_PARENT
                 *      			 ORDER SIBLINGS BY FAQ_GROUP desc) 
                 *			 f)
 				 * WHERE rnum>=? and rnum<=? ;
				 */
				sql.append(" select * from");
				sql.append(" (select rownum as rnum, f.* from ");
				sql.append("   (select FAQ_level, FAQ_NO,FAQ_ID,FAQ_NAME,FAQ_TITLE,");
				sql.append("           FAQ_CONTENT,FAQ_COUNT,FAQ_GROUP,FAQ_DATE,FAQ_PARENT,FAQ_RE_SEQ");
				sql.append("   from board_faq ");
				sql.append("   start with faq_parent =0");
				sql.append("   connect by prior faq_no = faq_parent");
				sql.append("   order siblings by FAQ_GROUP desc)");
				sql.append(" f) ");
				sql.append(" where rnum>=? and rnum<=?");
				
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setInt(1, start);
				pstmt.setInt(2, start+9);
				
				sql.delete(0, sql.toString().length());
			
			}else if(skey.equals("0")) {
				
				sql.append(" select * from");
				sql.append(" (select rownum as rnum, f.* from ");
				sql.append("   (select FAQ_level, FAQ_NO,FAQ_ID,FAQ_NAME,FAQ_TITLE,");
				sql.append("           FAQ_CONTENT,FAQ_COUNT,FAQ_GROUP,FAQ_DATE,FAQ_PARENT,FAQ_RE_SEQ");
				sql.append("   from board_faq ");
				sql.append("   where faq_title like ?");
				sql.append("   start with faq_parent =0");
				sql.append("   connect by prior faq_no = faq_parent");
				sql.append("   order siblings by FAQ_GROUP desc)");
				sql.append(" f) ");
				sql.append(" where rnum>=? and rnum<=?");
				
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, '%'+sval+'%');
				pstmt.setInt(2, start);
				pstmt.setInt(3, start+9);
				
				sql.delete(0, sql.toString().length());
			}else if(skey.equals("1")) {
				sql.append(" select * from");
				sql.append(" (select rownum as rnum, f.* from ");
				sql.append("   (select FAQ_level, FAQ_NO,FAQ_ID,FAQ_NAME,FAQ_TITLE,");
				sql.append("           FAQ_CONTENT,FAQ_COUNT,FAQ_GROUP,FAQ_DATE,FAQ_PARENT,FAQ_RE_SEQ");
				sql.append("   from board_faq ");
				sql.append("   where faq_content like ?");
				sql.append("   start with faq_parent =0");
				sql.append("   connect by prior faq_no = faq_parent");
				sql.append("   order siblings by FAQ_GROUP desc)");
				sql.append(" f) ");
				sql.append(" where rnum>=? and rnum<=?");
				
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, '%'+sval+'%');
				pstmt.setInt(2, start);
				pstmt.setInt(3, start+9);
				
				sql.delete(0, sql.toString().length());
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				FAQ_Bean bean = new FAQ_Bean();
				bean.setRn(rs.getInt("rnum"));
				bean.setLevel(rs.getInt("faq_level"));
				bean.setNo(rs.getInt("faq_no"));
				bean.setId(rs.getString("faq_id"));
				bean.setName(rs.getString("faq_name"));
				bean.setTitle(rs.getString("faq_title"));
				bean.setContent(rs.getString("faq_content"));
				bean.setCount(rs.getInt("faq_count"));
				bean.setGroup(rs.getInt("faq_group"));
				bean.setRe_seq(rs.getInt("faq_re_seq"));
				bean.setDate(rs.getDate("faq_date"));
				bean.setParent(rs.getInt("faq_parent"));
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
	public int getFAQListCount(HashMap<String,Object> list) {		
		int result = 0;
		String skey = (String)list.get("skey");
		String sval = (String)list.get("sval");
		try {
			conn = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			if(skey==null) {
				sql.append("select count(*) from board_faq");
				pstmt = conn.prepareStatement(sql.toString());
				sql.delete(0, sql.toString().length());				
			}else if(skey.equals("0")) {
				sql.append("select count(*) from board_faq where faq_title like ?");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, '%'+sval+'%');
				sql.delete(0, sql.toString().length());	
			}else if(skey.equals("1")) {
				sql.append("select count(*) from board_faq where faq_content like ?");
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
	public FAQ_Bean getDetail(int num) {
		FAQ_Bean bean = null;
		try {
			/*
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
			*/
			conn = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * from board_faq where faq_no = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bean = new FAQ_Bean();
				bean.setNo(num);
				bean.setId(rs.getString("faq_id"));
				bean.setName(rs.getString("faq_name"));
				bean.setTitle(rs.getString("faq_title"));
				bean.setContent(rs.getString("faq_Content"));
				bean.setCount(rs.getInt("faq_count"));
				bean.setDate(rs.getDate("faq_date"));
				bean.setLevel(rs.getInt("faq_level"));
				bean.setParent(rs.getInt("faq_parent"));
				bean.setGroup(rs.getInt("faq_group"));
				bean.setRe_seq(rs.getInt("faq_re_seq"));
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
	public boolean updateCount(int num) {
		boolean result = false;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			
			StringBuffer sql = new StringBuffer();
			sql.append("update board_faq set faq_count = faq_count +1" );
			sql.append("where faq_no = ?");
			pstmt = conn.prepareStatement(sql.toString());
				pstmt.setInt(1, num);
			
			int flag = pstmt.executeUpdate();
			if(flag>0) {
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
	public boolean deleteFAQ(int num) {
		boolean result = false;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			
			StringBuffer sql = new StringBuffer();
			sql.append(" delete from board_faq");
			sql.append(" where faq_no = ?");			
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, num);
			
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
	public boolean updateFAQ(FAQ_Bean bean) {
		boolean result = false;
		try {
			conn = DBConnection.getConnection();
			conn.setAutoCommit(false);
			
			StringBuffer sql = new StringBuffer();
			sql.append(" update board_faq set");
			sql.append(" faq_title = ?");
			sql.append(" ,faq_content = ?");
			sql.append(" ,faq_date = sysdate ");
			sql.append(" where faq_no = ?");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, bean.getTitle());
			pstmt.setString(2, bean.getContent());			
			pstmt.setInt(3, bean.getNo());
			
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
	
	public String getName(String id) {
		String name="";
		try {
			conn = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select ac_name from account where ac_id = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				name = rs.getString("ac_name");
			}
		}catch(Exception e) {
			System.out.println("getName error : "+e);
		}
		return name;
	}
	
	public int getRe(int num) {
		int result = 0;
		try {
		conn = DBConnection.getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) from board_faq where faq_parent = ? and faq_level>0");
		pstmt = conn.prepareStatement(sql.toString());
		pstmt.setInt(1, num);
		sql.delete(0, sql.toString().length());
		rs = pstmt.executeQuery();
		if (rs.next()){
			result = rs.getInt(1);		
		}
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
			throw new RuntimeException(e.getMessage());
			} finally {
				try {
					if (pstmt != null) {
						pstmt.close();
						pstmt = null;
					}
					if (conn != null) {
						conn.close();
						conn = null;
					}
				} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
				}
			}
			return result;
	}

}
