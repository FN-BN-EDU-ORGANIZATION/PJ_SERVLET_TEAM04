package Controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Domain.Common.Service.SaysService;

public class SaysController implements SubController{
	private SaysService SS;
	public SaysController() {
		 SS = SaysService.getInstance();
	}
	

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
			try {
				SS.getSaysService();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
	}

}
