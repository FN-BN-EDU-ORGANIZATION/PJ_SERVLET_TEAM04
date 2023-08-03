package Domain.Common.Dao;

import java.util.ArrayList;
import java.util.List;

import Domain.Common.Dto.LoginDto;

public class LoginDaoImpl extends ConnectionPool implements LoginDao{

	//싱글톤
		private static LoginDao instance;
		public static LoginDao getInstance() {
			if(instance==null)
				instance=new LoginDaoImpl();
			return instance;
		}
		 
		private LoginDaoImpl(){
			
		}
	
	
	@Override
	public int insert(LoginDto dto) throws Exception {
		DBprepar=DBcon.prepareStatement("insert into tbl_profile values(?,?,?,?)");
		DBprepar.setString(1, dto.getUserid());
		DBprepar.setString(2, dto.getPw());
		DBprepar.setString(3,dto.getCity());
		DBprepar.setString(4, dto.getRole());
		int result=DBprepar.executeUpdate();
		DBprepar.close();
		
		return result;
	}

	@Override
	public List<LoginDto> select() throws Exception {
		List<LoginDto> list = new ArrayList();
		LoginDto dto=null;
		DBprepar=DBcon.prepareStatement("select * from tbl_profile");
		DBresult=DBprepar.executeQuery();
		if(DBresult!=null)
		{
			while(DBresult.next()) {
				dto=new LoginDto();
				dto.setUserid(DBresult.getString("userid"));
				dto.setPw(null);
				dto.setCity(DBresult.getString("city"));
				dto.setRole(DBresult.getString("role"));
				list.add(dto);
			}
			DBresult.close();
		}
		DBprepar.close();
			
		return list;
	}

	@Override
	public LoginDto select(String userid) throws Exception {
		LoginDto dto=null;
		DBprepar=DBcon.prepareStatement("select * from tbl_profile where userid=?");
		DBprepar.setString(1, userid);
		
		DBresult=DBprepar.executeQuery();
		
		try {
		if(DBresult!=null&&DBresult.isBeforeFirst())
		{
			DBresult.next();
			dto=new LoginDto();
			dto.setUserid(DBresult.getString("userid"));
			dto.setPw(DBresult.getString("pw"));
			dto.setCity(DBresult.getString("city"));
			dto.setRole(DBresult.getString("role"));	
			DBresult.close();
		}
		}catch(Exception e) {
			throw new Exception("이거 rs 없는거다..");
		}
		
		DBprepar.close();
			
		return dto;
	}

	@Override
	public int update(LoginDto dto) throws Exception {
		DBprepar=DBcon.prepareStatement("update tbl_profile set pw=?,addr=?,role=? where userid=?");
		DBprepar.setString(1, dto.getPw());
		DBprepar.setString(2, dto.getCity());
		DBprepar.setString(3,dto.getRole());
		DBprepar.setString(4, dto.getUserid());
		int result=DBprepar.executeUpdate();
		DBprepar.close();
		
		return result;
	}

	@Override
	public int delete(String userid) throws Exception {
		DBprepar=DBcon.prepareStatement("delete from tbl_profile where userid=?");
		DBprepar.setString(1, userid);	
		int result=DBprepar.executeUpdate();
		DBprepar.close();
		
		return result;
	}

	
	
	
	
	
}
