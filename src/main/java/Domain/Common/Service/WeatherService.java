package Domain.Common.Service;

import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

public interface WeatherService {

	//================================================================
	Set<String> nameLvl1() throws SQLException;

	Set<String> nameLvl2(String a) throws SQLException;

	Set<String> nameLvl3(String a, String b) throws SQLException;

	Map<String, Object> coordinate(String a, String b, String c) throws SQLException;
	//==================================================================

	Map<String, Object> ApiExplorerNcst() throws Exception;

	void ApiExplorerFcst() throws Exception;

}