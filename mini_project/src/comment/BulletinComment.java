package comment;

import java.sql.Date;

import app.LoginSystem;
import board.Bulletin;
import board.BulletinDAO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BulletinComment {
	private int commentNumber;
	private int boardNumber;
	private String id;
	private String content;
	private Date boardDate;

	public String toString() {
		return line() + annony() + content + "\n" + boardDate;
	}
	
	public String annony() {
		return "익명(" + commentNumber + ")\n";
	}

	public String line() {
		return "---------------------------------------\n";
	}
}
