package Domain.Common.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import Domain.Common.Dto.profiledto;

public interface profileservice {

	//유저 조회하기
	List<profiledto> getalluser(HttpServletRequest req) throws Exception;

	//유저 삭제하기
	int removealluser(String userid) throws Exception;

}