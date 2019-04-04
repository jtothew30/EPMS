package com.epms.Model.Reservation;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.epms.Model.DBConnection.DBConnection;
import com.epms.Model.DBConnection.GlobalVariable;


public class ReservationDAO extends GlobalVariable{
   
   public ReservationDAO() {
	//   CN=DBConnection.getConnection();
   }
   
   // 예약신청
   public boolean reservationInsert(String guest, String host, String from, String to, String address, String area, String message) { 
	   ArrayList<String> list = this.reservationList(from, to, address);	// 예약 신청완료시 
	   if(!list.contains(area)) {
		   return false;
	   }
	   try {
		   msg="insert into reservation values(r_seq.nextval,?,?,?,?,?,to_date('" + from + "','yyyy-mm-dd hh24:Mi'),to_date('" + to + "','yyyy-mm-dd hh24:Mi'),sysdate,null,null,sysdate,'false','false',?)";
		   CN=DBConnection.getConnection();
		   PST = CN.prepareStatement(msg);
		   		PST.setString(1, guest);
		   		PST.setString(2, host);
		   		PST.setString(3, address);
		   		PST.setString(4, area);
		   		PST.setString(5, message);
		   		PST.setString(6, "예약중");
		  PST.executeUpdate();
		  System.out.println("reservation request success");
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
   
   // 지역 host id 가져오기
   public String reservationSearchHost(String area, String address) {
	      String host="";
	      try {
	         msg="select pap_host from parking_area_personal where pap_area='"+area+"'";
	         CN=DBConnection.getConnection();
	         ST=CN.createStatement();
	         RS=ST.executeQuery(msg);
	         RS.next();
	         host = RS.getString("pap_host");
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
	      return host;
	   }
   
   // 예약가능한 목록 출력
   public ArrayList<String> reservationList(String from, String to, String address){
	   ArrayList<String> list = new ArrayList<String>();
	   		try {
	   			System.out.println(from + " / " + to);
	   			msg = "select c.pap_area from parking_area_personal c where c.pap_address = '" + address + "' and c.pap_area not in ( select distinct p.pap_area from parking_area_personal p inner join reservation r on (( r.r_status ='예약중' or r.r_status = '예약완료') and p.pap_area = r.r_area and r.r_address='" + address + "' and ((r.r_from <= To_Date('"+from+"','yyyy-mm-dd hh24:mi') and r.r_to > To_Date('"+from+"','yyyy-mm-dd hh24:mi')) or (r.r_from < To_Date('"+to+"','yyyy-mm-dd hh24:mi') and r.r_to >= To_Date('"+to+"','yyyy-mm-dd hh24:mi'))))) order by c.pap_area asc";
	   			CN=DBConnection.getConnection();
	   			ST=CN.createStatement();
	   			RS=ST.executeQuery(msg);
	   			
	   			while(RS.next()==true) {
	   				list.add(RS.getString("pap_area"));
	   				System.out.println(RS.getString("pap_area"));
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
	   return list;
   }
   
   /*
   public int reservation_alarm_cnt(String id){
		int cnt=0;
		try {
			msg = "select count(*) cnt from reservation where (r_host='"+id+"' and r_status = '예약중') or ()";
			CN=DBConnection.getConnection();
			ST=CN.createStatement();
			RS=ST.executeQuery(msg);
			RS.next();
			cnt = RS.getInt("cnt");
		}catch(Exception e) {
			System.out.println("reservation_alarm_cnt error:"+e);
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
	}*/
   
 
   //자신에게 온 모든 알람 체크. host, guest로 id를 비교. 확인하지 않은 정보 출력
   public JSONObject reservationCheck(String id){ 
      JSONObject jsonObject = new JSONObject();
      JSONArray jsonArray = new JSONArray();
      
            try {
               msg = "select r_no,r_guest,r_host,r_address,r_area,r_content,r_from,r_to,r_request,r_status from reservation where (r_host ='"+id+"' and r_hostread='false') or  (r_guest='"+id+"' and r_guestread='false' and r_status in ('예약완료', '예약취소') ) order by r_lastdate desc";
               CN=DBConnection.getConnection();
               ST=CN.createStatement();
               RS=ST.executeQuery(msg);
               while(RS.next()==true) {
                  JSONObject json = new JSONObject();
                  json.put("no",RS.getInt("r_no"));
                  json.put("guest",RS.getString("r_guest"));
                  json.put("host",RS.getString("r_host"));
                  json.put("address",RS.getString("r_address"));
                  json.put("area",RS.getString("r_area"));
                  json.put("content",RS.getString("r_content"));
                  json.put("from",RS.getString("r_from"));
                  json.put("to",RS.getString("r_to"));
                  json.put("request",RS.getString("r_request"));
                  json.put("status",RS.getString("r_status"));
                  jsonArray.add(json);
               }
               jsonObject.put("list", jsonArray);
            }catch(Exception e) {
               System.out.println("reservationCheck error:"+e);
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
      return jsonObject;
   }
   
  //대여자가 승낙 선택 시. 승낙 시간 저장 / 상태를 예약완료로 변경
   public void reservationAgree(int no) { 
	   try {
		   msg="update reservation set r_agree=sysdate, r_lastdate=sysdate, r_status='예약완료', r_hostread='true' where r_no="+no;
		   CN=DBConnection.getConnection();
		   ST=CN.createStatement();
		   ST.executeUpdate(msg);
		  System.out.println("reservationAgree success");
	   }catch(Exception e) {
		   System.out.println(e.getMessage());
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
   
  //대여자가 거부 선택 시. 거부 시간 저장 / 상태를 예약취소로 변경
   public void reservationReject(int no) { 
	   try {
		   msg="update reservation set r_statusdate=sysdate, r_lastdate=sysdate, r_status='예약취소', r_hostread='true' where r_no="+no;
		   CN=DBConnection.getConnection();
		   ST=CN.createStatement();
		   ST.executeUpdate(msg);
		  System.out.println("reservationReject success");
	   }catch(Exception e) {
		   System.out.println(e.getMessage());
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
   
   //메시지 읽음 업데이트
   public void reservationMessageRead(int no, String type) {
	   try {
		   switch(type) {
		   case "guest":
			   msg="update reservation set r_guestread = 'true' where r_no="+no;
			   break;
		   case "host":
			   msg="update reservation set r_hostread = 'true' where r_no="+no;
			   break;
		   default:
		   }		  
		   CN=DBConnection.getConnection();
		   ST=CN.createStatement();
		   ST.executeUpdate(msg);
	   }catch(Exception e) {
		   System.out.println(e.getMessage());
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
   
   public String checkUserType(int no, String id) {
	   String type="";
	   try {
		   msg="select r_guest, r_host from reservation where r_no="+no;
		   CN=DBConnection.getConnection();
		   ST=CN.createStatement();
		   RS=ST.executeQuery(msg);
		   RS.next();
		   String g = RS.getString("r_guest");
		   String h = RS.getString("r_host");
		   if(g.equals(id))
			   type="guest";
		   if(h.equals(id))
			   type="host";
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
	   return type;
   }
   
}


