package com.epms.Model.DBConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class GlobalVariable {
	public Connection CN; 
	public Statement ST, ST3 ; 
	public PreparedStatement PST; 
	public CallableStatement CS; 
	public ResultSet RS;
	public String msg;
	
	public int Gtotal=27, Stotal=7, Rtotal=0;
}//GlobalVariable
