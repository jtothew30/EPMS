package com.epms.Model.FreeBoard;

import java.util.ArrayList;

import com.epms.Model.DBConnection.DBConnection;
import com.epms.Model.DBConnection.GlobalVariable;

public class FreeBoardReplyDAO extends GlobalVariable {
	
  public FreeBoardReplyDAO( ) {
	  //CN=DBConnection.getConnection();
  }
	  
  public void freeBoardReplyInsert(FreeBoardReplyBean fbrb) {
	   try {
	     msg="insert into reply_fr values(fr_reply_seq.nextval,?,?,sysdate,?)";
	     CN=DBConnection.getConnection();
	     PST=CN.prepareStatement(msg);
	     	PST.setString(1, fbrb.getRfr_id());
	     	PST.setString(2, fbrb.getRfr_content());
	     	PST.setInt(3, fbrb.getRfr_board_no());
	     PST.executeUpdate();
	     System.out.println("리플db 저장성공");
	   }catch(Exception ex) {
		   System.out.println("ReplyInsertError: " + ex.getMessage());
	   }finally {
			try {
				if(PST != null) {
					PST.close();
				}
				if(CN != null) {
					CN.close();
				}
			}catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	 }
 
 	public ArrayList<FreeBoardReplyBean> freeBoardReplyList(int Rdata) {
	   ArrayList<FreeBoardReplyBean> rlist=new ArrayList<FreeBoardReplyBean>();
	   try {
	 	String x=" select b.fr_no,r.rfr_no,r.rfr_id,r.rfr_content,r.rfr_date,r.rfr_board_no from board_fr b " ;
	 	String y=" inner join reply_fr r " ;
	 	String m=" on  b.fr_no=r.rfr_board_no " ;
	 	String z=" where r.rfr_board_no=" + Rdata +" order by rfr_date";
	 	msg=x+y+m+z;
	 	CN=DBConnection.getConnection();
		ST=CN.createStatement();
		RS=ST.executeQuery(msg);
		while(RS.next()==true) {
		  FreeBoardReplyBean fbrb = new FreeBoardReplyBean();
		  fbrb.setRfr_no(RS.getInt("rfr_no"));
		  fbrb.setRfr_id(RS.getString("rfr_id"));
		  fbrb.setRfr_content(RS.getString("rfr_content"));
		  fbrb.setRfr_date(RS.getString("rfr_date"));
		  fbrb.setRfr_board_no(RS.getInt("rfr_board_no"));
		  rlist.add(fbrb);
		}
	   }catch(Exception ex) {
		   System.out.println("Error: "+ex);
	   }finally {
			try {
				if(RS != null) {
					RS.close();
				}
				if(ST != null) {
					ST.close();
				}
				if(CN != null) {
					CN.close();
				}
			}catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}	 
	   return rlist; 
	 }
 	
 	public FreeBoardReplyBean freeBoardReplyDelete(int rfrBoardNo) {
 	   FreeBoardReplyBean fbrb = new FreeBoardReplyBean();
 	   try {
 		msg="delete from reply_fr where rfr_no = ?";
 		CN=DBConnection.getConnection();
 		PST=CN.prepareStatement(msg);
 		PST.setInt(1, rfrBoardNo);
 		PST.executeUpdate();
 		System.out.println("reply Delete success!!!");
 	   }catch(Exception ex) {
 		   System.out.println("ReplyDeleteError: "+ex.getMessage());
 		}finally {
			try {
				if(PST != null) {
					PST.close();
				}
				if(CN != null) {
					CN.close();
				}
			}catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
 	return fbrb;	   
 	 }//end

	public FreeBoardReplyBean freeBoardReplyEditRequest(int data) {
		FreeBoardReplyBean fbrb = new FreeBoardReplyBean();
		try {
			msg="select * from reply_fr where rfr_no= ?";
			CN=DBConnection.getConnection();
			PST=CN.prepareStatement(msg);
	 		PST.setInt(1, data);
	 		RS = PST.executeQuery();
			if(RS.next()) {
				fbrb.setRfr_no(RS.getInt("rfr_no"));
				fbrb.setRfr_id(RS.getString("rfr_id"));
				fbrb.setRfr_content(RS.getString("rfr_content"));
				fbrb.setRfr_date(RS.getString("rfr_date"));
				fbrb.setRfr_board_no(RS.getInt("rfr_board_no"));			
			}			
			
		} catch(Exception ex) {
			System.out.println("ReplyEditError: " + ex.getMessage());
		}finally {
			try {
				if(RS != null) {
					RS.close();
				}
				if(PST != null) {
					PST.close();
				}
				if(CN != null) {
					CN.close();
				}
			}catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		
		return fbrb;
	}

	public void freeBoardReplyEditUpdate(int replyNo, String rfr_content) {
		try {
			msg = "update reply_fr set rfr_content=? where rfr_no=?"; 
			CN=DBConnection.getConnection();
			PST=CN.prepareStatement(msg);
			PST.setString(1, rfr_content);
			PST.setInt(2, replyNo);
			PST.executeUpdate();
			System.out.println("reply update success!!!");
		} catch(Exception ex) {
			System.out.println("ReplyEditError: " + ex.getMessage());
		}finally {
			try {
				if(PST != null) {
					PST.close();
				}
				if(CN != null) {
					CN.close();
				}
			}catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		
	}
 
}







