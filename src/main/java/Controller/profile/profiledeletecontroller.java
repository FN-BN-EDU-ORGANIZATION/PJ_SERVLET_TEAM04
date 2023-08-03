package Controller.profile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.SubController;
import Domain.Common.Service.profileservice;
import Domain.Common.Service.profileserviceImp;

public class profiledeletecontroller implements SubController{
	private profileservice service= profileserviceImp.getInstance();
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("BookDeleteController execute");
		
	}
}
