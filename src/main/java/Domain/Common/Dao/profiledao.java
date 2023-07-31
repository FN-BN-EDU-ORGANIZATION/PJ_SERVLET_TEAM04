package Domain.Common.Dao;

import java.util.ArrayList;
import java.util.List;

import Domain.Common.Dto.profiledto;

//DB연결
public class profiledao extends ConnectionPool{

	
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
				dto.setAddr(DBresult.getString("addr"));
				dto.setRole(DBresult.getString("role"));
				list.add(dto);
		}
			DBresult.close();
		}
		DBprepar.close();
		return list;
	}
	public List<profiledto> select(String keyword){
		return null;
	}
	public List<profiledto> select(String keyfield,String keyword){
		return null;
	}
	//user update
	public int update(profiledto dto) throws Exception{
		DBprepar=DBcon.prepareStatement("update tbl_profile set userid=?,pw=?,addr=?,role=? where userid=?");
		DBprepar.setString(1,dto.getUserid() );
		DBprepar.setString(2,dto.getPw() );
		DBprepar.setString(3,dto.getAddr());
		DBprepar.setString(4,dto.getRole() );
		int result=DBprepar.executeUpdate();
		DBprepar.close();
		return result;
	}
	//userid delete 
	public int delete(String userid) throws Exception{
		DBprepar=DBcon.prepareStatement("delete from tbl_profile where userid=?");
		DBprepar.setString(1,userid);
		int result=DBprepar.executeUpdate();
		DBprepar.close();
		return result;
	}
	
}
