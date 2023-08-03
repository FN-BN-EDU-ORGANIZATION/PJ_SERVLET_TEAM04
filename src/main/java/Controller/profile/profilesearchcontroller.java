package Controller.profile;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import Controller.SubController;
import Domain.Common.Dto.profiledto;
import Domain.Common.Service.profileservice;
import Domain.Common.Service.profileserviceImp;

public class profilesearchcontroller implements SubController{
		private profileservice service = profileserviceImp.getInstance();
		
		
		@Override
		public void execute(HttpServletRequest req, HttpServletResponse resp) {
			System.out.println("profileSearchController execute");
		
		List<profiledto> list = null;
		try {
		
			list = service.getalluser(req);
			
			
			// JAVA -> JSON 변환
			ObjectMapper objectMapper = new ObjectMapper();
	        String jsonConverted = objectMapper.writeValueAsString(list);
			
			// 4 View로 전달			
			resp.setContentType("application/json");
			PrintWriter out = resp.getWriter();
			out.print(jsonConverted);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
		
	}
