package board;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bulletin {
	private int boardNumber;
	private String id;
	private String title;
	private String content;
	private Date boardDate;
	
	@Override
	public String toString() {
		return boardNumber + "|" + title + "\n" + content + "\n" + boardDate + " | " + id() + "\n" + line();
	}
	
	private String id() {
		return "익명";
	}
	
	private String line() {
		return "ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ";
	}
}
