package cn.com.shxt.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @描述: 公共数据访问交互工具
 * @作者：乐毅
 * @版本：0.9
 * @开发时间：2013-4-19上午11:20:59
 */

public class DbUtils {
	
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	String url = "jdbc:mysql://localhost:3308/jinxiu2";
	String username = "root";
	String password = "1224";
	
	/**
	 * @描述:用来在类中最先执行数据库的驱动加载
	 */
	static{
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			System.out.println("请检查驱动包是否导入因为找不到驱动类！");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @描述：建立数据库连接并返回给开发者连接对象
	 * @作者：乐毅
	 * @参数：@return
	 * @返回值：Connection
	 */
	public Connection getConn(){
		
		try{
			conn = DriverManager.getConnection(url,username,password);
			return conn;
		}catch(Exception e){
			System.out.println("对不起连接不上数据库,请检查URL OR USERNAME OR PASSWORD");
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * @描述：执行更新数据库的SQL语句
	 * @作者：乐毅
	 * @参数：@param sql
	 * @参数：@return
	 * @返回值：int = 影响表中记录行数  更新成功 = >0的整数  更新失败 = 0
	 */
	public int update(String sql){
		try{
			stmt = this.getConn().createStatement();
			return stmt.executeUpdate(sql);
		}catch(SQLException e){
			System.out.println("更新失败请检查sql语句的语法及格式");
			e.printStackTrace();
			return -1;
		}
	}
	
	/**
	 * @描述：执行查询数据库的SQL语句
	 * @作者：乐毅
	 * @参数：@param sql
	 * @参数：@return
	 * @返回值：List<Map<String,Object>>
	 */
	public List<Map<String,Object>> query(String sql){
	
		try{
			
			ArrayList<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			
			stmt = this.getConn().createStatement();
			rs = stmt.executeQuery(sql);
			//获得结构化的结果集对象包含表信息
			ResultSetMetaData rsmd = rs.getMetaData();
			//获得我所查询的表的总列数
			int columnCount = rsmd.getColumnCount();
			
			while(rs.next()){
				Map<String,Object> map = new HashMap<String,Object>();
				for(int i = 1; i<=columnCount; i++){
					String columnName = rsmd.getColumnName(i);
					Object columnValue = rs.getObject(columnName);
					map.put(columnName, columnValue);
				}
				list.add(map);
			}
			
			return list;
		}catch(SQLException e){
			System.out.println("查询失败请检查sql语句的语法及格式");
			e.printStackTrace();
			return null;
		}finally{
			guanBi();
		}
	}
	
	/**
	 * @描述：关闭三大对象
	 * @作者：乐毅
	 * @参数：
	 * @返回值：void
	 */
	public void guanBi(){
		
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(stmt != null){
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
