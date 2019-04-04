package com.epms.Model.Reservation;

import java.util.ArrayList;

import com.epms.Model.DBConnection.DBConnection;
import com.epms.Model.DBConnection.GlobalVariable;

public class ReservationBoardDAO extends GlobalVariable{
	 public ReservationBoardDAO() {
		   //CN=DBConnection.getConnection();
	 }
	 
	 public ArrayList<ReservationBoardBean> reservationBoard_Select(String area,String search,int start, int end) {
		 	ArrayList<ReservationBoardBean> bean = new ArrayList<>();
		 	try {
		 		msg = "select count(*) as cnt from parking_area where pa_address like '%" + area + "%' and pa_address like '%"+search+"%'";
		 		CN=DBConnection.getConnection();
		 		ST = CN.createStatement();
		 		RS = ST.executeQuery(msg);
		 		if(RS.next()) {
		 			int cnt = RS.getInt("cnt");
		 			System.out.println("cnt : " + cnt );
			 		if(cnt > 0) {
				 		msg = "select r.* from (select p.*, rownum as rn from (select * from parking_area order by pa_address asc) p where pa_address like '%" + area + "%' and pa_address like '%"+search+"%') r where rn between "+ start + " and " + end; 
				 		ST = CN.createStatement();
				 		RS = ST.executeQuery(msg);
				 		while(RS.next()) {
				 			ReservationBoardBean rbb = new ReservationBoardBean();
				 			rbb.setPa_address(RS.getString("pa_address"));
				 			rbb.setPa_lat(RS.getDouble("pa_lat"));
				 			rbb.setPa_lon(RS.getDouble("pa_lon"));
				 			rbb.setBoardCount(cnt);
				 			bean.add(rbb);
				 		}
			 		}
		 		}
		 	} catch(Exception e) {
		 		System.out.println(e.getMessage() + " / " + e.toString());
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
		 	
		 	return bean;
	 }
}
