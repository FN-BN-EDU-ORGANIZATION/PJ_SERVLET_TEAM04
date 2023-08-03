package Domain.Common.Dao;

import java.util.List;

import Domain.Common.Dto.profiledto;

public interface profiledao{
	
	//CRUD
	List<profiledto> select() throws Exception;
	
	int delete(String userid) throws Exception;
	
//	List<profiledto> select(String keyfield);
//	List<profiledto> select(String keyfield, String keyword);
	
	
	
}
