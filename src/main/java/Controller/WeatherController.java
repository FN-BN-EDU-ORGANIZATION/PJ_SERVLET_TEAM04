package Controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import Domain.Common.Service.WeatherService;
import Domain.Common.Service.WeatherServiceImpl;

public class WeatherController implements SubController {
	private WeatherService WS;
	public Map<String, Object> ncst=null;
	public WeatherController() {
		WS = WeatherServiceImpl.getInstance();
	}


	
	@Override
	public Map<String,Object> execute(String serviceStr,Map<String,Object> Rp) throws SQLException{
		if(serviceStr.equals("/city")) {
			Set<String> city = new HashSet();
			city = WS.nameLvl1();
			Map<String,Object> result = new HashMap();
			result.put("result", city);
			return result;
		}
		else if(serviceStr.equals("/county")) {
			Set<String> county = new HashSet();
			county = WS.nameLvl2((String)Rp.get("city"));
			Map<String,Object> result = new HashMap();
			result.put("result", county);
			return result;
		}
		else if(serviceStr.equals("/dong")) {
			Set<String> dong = new HashSet();
			dong = WS.nameLvl3((String)Rp.get("city"), (String)Rp.get("county"));
			Map<String,Object> result = new HashMap();
			result.put("result", dong);
			return result;
		}else if(serviceStr.equals("/coordinate")){
			WS.coordinate((String)Rp.get("city"), (String)Rp.get("county"), (String)Rp.get("dong"));
			return null;
		}

		/* ************************************************************************************ */
		else if (serviceStr.equals("ncst")) {
			try {
				ncst=WS.ApiExplorerNcst();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return ncst;

		} else {
			System.err.println("잘못된 서비스 명령어 입니다.");
			return null;
		}

	}
}
