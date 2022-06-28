package members;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
	private String name;
	private String departmentName;
	private int studentNumber;
	private String id;
	private String pwd;
	private int role;
	
	@Override
	public String toString() {
		return user()+ "\n이름:" + name + "\n학과:" + departmentName + "\n학번:" + studentNumber + "\n아이디:" + id + "\n비밀번호:" + pwd;
	}
	
	private String user() {
		if(role == 0) {
			return "[일반회원]";
		}else {
			return "[관리자]";
		}
	}
}
