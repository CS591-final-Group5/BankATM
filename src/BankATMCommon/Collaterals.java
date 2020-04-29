package BankATMCommon;

public class Collaterals {
	
	private String username;
	private String cid;
	private String description;
	
	public Collaterals(String username, String cid, String description) {
		this.username = username;
		this.cid = cid;
		this.description = description;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
