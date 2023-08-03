package Controller.Login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.SubController;

public class LogoutAddController implements SubController{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("LogoutController execute");
		
		//GET 요청 처리
				if(req.getMethod().equals("GET"))
				{
					try {
						req.getRequestDispatcher("/WEB-INF/view/Logout.jsp").forward(req, resp);
					
					} catch (ServletException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		
		
				/*
				 * try { HttpSession session = req.getSession(); session.invalidate();
				 * resp.sendRedirect(req.getContextPath()+"/LogIn.do"); } catch (IOException e)
				 * { // TODO Auto-generated catch block e.printStackTrace(); }
				 */
		
	}

}
