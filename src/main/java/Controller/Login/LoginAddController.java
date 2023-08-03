package Controller.Login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.SubController;
import Domain.Common.Service.LoginService;
import Domain.Common.Service.LoginServiceImpl;

public class LoginAddController implements SubController {

	private LoginService service = LoginServiceImpl.getInstance();

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("LoginController execute");

		// GET 요청 처리
		if (req.getMethod().equals("GET")) {
			try {
				req.getRequestDispatcher("/WEB-INF/view/LogIn.jsp").forward(req, resp);

			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}

		// POST 요청 처리
		// 1 파라미터 추출
		String userid = (String) req.getParameter("userid");
		String pw = (String) req.getParameter("pw");
		System.out.println("LoginController paramtes : " + userid + " " + pw);

		try {
			// 2 입력값 검증
			if (userid.isEmpty() || pw.isEmpty()) {
				System.out.println("[ERROR] Data Validation Check Error!");
		
				req.getRequestDispatcher("/WEB-INF/view/LogIn.jsp").forward(req, resp);
				return;
			}
			// 3 서비스 실행
			boolean isLogin = false;

			isLogin = service.login(req);

			//4 View로 전달 
			if(isLogin)
			{
				//login.do 이동 - Forward
				resp.sendRedirect(req.getContextPath()+"/main.do");
			}
			else
			{
				//login.do 이동 - Forward
				req.getRequestDispatcher("/WEB-INF/view/LogIn.jsp").forward(req, resp);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
