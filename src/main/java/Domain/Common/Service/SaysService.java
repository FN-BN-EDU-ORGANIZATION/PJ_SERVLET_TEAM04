package Domain.Common.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import Domain.Common.Dao.SaysDao;

public class SaysService {
	private static SaysService Instance;
	public static SaysService getInstance() {
		if(Instance==null)
			Instance = new SaysService();
		return Instance;
	}
	private int rnum = 0;
	private SaysDao SD;
	
	private SaysService() {
		SD = SaysDao.getInstance();
	}
	public Map<String, Object> getSaysService() throws SQLException{
		Map<String, Object> map = new HashMap();
		Random RS = new Random();
		rnum = RS.nextInt(SD.getPDAuthor().size());
		System.out.println(rnum);
		map.put("PDAuthor", SD.getPDAuthor().get(rnum));
		map.put("PDsays", SD.getPDsays().get(rnum));
		System.out.println(map.get("PDsays"));
		return map;
	}


}
