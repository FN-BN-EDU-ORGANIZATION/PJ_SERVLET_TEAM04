package Domain.Common.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SaysDao extends ConnectionPool{
	private ArrayList<String> PDsays;
	private ArrayList<String> PDAuthor;
	
	private static SaysDao Instance;
	public static SaysDao getInstance(){
		if(Instance==null)
			Instance = new SaysDao();
		return Instance;
	}
	public ArrayList<String> getPDAuthor() throws SQLException {

		DBprepar = DBcon.prepareStatement("select proverb_author from today_proverb");
		DBresult = DBprepar.executeQuery();
		PDAuthor = new ArrayList();
		if (DBresult != null) {
			while (DBresult.next()) {
				PDAuthor.add(DBresult.getString("proverb_author"));
			}
			System.out.println(PDAuthor);
		}
		DBprepar.close();
		DBresult.close();
		return PDAuthor;
	}
	public ArrayList<String> getPDsays() throws SQLException {

		DBprepar = DBcon.prepareStatement("select proverb_say from today_proverb");
		DBresult = DBprepar.executeQuery();
		PDsays = new ArrayList();
		if (DBresult != null) {
			while (DBresult.next()) {
				PDsays.add(DBresult.getString("proverb_say"));
			}
			System.out.println(PDsays);
		}
		DBprepar.close();
		DBresult.close();
		return PDsays;
	}
	
	
}
