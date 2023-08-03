package Domain.Common.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import Domain.Common.Dao.profiledao;
import Domain.Common.Dao.profiledaoimpl;
import Domain.Common.Dto.profiledto;

public class profileserviceImp implements profileservice {
	
	private profiledao dao = profiledaoimpl.getInstance();
	
	
	//싱글톤
	private static profileservice instance;
	public static profileservice getInstance() {
		if(instance==null)
			instance = new profileserviceImp();
		return instance;
	}
	//유저 조회하기
	@Override
	public List<profiledto> getalluser(HttpServletRequest req) throws Exception {
		System.out.println("profileservice's getalluser");
		List<profiledto> result = (List<profiledto>)dao.select();
		return dao.select();
		
	}
	//유저 삭제하기
	@Override
	public int removealluser(String userid) throws Exception{
		return dao.delete(userid);
		
	}


	
	
}
