package Controller.Combobox;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Controller.SubController;
import Domain.Common.Service.WeatherService;
import Domain.Common.Service.WeatherServiceImpl;

public class CDongController implements SubController{
	private WeatherService WS = WeatherServiceImpl.getInstance();
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		Set<String> dong = new HashSet();
		try {
			dong = WS.nameLvl3(req.getParameter("city"), req.getParameter("ccounty"));
			ObjectMapper jmapper = new ObjectMapper();
			String jconvert = jmapper.writeValueAsString(dong);
			
			resp.setContentType("application/json");
			PrintWriter wout = resp.getWriter();
			wout.print(jconvert);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
	}

}
