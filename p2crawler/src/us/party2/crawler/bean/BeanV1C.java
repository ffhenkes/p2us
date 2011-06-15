package us.party2.crawler.bean;

import java.util.Date;

public class BeanV1C implements IBean {
	private String partyName;
	private String partyDate;	
	private PartyPlace partyPlace;
	private String partyDetail;
	private String partyImage;
	private PartyType partyType;	

	public String getPartyName() {
		return partyName;
	}
	public String getPartyDate() {
		return partyDate;
	}
	public PartyPlace getPartyPlace() {
		return partyPlace;
	}
	public String getPartyDetail() {
		return partyDetail;
	}
	public String getPartyImage() {
		return partyImage;
	}
	public PartyType getPartyType() {
		return partyType;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public void setPartyDate(String date) {
		this.partyDate = date;
	}
	public void setPartyPlace(PartyPlace partyPlace) {
		this.partyPlace = partyPlace;
	}
	public void setPartyDetail(String partyDetail) {
		this.partyDetail = partyDetail;
	}
	public void setPartyImage(String partyImage) {
		this.partyImage = partyImage;
	}
	public void setPartyType(PartyType partyType) {
		this.partyType = partyType;
	}

}

//is it really needed another bean?
class PartyType{
	
}

class PartyPlace implements IBean{
	private String placeName;
	private String placeAddress;
	
	public String getPlaceName() {
		return placeName;
	}
	public String getPlaceAddress() {
		return placeAddress;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public void setPlaceAddress(String placeAddress) {
		this.placeAddress = placeAddress;
	}	
}