package com.epms.Model.Myinfo;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.epms.Model.DBConnection.DBConnection;
import com.epms.Model.DBConnection.GlobalVariable;
import com.epms.Model.Reservation.ReservationBean;


public class MyinfoDAO extends GlobalVariable{
   
   public MyinfoDAO() {
	   //CN=DBConnection.getConnection();
   }
 
   // my ongoing reservation list as guest
   public ArrayList<ReservationBean> myinfoGuestList(String id){
	   System.out.println("id:"+id);
	   ArrayList<ReservationBean> rblist = new ArrayList<ReservationBean>();
	   		try {
	   			msg = "select rownum rn, r.* from reservation r where r_guest='"+id+"' and r_status in ('예약완료', '예약중') order by r_request desc";
	   			CN=DBConnection.getConnection();
	   			ST=CN.createStatement();
	   			RS=ST.executeQuery(msg);		
	   			while(RS.next()==true) {
	   				ReservationBean rb = new ReservationBean();
	   				rb.setR_rn(RS.getInt("rn"));
	   				rb.setR_no(RS.getInt("r_no"));
	   				rb.setR_host(RS.getString("r_host"));
	   				rb.setR_address(RS.getString("r_address"));
	   				rb.setR_area(RS.getString("r_area"));
	   				rb.setR_from(RS.getString("r_from"));
	   				rb.setR_to(RS.getString("r_to"));
	   				rb.setR_status(RS.getString("r_status"));
	   				rblist.add(rb);
	   			}
	   		}catch(Exception e) {
	   			System.out.println("reservationSelect_guest error:"+e);
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
	   return rblist;
   }
   
// my ongoing reservation list as host
   public ArrayList<ReservationBean> myinfoHostList(String id, int start, int end){
	   System.out.println("id:"+id);
	   ArrayList<ReservationBean> rblist = new ArrayList<ReservationBean>();
	   		try {
	   			String x=" select * from ( " ;
	 		    String y=" select rownum rn, r.* from reservation r where r_host='"+id+"' and r_status in ('예약완료', '예약중')";  
	 		    String z=" order by r_request desc) where rn between "+start+" and "+end;
	 		    msg=x+y+z;  
	   			//msg = "select rownum rn, r.* from reservation r where r_host='"+id+"' and r_status in ('예약완료', '예약중') order by r_request desc";
	 		    CN=DBConnection.getConnection();
	 		    ST=CN.createStatement();
	   			RS=ST.executeQuery(msg);		
	   			while(RS.next()==true) {
	   				ReservationBean rb = new ReservationBean();
	   				rb.setR_rn(RS.getInt("rn"));
	   				rb.setR_no(RS.getInt("r_no"));
	   				rb.setR_guest(RS.getString("r_guest"));
	   				rb.setR_address(RS.getString("r_address"));
	   				rb.setR_area(RS.getString("r_area"));
	   				rb.setR_from(RS.getString("r_from"));
	   				rb.setR_to(RS.getString("r_to"));
	   				rb.setR_status(RS.getString("r_status"));
	   				rblist.add(rb);
	   			}
	   		}catch(Exception e) {
	   			System.out.println("reservationSelect_guest error:"+e);
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
	   return rblist;
   }
   
   public ArrayList<ReservationBean> myinfoPastList(String id, int start, int end){
	   System.out.println("id:"+id);
	   ArrayList<ReservationBean> rblist = new ArrayList<ReservationBean>();
	   		try {
	   		    String x=" select * from ( " ;
	 		    String y=" select rownum rn, r.* from reservation r where (r_host='"+id+"' and r_status in ('사용완료', '예약취소')) or (r_guest='"+id+"' and r_status in ('사용완료', '예약취소'))";  
	 		    String z=" order by r_lastdate desc) where rn between "+start+" and "+end;
	 		    msg=x+y+z;  
	 		    CN=DBConnection.getConnection();
	   			ST=CN.createStatement();
	   			RS=ST.executeQuery(msg);		
	   			while(RS.next()==true) {
	   				ReservationBean rb = new ReservationBean();
	   				rb.setR_rn(RS.getInt("rn"));
	   				rb.setR_no(RS.getInt("r_no"));
	   				rb.setR_guest(RS.getString("r_guest"));
	   				rb.setR_host(RS.getString("r_host"));
	   				rb.setR_address(RS.getString("r_address"));
	   				rb.setR_area(RS.getString("r_area"));
	   				rb.setR_from(RS.getString("r_from"));
	   				rb.setR_to(RS.getString("r_to"));
	   				rb.setR_statusdate(RS.getString("r_statusdate"));
	   				rb.setR_status(RS.getString("r_status"));
	   				rblist.add(rb);
	   			}
	   		}catch(Exception e) {
	   			System.out.println("reservationSelect_guest error:"+e);
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
	   return rblist;
   }
   
   public int myinfoHostCount(String id) {
	   int cnt=0;
	   try {
	 	msg="select count(*) as cnt from reservation r where r_host='"+id+"' and r_status in ('예약완료', '예약중')";
	 	CN=DBConnection.getConnection();
	 	ST=CN.createStatement();
	 	RS=ST.executeQuery(msg);
	 	RS.next() ;
	 	cnt=RS.getInt("cnt");
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
	   return cnt;
	  }
   
   
   public int myinfoPastCount(String id) {
	   int cnt=0;
	   try {
	 	msg="select count(*) as cnt from reservation r where (r_host='"+id+"' and r_status in ('사용완료', '예약취소')) or (r_guest='"+id+"' and r_status in ('사용완료', '예약취소'))";
	 	CN=DBConnection.getConnection();
	 	ST=CN.createStatement();
	 	RS=ST.executeQuery(msg);
	 	RS.next() ;
	 	cnt=RS.getInt("cnt");
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
	   return cnt;
	  }
   
   
   public void myinfoCancelOngoing(int no, String status) {
	   try {
		   if(status.equals("예약중")) {
			   msg="update reservation set r_statusdate=sysdate, r_lastdate=sysdate, r_status='예약취소', r_hostread = 'true', r_guestread='true' where r_no="+no;
		   }else{
			   msg="update reservation set r_statusdate=sysdate, r_lastdate=sysdate, r_status='예약취소', r_hostread = 'false', r_guestread='true' where r_no="+no;
		   }
		   CN=DBConnection.getConnection();
		   ST=CN.createStatement();
		   ST.executeUpdate(msg);
		  System.out.println("myinfoCancelOngoing success");
	   }catch(Exception e) {
		   System.out.println(e.getMessage());
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
   }
   
}

