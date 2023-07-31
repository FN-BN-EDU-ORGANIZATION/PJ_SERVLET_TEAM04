package Domain.Common.Dao;

import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

public interface AddressDao {

	//무슨 시 인지 검색
	Set<String> selectNamesLevel1() throws SQLException;

	//무슨 구 인지 검색
	Set<String> selectNamesLevel2(String a) throws SQLException;

	//무슨 동 인지 검색
	Set<String> selectNamesLevel3(String a, String b) throws SQLException;

	//좌표값 생성기
	Map<String, Object> getcoordinate(String a, String b, String c) throws SQLException;

}