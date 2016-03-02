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
 * @����: �������ݷ��ʽ�������
 * @���ߣ�����
 * @�汾��0.9
 * @����ʱ�䣺2013-4-19����11:20:59
 */

public class DbUtils {
	
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	String url = "jdbc:mysql://localhost:3308/jinxiu2";
	String username = "root";
	String password = "1224";
	
	/**
	 * @����:��������������ִ�����ݿ����������
	 */
	static{
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			System.out.println("�����������Ƿ�����Ϊ�Ҳ��������࣡");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @�������������ݿ����Ӳ����ظ����������Ӷ���
	 * @���ߣ�����
	 * @������@return
	 * @����ֵ��Connection
	 */
	public Connection getConn(){
		
		try{
			conn = DriverManager.getConnection(url,username,password);
			return conn;
		}catch(Exception e){
			System.out.println("�Բ������Ӳ������ݿ�,����URL OR USERNAME OR PASSWORD");
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * @������ִ�и������ݿ��SQL���
	 * @���ߣ�����
	 * @������@param sql
	 * @������@return
	 * @����ֵ��int = Ӱ����м�¼����  ���³ɹ� = >0������  ����ʧ�� = 0
	 */
	public int update(String sql){
		try{
			stmt = this.getConn().createStatement();
			return stmt.executeUpdate(sql);
		}catch(SQLException e){
			System.out.println("����ʧ������sql�����﷨����ʽ");
			e.printStackTrace();
			return -1;
		}
	}
	
	/**
	 * @������ִ�в�ѯ���ݿ��SQL���
	 * @���ߣ�����
	 * @������@param sql
	 * @������@return
	 * @����ֵ��List<Map<String,Object>>
	 */
	public List<Map<String,Object>> query(String sql){
	
		try{
			
			ArrayList<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			
			stmt = this.getConn().createStatement();
			rs = stmt.executeQuery(sql);
			//��ýṹ���Ľ���������������Ϣ
			ResultSetMetaData rsmd = rs.getMetaData();
			//���������ѯ�ı��������
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
			System.out.println("��ѯʧ������sql�����﷨����ʽ");
			e.printStackTrace();
			return null;
		}finally{
			guanBi();
		}
	}
	
	/**
	 * @�������ر��������
	 * @���ߣ�����
	 * @������
	 * @����ֵ��void
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
