package main.java.vo;

public class AccountBean {
	private String flag ;
	private String transferTo;		// Transfer to email.
	private int amount;				// amount 	
	private String transferFrom;	// Transfer FROM ObjectID (unique) Account.
	private String personName;
	
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getTransferTo() {
		return transferTo;
	}
	public void setTransferTo(String transferTo) {
		this.transferTo = transferTo;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getTransferFrom() {
		return transferFrom;
	}
	public void setTransferFrom(String transferFrom) {
		this.transferFrom = transferFrom;
	}
	
	
}
