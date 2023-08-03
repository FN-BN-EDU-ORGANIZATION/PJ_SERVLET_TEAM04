package Domain.Common.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import Domain.Common.Dto.LoginDto;

public interface LoginService {

	//회원 조회하기(전체) - 사서
	List<LoginDto> memberSearch(String sid) throws Exception;

	//회원 조회하기(한명) - 사서
	LoginDto memberSearchOne(String role, String userid) throws Exception;

	//회원 조회하기(한 회원) - 로그인한 회원만 
	LoginDto memberSearch(String userid, String sid) throws Exception;

	//로그인
	boolean login(HttpServletRequest req) throws Exception;

	//로그아웃
	boolean logout(String userid, String sid);

	String getRole(String sid);

}