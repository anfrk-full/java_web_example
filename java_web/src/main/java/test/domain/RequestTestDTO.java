package test.domain;

public class RequestTestDTO {
	private String id, pwd;

	public RequestTestDTO() {
		super();
	}

	public RequestTestDTO(String id, String pwd) {
		super();
		this.id = id;
		this.pwd = pwd;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public String toString() {
		return "TestDTO [id=" + id + ", pwd=" + pwd + "]";
	}
	
	
}
