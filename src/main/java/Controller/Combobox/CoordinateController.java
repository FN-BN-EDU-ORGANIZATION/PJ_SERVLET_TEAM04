package Controller.Combobox;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.SubController;
import Domain.Common.Service.WeatherService;
import Domain.Common.Service.WeatherServiceImpl;

public class CoordinateController implements SubController{
	private WeatherService WS = WeatherServiceImpl.getInstance();
	private String city = null;
	private String ccounty = null;
	private String dong = null;
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		//=======유효성 검사
		city = req.getParameter("city");
		ccounty = req.getParameter("ccounty");
		dong = req.getParameter("dong");
		
		System.out.println(city);
		System.out.println(ccounty);
		System.out.println(dong);
		//==================================
		
		
		try {
			 WS.coordinate(city,ccounty,dong);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
