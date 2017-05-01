package vo;

public class BookVo{
	private Long no;
	private String title;
	private Long price;
	private Long authorNo;
	@Override
	public String toString() {
		return "BookVo [no=" + no + ", title=" + title + ", price=" + price + ", authorNo=" + authorNo + "]";
	}
	public final Long getNo() {
		return no;
	}
	public final void setNo(Long no) {
		this.no = no;
	}
	public final String getTitle() {
		return title;
	}
	public final void setTitle(String title) {
		this.title = title;
	}
	public final Long getPrice() {
		return price;
	}
	public final void setPrice(Long price) {
		this.price = price;
	}
	public final Long getAuthorNo() {
		return authorNo;
	}
	public final void setAuthorNo(Long authorNo) {
		this.authorNo = authorNo;
	}
}
