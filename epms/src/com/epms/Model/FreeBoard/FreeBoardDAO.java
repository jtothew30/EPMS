package com.epms.Model.FreeBoard;

import java.beans.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.epms.Model.DBConnection.DBConnection;
import com.epms.Model.DBConnection.GlobalVariable;


public class FreeBoardDAO extends GlobalVariable{
   
   public FreeBoardDAO() {
	   //CN=DBConnection.getConnection();
   }
   
   public boolean freeBoardInsert(FreeBoardBean fbb) {
	   try {
	     msg="insert into board_fr values(fr_seq.nextval,?,?,?,?,0,sysdate)";
	     CN=DBConnection.getConnection();
	     PST=CN.prepareStatement(msg);
	     	PST.setString(1, fbb.getFr_id());
	     	PST.setString(2, fbb.getFr_name());
	     	PST.setString(3, fbb.getFr_title());
	     	PST.setString(4, fbb.getFr_content());
	     PST.executeUpdate();
	     System.out.println("db ���强��");
	     return true;
	   }catch(Exception ex) {
		   System.out.println("InsertError :" + ex.getMessage());
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
	   return false;
	 }

   public FreeBoardBean freeBoardDetail(int data) { 
	   FreeBoardBean fbb = new FreeBoardBean();
	   try {
		   msg="select * from board_fr where fr_no = " + data;
		   CN=DBConnection.getConnection();
		   ST=CN.createStatement();
		   RS=ST.executeQuery(msg);
		   RS.next();
		   fbb.setFr_no(RS.getInt("fr_no"));
		   fbb.setFr_id(RS.getString("fr_id"));
		   fbb.setFr_title(RS.getString("fr_title"));
		   fbb.setFr_content(RS.getString("fr_content"));
		   fbb.setFr_date(RS.getString("fr_date"));
		   fbb.setFr_count(RS.getInt("fr_count"));
		 
	   }catch(Exception ex) {
		   System.out.println("DetailError" + ex.getMessage());
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
	   return fbb;
   }
   
   public int freeBoardCount() {
	   try {
	 	msg="select count(*) as cnt from board_fr ";
	 	CN=DBConnection.getConnection();
	 	ST=CN.createStatement();
	 	RS=ST.executeQuery(msg);
	 	RS.next() ;
	 	Gtotal=RS.getInt("cnt");
	    }catch(Exception ex) {
	    	System.out.println("CountError: "+ex.getMessage());
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
					}if(CN != null) {
						CN.close();
					}
				}catch (Exception e) {
					throw new RuntimeException(e.getMessage());
				}
			}
	   return Gtotal;
	  }//end
   
   public int freeBoardCountSearch(String skey, String sval) {
		 String sqry=" where " + skey + " like '%" + sval + "%' "  ;
		  try {
			msg="select count(*) as cnt from board_fr " + sqry;
			CN=DBConnection.getConnection();
			ST=CN.createStatement();
			RS=ST.executeQuery(msg);
			RS.next() ;
			Gtotal=RS.getInt("cnt");
		   }catch(Exception ex) {
			   System.out.println("CountSearchError: "+ex.getMessage());
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
		  return Gtotal;
	 }//end
   
   public ArrayList<FreeBoardBean>  freeBoardSearch( int start, int end, String skey, String sval){
	   ArrayList<FreeBoardBean> list=new ArrayList<FreeBoardBean>();

	   String sqry=" where " + skey + " like '%" + sval + "%' " ;
	   System.out.println(start + " / " + end);
	   try {
	 	 String x=" select * from ( ";
	 	 String y=" select rownum rn, fr_no,fr_id,fr_name,fr_title,fr_content,fr_count,fr_date from ";
	 	 String m=" (select * from board_fr "+ sqry + " order by fr_date asc) "; 
	 	 String z=" ) where rn between "+start+" and "+end + "order by rn desc";
	 	 
	    msg=x+y+m+z;
	    CN=DBConnection.getConnection();
	    ST=CN.createStatement();
	    RS=ST.executeQuery(msg);
	    
	     while(RS.next()==true) {
	    	 FreeBoardBean fbb = new FreeBoardBean( );
	    	 fbb.setRn(RS.getInt("rn"));
	    	 fbb.setFr_no(RS.getInt("fr_no"));
			 fbb.setFr_id(RS.getString("fr_id"));
			 fbb.setFr_name(RS.getString("fr_name"));
			 fbb.setFr_title(RS.getString("fr_title"));
			 fbb.setFr_content(RS.getString("fr_content"));
			 fbb.setFr_count(RS.getInt("fr_count"));
			 fbb.setFr_date(RS.getString("fr_date"));
			 msg ="select count(*) as cnt from reply_fr where rfr_board_no="+RS.getInt("fr_no");
			 java.sql.Statement ST1=CN.createStatement();
			 ResultSet RS1= ST1.executeQuery(msg);
			 while(RS1.next()) {
				 fbb.setReply_count(RS1.getInt("cnt"));
			 }
			 list.add(fbb);
			 if(RS1 != null) {
					RS1.close();
				}
				if(ST1 != null) {
					ST1.close();
				}
	     }
	   }catch(Exception ex) { 
		   System.out.println("SearchError: "+ex.getMessage()); 
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
	   return list;
	  }
   
   public FreeBoardBean freeBoardDelete(int data) {
	   FreeBoardBean fbb = new FreeBoardBean();
	   try {
		msg="delete from board_fr where fr_no = " + data;
		System.out.println("msg " + msg);
		CN=DBConnection.getConnection();
		ST=CN.createStatement();
		ST.executeUpdate(msg);
	   }catch(Exception ex) {
		   System.out.println("DeleteError: "+ex.getMessage());
		}finally {
			try {
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
	return fbb;	   
	 }//end
   
   public FreeBoardBean freeBoardEditRequest(int data) {
	   FreeBoardBean fbb = new FreeBoardBean();
	   try {
		   msg="select * from board_fr where fr_no = " + data;
		   CN=DBConnection.getConnection();
		   ST=CN.createStatement();
		   RS=ST.executeQuery(msg);
		   RS.next();
		   
		   	fbb.setFr_no(RS.getInt("fr_no"));
			fbb.setFr_title(RS.getString("fr_title"));
			fbb.setFr_content(RS.getString("fr_content"));
			fbb.setFr_date(RS.getString("fr_date"));
			fbb.setFr_count(RS.getInt("fr_count"));
			fbb.setFr_id(RS.getString("fr_id"));
			 
	   } catch(Exception ex) {
		   System.out.println("EditRequestError: " + ex.getMessage());
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
	   return fbb;
   }
   
   public void freeBoardEditUpdate(int data , String fr_title, String fr_content) {
	   try {
		   msg="update board_fr set fr_title = '"+ fr_title +"', fr_content = '"+ fr_content +"' where fr_no = " + data;
		   CN=DBConnection.getConnection();
		   ST=CN.createStatement();
		   ST.executeUpdate(msg);
		   System.out.println("db ������Ʈ����");
	   } catch(Exception ex) {
		   System.out.println("EditUpdateError: " + ex.getMessage());
	   }finally {
			try {
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
   }
   
   public void freeBoardCountUpdate(int data) {
	   try {
		   msg="update board_fr set fr_count = fr_count +1 where fr_no = " + data;
		   CN=DBConnection.getConnection();
		   ST=CN.createStatement();
		   ST.executeUpdate(msg);
		   System.out.println("db ������Ʈ����");
	   } catch(Exception ex) {
		   System.out.println("EditUpdateError: " + ex.getMessage());
	   }finally {
			try {
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
   }
}


