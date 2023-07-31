package Controller.api;

import Controller.SubController;
import Domain.Common.Service.WeatherService;
import Domain.Common.Service.WeatherServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


public class ncst implements SubController {
    String PTY=null;
    String T1H=null;
    String REH=null;
    String RN1=null;
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        WeatherService WS = WeatherServiceImpl.getInstance();

        try {
            Map<String ,Object> ncst=WS.ApiExplorerNcst();
            PTY = (String) ncst.get("PTY");
            T1H = (String) ncst.get("T1H");
            REH = (String) ncst.get("REH");
            RN1 = (String) ncst.get("RN1");

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("확인용: "+PTY+" "+T1H+" "+REH+" "+RN1);

        // JSP 페이지로 값을 전달
        req.setAttribute("T1H", T1H);
        req.setAttribute("REH", REH);
        req.setAttribute("RN1", RN1);

        String jsonData = "{\"T1H\":\"" + T1H + "\",\"REH\":\"" + REH + "\",\"RN1\":\"" + RN1 + "\"}";
        try {
			resp.getWriter().write(jsonData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
