package us.party2.crawler.bean;

public class BeanBT implements IBean {

	private String partyName;
	private String partyDate;	
	private String partyPlace;	
	private String partyImage;
	private String btId;
	
	public String getPartyName() {
		return partyName;
	}
	public String getPartyDate() {
		return partyDate;
	}
	public String getPartyPlace() {
		return partyPlace;
	}
	public String getPartyImage() {
		return partyImage;
	}
	public String getBtId() {
		return btId;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public void setPartyDate(String partyDate) {
		this.partyDate = partyDate;
	}
	public void setPartyPlace(String partyPlace) {
		this.partyPlace = partyPlace;
	}
	public void setPartyImage(String partyImage) {
		this.partyImage = partyImage;
	}
	public void setBtId(String btId) {
		this.btId = btId;
	}	
	
}
