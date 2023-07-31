package Domain.Common.Dto;

public class profiledto {
		private String userid;
		private String pw;
		private String addr;
		private String role;
		//Getter setter 
		
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
		public String getAddr() {
			return addr;
		}
		public void setAddr(String addr) {
			this.addr = addr;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
		
		
		//toString 
		@Override
		public String toString() {
			return "profiledto [userid=" + userid + ", pw=" + pw + ", addr=" + addr + ", role=" + role + "]";
		}
		//디폴트 생성자
		public profiledto() {}
		//생성자
		public profiledto(String userid, String pw, String addr, String role) {
			super();
			this.userid = userid;
			this.pw = pw;
			this.addr = addr;
			this.role = role;
		}
		
		
}
