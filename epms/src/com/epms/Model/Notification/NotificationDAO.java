package com.epms.Model.Notification;

import java.util.ArrayList;
import com.epms.Model.DBConnection.DBConnection;
import com.epms.Model.DBConnection.GlobalVariable;


public class NotificationDAO extends GlobalVariable{
   
   public NotificationDAO() {
	   //CN=DBConnection.getConnection();
   }
   
   public boolean notificationInsert(String id, String title, String content) { 
	   try {
		   msg="insert into board_nofi values(nofi_seq.nextval,?,?,?,sysdate,0)";
		   CN=DBConnection.getConnection();
		   PST = CN.prepareStatement(msg);
		   		PST.setString(1, id);
		   		PST.setString(2, title);
		   		PST.setString(3, content);
		  PST.executeUpdate();
		  System.out.println("notification insert success");
		  return true;
	   }catch(Exception e) {
		   System.out.println(e.getMessage());
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
   
   
   public ArrayList<NotificationBean>  notificationList( int start, int end){
	   ArrayList<NotificationBean> list=new ArrayList<NotificationBean>( );
	   try {
		   String x=" select * from ( " ;
		   String y=" select rownum rn, n.* from board_nofi n ";  
		   String z=" order by nofi_date desc) where rn between "+start+" and "+end;
		   msg=x+y+z;  
		   CN=DBConnection.getConnection();
		   ST=CN.createStatement();
		   RS=ST.executeQuery(msg);
	   
		   while(RS.next()==true) {
		      NotificationBean nb=new NotificationBean( ); 
		      nb.setNofi_rn(RS.getInt("rn"));
		 	  nb.setNofi_no(RS.getInt("nofi_no"));
		 	  nb.setNofi_id(RS.getString("nofi_id"));
		 	  nb.setNofi_title(RS.getString("nofi_title"));
		 	  nb.setNofi_content(RS.getString("nofi_content"));
		 	  nb.setNofi_date(RS.getString("nofi_date"));
		 	  nb.setNofi_cnt(RS.getInt("nofi_cnt"));
		 	  list.add(nb); 
		   }
	   }catch(Exception ex){System.out.println("Error: "+ex);}finally {
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
   

   public int notificationCount() {
	   int cnt=0;
	   try {
	 	msg="select count(*) as cnt from board_nofi";
	 	CN=DBConnection.getConnection();
	 	ST=CN.createStatement();
	 	RS=ST.executeQuery(msg);
	 	RS.next() ;
	 	cnt=RS.getInt("cnt");
	    }catch(Exception ex){System.out.println("Error: "+ex);
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
	   
	   return cnt;
	  }
   
   public NotificationBean notificationDetail(int no) {
	   NotificationBean nb = new NotificationBean();
	   try {
		    msg="update board_nofi set nofi_cnt=nofi_cnt+1 where nofi_no="+no;
		    CN=DBConnection.getConnection();
		    ST=CN.createStatement();
		    ST.executeUpdate(msg);
		   
		 	msg="select * from board_nofi where nofi_no="+no;
		 	ST=CN.createStatement();
		 	RS=ST.executeQuery(msg);
		 	RS.next() ;
		 	nb.setNofi_no(RS.getInt("nofi_no"));
		 	nb.setNofi_id(RS.getString("nofi_id"));
		 	nb.setNofi_title(RS.getString("nofi_title"));
		 	nb.setNofi_content(RS.getString("nofi_content"));
		 	nb.setNofi_date(RS.getString("nofi_date"));
		 	nb.setNofi_cnt(RS.getInt("nofi_cnt"));	
	    }catch(Exception ex){System.out.println("Error: "+ex);}
	   finally {
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
	   return nb;  
   }   
   
   public NotificationBean notificationSelct(int no) {
	   NotificationBean nb = new NotificationBean();
	   try {
		 	msg="select * from board_nofi where nofi_no="+no;
		 	CN=DBConnection.getConnection();
		 	ST=CN.createStatement();
		 	RS=ST.executeQuery(msg);
		 	RS.next() ;
		 	nb.setNofi_no(RS.getInt("nofi_no"));
		 	nb.setNofi_id(RS.getString("nofi_id"));
		 	nb.setNofi_title(RS.getString("nofi_title"));
		 	nb.setNofi_content(RS.getString("nofi_content"));
		 	nb.setNofi_date(RS.getString("nofi_date"));
		 	nb.setNofi_cnt(RS.getInt("nofi_cnt"));	
	    }catch(Exception ex){System.out.println("Error: "+ex);}
	   finally {
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
	   return nb;  
   }   
   
   public void notificationEdit(int no, String id, String title, String content) {
	   try {
		    msg="update board_nofi set nofi_id=?, nofi_title=?, nofi_content=? where nofi_no=?";
		    CN=DBConnection.getConnection();
		    PST=CN.prepareStatement(msg);
		    	PST.setString(1, id);
		    	PST.setString(2, title);
		    	PST.setString(3, content);
		    	PST.setInt(4, no);
		    PST.executeUpdate();
		    System.out.println("notificationEdit success");
	    }catch(Exception ex){System.out.println("Error: "+ex);}
	   finally {
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
   
   public void notificationDelete(int no) {
	   try {
		    msg="delete from board_nofi where nofi_no="+no;
		    CN=DBConnection.getConnection();
		    ST=CN.createStatement();
		    ST.executeUpdate(msg);
		    System.out.println("notificationDelete success");
	    }catch(Exception ex){System.out.println("Error: "+ex);}finally {
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


