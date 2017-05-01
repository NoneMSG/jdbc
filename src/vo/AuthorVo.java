package vo;

public class AuthorVo {
	private Long no ;
	private String name;
	private String bio ;
	
	public final Long getNo() {
		return no;
	}
	public final void setNo(Long no) {
		this.no = no;
	}
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final String getBio() {
		return bio;
	}
	public final void setBio(String bio) {
		this.bio = bio;
	}
	
	@Override
	public String toString() {
		return "AuthorVo [no=" + no + ", name=" + name + ", bio=" + bio + "]";
	}
	
	

}
