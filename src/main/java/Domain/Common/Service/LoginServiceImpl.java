package Domain.Common.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import Domain.Common.Dao.LoginDao;
import Domain.Common.Dao.LoginDaoImpl;
import Domain.Common.Dto.LoginDto;
import Domain.Common.Service.Auth.Session;

public class LoginServiceImpl implements LoginService {
	//세션정보저장
		public Map<String,Session> sessionMap;
		
		private LoginDao dao;
		
		
		//싱글톤
		private static LoginService instance;
		public static LoginService getInstance() {
			if(instance==null)
				instance = new LoginServiceImpl();
			return instance;
		}
		//
		
		private LoginServiceImpl() {
			dao=LoginDaoImpl.getInstance();
			sessionMap=new HashMap();
		}
		
		
		//회원 조회하기(전체) - 사서
		@Override
		public List<LoginDto> memberSearch(String sid) throws Exception{
			
			String role = this.getRole(sid);
			
			if(role.equals("ROLE_MEMBER"))		
				return dao.select();
			return null;
		}
		//회원 조회하기(한명) - 사서
		@Override
		public LoginDto memberSearchOne(String role,String userid) throws Exception{
			if(role.equals("ROLE_MEMBER"))		
				return dao.select(userid);
			return null;
		}	
		
		
		//회원 조회하기(한 회원) - 로그인한 회원만 
		@Override
		public LoginDto memberSearch(String userid,String sid) throws Exception {
			Session session = sessionMap.get(sid);
			
			if(session!=null && session.getId().equals(userid))
				return dao.select(userid);
			
			return null;
		}
		//로그인
		@Override
		public boolean login(HttpServletRequest req) throws Exception{
			
			String userid = (String) req.getParameter("userid");
			String pw = (String) req.getParameter("pw");
			//1 ID/PW 체크 ->Dao 전달받은 id와 일치하는 정보를 가져와서 Pw일치 확인
			LoginDto dbDto = dao.select(userid);
			if(dbDto==null) {
				System.out.println("[ERROR] Login Fail... 아이디가 일치하지 않습니다");
				req.setAttribute("msg", "[ERROR] Login Fail... 아이디가 일치하지 않습니다");
				return false;
			}
			if(!pw.equals(dbDto.getPw())) {
				System.out.println("[ERROR] Login Fail... 패스워드가 일치하지 않습니다");
				req.setAttribute("msg", "[ERROR] Login Fail... 패스워드가 일치하지 않습니다");
				return false;
			}
			
			HttpSession session = req.getSession();
			session.setAttribute("ID", userid);
			session.setAttribute("ROLE", dbDto.getRole());
			return true;
		}
		
		//로그아웃
		@Override
		public boolean logout(String userid,String sid) {
			Session session =  sessionMap.get(sid);
			
			if( ! session.getId().equals(userid) ) {
				System.out.println("[ERROR] ID가 일치하지 않습니다.");
				return false;
			}
			sessionMap.remove(sid);
			return true;
		}
		
		@Override
		public String getRole(String sid) {
			Session session = sessionMap.get(sid);
			System.out.println("getRole's Session : " + session);
			if(session!=null)
				return session.getRole();
			
			return null;
		}
		
}
