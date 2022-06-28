package board;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
public class Notice {
	private int boardNumber;
	private String title;
	private String content;
	private Date boardDate;
	
	@Override 
	public String toString() {
		return line() + boardNumber + "|" + title + " " + boardDate + nullContent();
	}
	
	private String nullContent() {
		if(content == null) {
			return "";
		}else 
			return "\n\n" + content;
	}
	
	public String line() {
		return "ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ\n";
	}
}
