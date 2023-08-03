package Domain.Common.Dao;

import java.util.List;

import Domain.Common.Dto.LoginDto;

public interface LoginDao {

	int insert(LoginDto dto) throws Exception;
	
	List<LoginDto> select() throws Exception;
	
	LoginDto select(String userid) throws Exception;
	
	int update(LoginDto dto) throws Exception;
	
	int delete(String userid) throws Exception;
	
}
