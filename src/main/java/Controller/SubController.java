package Controller;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SubController {

	void execute(HttpServletRequest req,HttpServletResponse resp);

}