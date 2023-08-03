package Controller.Login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.SubController;

public class SignUpPageController implements SubController{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("회원가입 : "+req.getMethod());
		if(req.getMethod().equals("POST")) {
			try {
				req.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(req, resp);
				System.out.println("회원가입 페이지로 이동..");
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				System.out.println("회원가입 페이지로 이동실패..");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("회원가입 페이지로 이동실패..");
				e.printStackTrace();
			}
		}else {
			System.out.println("if 문 조차 안드갔음");
		}
		
	}

}
