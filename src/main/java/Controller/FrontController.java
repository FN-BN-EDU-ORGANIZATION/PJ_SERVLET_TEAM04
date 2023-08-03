package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.Combobox.CCityController;
import Controller.Combobox.CCountyController;
import Controller.Combobox.CDongController;
import Controller.Combobox.CoordinateController;
import Controller.Login.LoginAddController;
import Controller.Login.LogoutAddController;
import Controller.Login.SignUpController;
import Controller.Login.SignUpPageController;
import Controller.api.ncst;
import Controller.profile.profilesearchcontroller;

public class FrontController extends HttpServlet{
	private Map<String, SubController> map = new HashMap();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		String projectPath=config.getServletContext().getContextPath();
		//시 구 동 컨트롤러
		map.put(projectPath+"/Combobox/ccity.do", new CCityController());
		map.put(projectPath+"/Combobox/ccounty.do", new CCountyController());
		map.put(projectPath+"/Combobox/cdong.do", new CDongController());
		//좌표 생성 컨트롤러
		map.put(projectPath+"/Combobox/Coordinate.do", new CoordinateController());
		//명언 생성기 컨트롤러
		map.put(projectPath+"/SaysController.do", new SaysController());
		
		map.put(projectPath+"/api/ncst.do", new ncst());
		
		//회원가입 컨트롤러
		
		map.put(projectPath+"/Login/register.do", new SignUpController());
		map.put(projectPath+"/Login/registerPage.do", new SignUpPageController());
		//조회 컨트롤러
		map.put(projectPath+"/profile/search.do",new profilesearchcontroller());
		
		//로그인 컨트롤러
		map.put(projectPath+"/Login/LogIn.do", new LoginAddController());
		map.put(projectPath+"/Login/Logout.do", new LogoutAddController());
		
		
		
		//메인 컨트롤러
		map.put(projectPath+"/main.do", new mainController());
	}



	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[Front]컨트롤러 연결 => "+req.getRequestURI());
		SubController controller = map.get(req.getRequestURI());
		controller.execute(req,resp);
	}




}
