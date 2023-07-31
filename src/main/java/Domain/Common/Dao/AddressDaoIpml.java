package Domain.Common.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AddressDaoIpml extends ConnectionPool implements AddressDao{
	//주소 검색 결과를 저장하는 리스트
	private Set<String> set1 = null;
	private Set<String> set2 = null;
	private Set<String> set3 = null;
	
	//좌표값을 저장하는 변수
	private String NX;
	private String NY;
	
	private static AddressDao Instance;
	public static AddressDao getInstance(){
		if(Instance==null)
			Instance = new AddressDaoIpml();
		return Instance;
	}
	
	//데이터 베이스 연결
	private AddressDaoIpml() {
	}
	
	//무슨 시 인지 검색
	@Override
	public Set<String> selectNamesLevel1() throws SQLException {

		DBprepar = DBcon.prepareStatement("select 1단계 from tbl_api group by 1단계");
		DBresult = DBprepar.executeQuery();
		set1 = new HashSet();
		if (DBresult != null) {
			while (DBresult.next()) {
				set1.add(DBresult.getString("1단계"));
			}
			System.out.println(set1);
		}
		DBprepar.close();
		DBresult.close();
		return set1;
	}
	
	
	//무슨 구 인지 검색
	@Override
	public Set<String> selectNamesLevel2(String a) throws SQLException {
		set2 = new HashSet();
		if (a != null) {
			DBprepar = DBcon.prepareStatement("select 2단계 from tbl_api where 1단계 =?;");
			DBprepar.setString(1, a);
			DBresult = DBprepar.executeQuery();

			if (DBresult != null) {
				while (DBresult.next()) {
					set2.add(DBresult.getString("2단계"));
				}
				System.out.println(set2);
			}
			DBprepar.close();
			DBresult.close();
			return set2;
		} else {
			set2.add("");
			DBprepar.close();
			DBresult.close();
			return set2;
		}
	}
	
	
	//무슨 동 인지 검색
	@Override
	public Set<String> selectNamesLevel3(String a,String b) throws SQLException {
		set3 = new HashSet();
		if (a != null) {
			DBprepar = DBcon.prepareStatement("select 3단계 from tbl_api where 1단계 =? and 2단계 = ?;");
			DBprepar.setString(1, a);
			DBprepar.setString(2, b);
			DBresult = DBprepar.executeQuery();

			if (DBresult != null) {
				while (DBresult.next()) {
					set3.add(DBresult.getString("3단계"));
				}
				System.out.println(set3);
			}
			DBprepar.close();
			DBresult.close();
			return set3;
		} else {
			set3.add("");
			DBprepar.close();
			DBresult.close();
			return set3;
		}

	}
	//좌표값 생성기
	@Override
	public Map<String,Object> getcoordinate(String a,String b,String c) throws SQLException {
		DBprepar = DBcon.prepareStatement("select * from tbl_api where 1단계 =? and 2단계=? and 3단계 =?");
		DBprepar.setString(1, a);
		if(b!=null) {
		DBprepar.setString(2, b);
		}else {
		DBprepar.setString(2, "");	
		}
		if(c!=null) {
		DBprepar.setString(3, c);
		}else {
		DBprepar.setString(3, "");
		}
		DBresult = DBprepar.executeQuery();
		Map<String,Object> map = new HashMap();
		if(DBresult != null) {
			
			DBresult.next();
			map.put("NX",DBresult.getString("격자 X"));
			map.put("NY",DBresult.getString("격자 Y"));
			System.out.println(map.get("NX"));
			System.out.println(map.get("NY"));
			DBprepar.close();
			DBresult.close();
			return map;
			
			
		}
		System.out.println("좌표값을 받지 못했습니다.");
		DBprepar.close();
		DBresult.close();
		return null;
		
	}
}
