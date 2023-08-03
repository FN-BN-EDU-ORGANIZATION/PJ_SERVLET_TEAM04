package Controller.Login;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.SubController;
import Domain.Common.Dao.UserDAO;
import Domain.Common.Dto.Member;

public class SignUpController implements SubController {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // 클라이언트로부터 전송된 파라미터를 UTF-8로 인코딩해서 받도록 설정
            req.setCharacterEncoding("UTF-8");

            System.out.println(req.getParameter("userid"));
            System.out.println(req.getParameter("pw"));
            System.out.println(req.getParameter("city"));
            System.out.println(req.getParameter("role"));

            String userid = req.getParameter("userid");
            String pw = req.getParameter("pw");
            String city = req.getParameter("city");
            String role = req.getParameter("role");

            // Member 객체를 생성하고 회원 정보를 설정
            Member member = new Member(userid, pw, city, role);
            UserDAO dao = new UserDAO();

            // UserDAO 클래스를 이용하여 회원 정보를 데이터베이스에 저장
            if (dao.join(member)) {
                resp.getWriter().println("회원가입에 성공했습니다.");
                
            } else {
                resp.getWriter().println("회원가입에 실패했습니다. 다시 시도해주세요.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            // IOException을 처리하기 위한 코드 작성
        }
    }
}
