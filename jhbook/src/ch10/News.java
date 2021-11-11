package ch10;

//do를 구현한 model
public class News {
	private int aid;
	private String title;
	private String img;
	private String date;
	private String cotent;
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCotent() {
		return cotent;
	}
	public void setCotent(String cotent) {
		this.cotent = cotent;
	}

}
