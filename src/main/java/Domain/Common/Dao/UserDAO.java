package Domain.Common.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Domain.Common.Dto.Member;

public class UserDAO extends ConnectionPool{
    public boolean join(Member member) {
        String query = "INSERT INTO tbl_profile VALUES(?,?,?,?)";
        try{
        	DBprepar = DBcon.prepareStatement(query);
        	DBprepar.setString(1, member.getUserid());
        	DBprepar.setString(2, member.getPw());
        	DBprepar.setString(3, member.getCity());
        	DBprepar.setString(4, member.getRole());

        	DBcon.setAutoCommit(false);
            int rowsAffected = DBprepar.executeUpdate(); // 데이터 삽입

            // 트랜잭션 처리
            if (rowsAffected > 0) {
            	DBcon.commit(); // 데이터 삽입이 성공하면 커밋
            	DBcon.setAutoCommit(true);
                return true;
            } else {
            	DBcon.rollback(); // 데이터 삽입이 실패하면 롤백
            	DBcon.setAutoCommit(true);
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
