package com.epms.Model.User;

import com.epms.Model.DBConnection.DBConnection;

public class Login_DAO extends GlobalVariable{
	
	public Login_DAO(){
		CN = DBConnection.getConnection();
	}
	
	public String accountCheck(String id) {
		String result = "";
		
		try {
			String msg = "select ac_name from account where ac_id='"+id+"'";
			ST = CN.createStatement();
			RS = ST.executeQuery(msg);
			if(RS.next()) {
				result = RS.getString("ac_name");
			}
		}catch(Exception e) {
			System.out.println("loginCheck :" + e.toString());
		}finally {
			try {
				if(RS != null) {
					RS.close();
				}
				if(ST != null) {
					ST.close();
				}
			}catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return result;
	}
	
	public int acCount(String id, String pw) {
		int count = 0;
		try {
			String msg = "select count(*) as acnt from account where ac_id='"+id +"' and ac_pw='"+pw+"'";
			ST = CN.createStatement();
			RS = ST.executeQuery(msg);
			if(RS.next()) {
				count = RS.getInt("acnt");
			}
		}catch(Exception e) {
			System.out.println("account count error: "+ e.toString());
		}finally {
			try {
				if(RS != null) {
					RS.close();
				}
				if(ST != null) {
					ST.close();
				}
			}catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return count;
	}
	
	public boolean login(String id, String name) {
		try {
			String msg = "insert into login values(?,?)";
			PST = CN.prepareStatement(msg);
			PST.setString(1, id);
			PST.setString(2, name);
			PST.executeUpdate();
			System.out.println("login success !!!!!");
			return true;
		}catch(Exception e) {
			System.out.println("login error :" + e.toString());
			return false;
		}finally {
			try {
				if(PST != null) {
					PST.close();
				}
			}catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		
	}
	
	public boolean logOut(String id) {
		try {
			String msg = "delete from login where lg_id=?";
			PST = CN.prepareStatement(msg);
			PST.setString(1, id);
			PST.executeUpdate();
			System.out.println("logOut success !!!");
			return true;
		}catch(Exception e) {
			System.out.println("logOut error :" + e.toString());
		}finally {
			try {
				if(PST != null) {
					PST.close();
				}
			}catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return false;
	}
}
