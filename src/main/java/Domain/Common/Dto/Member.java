package Domain.Common.Dto;

public class Member {
	private String userid;
	private String pw;
	private String city;
	private String role;
	public Member() {}
	public Member(String userid, String pw, String city, String role) {
		super();
		this.userid = userid;
		this.pw = pw;
		this.city = city;
		this.role = role;
	}
	@Override
	public String toString() {
		return "Member [userid=" + userid + ", pw=" + pw + ", city=" + city + ", role=" + role + "]";
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	



	}

