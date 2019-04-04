package com.epms.Model.User;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.epms.Model.DBConnection.DBConnection;

public class Account_DAO extends GlobalVariable{
	
	public Account_DAO(){
		CN = DBConnection.getConnection();
	}
	
	public boolean epmsInsert(Account_Bean bean) {
		try {
			String msg = "insert into account values(?,?,?,?,?,?,?,sysdate,?,0,?)";
			PST = CN.prepareStatement(msg);
			PST.setString(1, bean.getId());
			PST.setString(2, bean.getPw());
			PST.setString(3, bean.getName());
			PST.setString(4, bean.getPhone());
			PST.setString(5, bean.getAddr1());
			PST.setString(6, bean.getAddr2());
			PST.setString(7, bean.getEmail());
			PST.setString(8, "false");
			PST.setString(9, "default");
			PST.executeUpdate();
			System.out.println("account insert success!!!");
			return true;
		}catch(Exception e) {
			System.out.println("insert error" + e.getMessage());
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
	
	public int epmsIdCheck(String id) {
		int result = 0;
		try {
			String msg = "select count(*) as cnt from account where ac_id='"+id+"'";
			ST = CN.createStatement();
			RS = ST.executeQuery(msg);
			if(RS.next()) {
				result = RS.getInt("cnt");
			}
		}catch(Exception e) {
			System.out.println("idCheck error : "+ e.toString());
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
	
	public String accountIdRefer(String name, String phone) {
		String result = null;
		try {
			String msg = "select ac_id from account where ac_name=? and ac_phone=?";
			PST = CN.prepareStatement(msg);
			PST.setString(1, name);
			PST.setString(2, phone);
			RS = PST.executeQuery();
			if(RS.next()) {
				result = RS.getString("ac_id");
			}
		}catch(Exception e) {
			System.out.println("accountIdRefer error : " + e.toString());
		}finally {
			try {
				if(RS != null) {
					RS.close();
				}
				if(PST != null) {
					PST.close();
				}
			}catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return result;
	}
	
	public String passRefer(String id, String email) {
		String result = null;
		try {
			String msg = "select ac_pw from account where ac_id=? and ac_email=?";
			PST = CN.prepareStatement(msg);
			PST.setString(1, id);
			PST.setString(2, email);
			RS = PST.executeQuery();
			if(RS.next()) {
				result = RS.getString("ac_pw");
			}
		}catch(Exception e) {
			System.out.println("passRefer error" + e.toString());
		}finally {
			try {
				if(RS != null) {
					RS.close();
				}
				if(PST != null) {
					PST.close();
				}
			}catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return result;
	}
	
	public JSONArray accountInfo(String id) {
		JSONArray array= new JSONArray();
		try {
			String msg = "select * from account where ac_id=?";
			PST = CN.prepareStatement(msg);
			PST.setString(1, id);
			RS = PST.executeQuery();
			if(RS.next()) {
				array.add(RS.getString("ac_id"));
				array.add(RS.getString("ac_pw"));
				array.add(RS.getString("ac_name"));
				array.add(RS.getString("ac_phone"));
				array.add(RS.getString("ac_addr1"));
				array.add(RS.getString("ac_addr2"));
				array.add(RS.getString("ac_email"));
				array.add(RS.getString("ac_date"));
				array.add(RS.getString("ac_type"));
				array.add(RS.getString("ac_point"));
				array.add(RS.getString("ac_area"));
			}
		}catch(Exception e) {
			System.out.println("accountInfo error" + e.toString());
		}finally {
			try {
				if(RS != null) {
					RS.close();
				}
				if(PST != null) {
					PST.close();
				}
			}catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return array;
	}
}
