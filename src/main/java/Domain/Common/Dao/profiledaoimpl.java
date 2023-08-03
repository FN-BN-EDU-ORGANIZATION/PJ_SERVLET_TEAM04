package Domain.Common.Dao;

import java.util.ArrayList;
import java.util.List;

import Domain.Common.Dto.profiledto;

public class profiledaoimpl extends ConnectionPool implements profiledao{
	

	//싱글톤 패턴
	private static profiledao instance;
	public static profiledao getInstance() {
		if(instance==null)
			instance=new profiledaoimpl();
		return instance;
	}
	
	private profiledaoimpl() {}
	
	
	//CRUD
	//All User select
	public List<profiledto> select() throws Exception {
		List<profiledto> list = new ArrayList();
		profiledto dto = null;
		DBprepar=DBcon.prepareStatement("select * from tbl_profile");
		DBresult=DBprepar.executeQuery();
		if(DBresult!=null)
		{ 
			while(DBresult.next()) {
				dto=new profiledto();
				dto.setUserid(DBresult.getString("userid"));
				dto.setPw(DBresult.getString("pw"));
				dto.setAddr(DBresult.getString("city"));
				dto.setRole(DBresult.getString("role"));
				list.add(dto);
		}
			DBresult.close();
		}
		DBprepar.close();
		return list;
	}
//	public List<profiledto> select(String keyword){
//		return null;
//	}
//	public List<profiledto> select(String keyfield,String keyword){
//		return null;
//	}

	//userid delete 
	public int delete(String userid) throws Exception{
		DBprepar=DBcon.prepareStatement("delete from tbl_profile where userid=?");
		DBprepar.setString(1,userid);
		int result=DBprepar.executeUpdate();
		DBprepar.close();
		return result;
	}
}
