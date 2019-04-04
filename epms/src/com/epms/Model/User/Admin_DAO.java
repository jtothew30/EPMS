package com.epms.Model.User;

import com.epms.Model.DBConnection.DBConnection;

public class Admin_DAO extends GlobalVariable{
	
	public Admin_DAO(){
		CN = DBConnection.getConnection();
	}
	
	public int adminCheck(String id, String pw) {
		int result = 0;
		try {
			String msg = "select count(*) as acnt from admin where ad_id=? and ad_pw=?";
			PST = CN.prepareStatement(msg);
			PST.setString(1, id);
			PST.setString(2, pw);
			RS = PST.executeQuery();
			if(RS.next()) {
				result = RS.getInt("acnt");
			}
			System.out.println("adminCheck success");
		}catch(Exception e) {
			System.out.println("admin check error : "+ e.toString());
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
		return result;
	}
	
	
}
