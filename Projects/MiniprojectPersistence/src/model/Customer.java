package model;

public class Customer {
	private long customerId;
	private String name;
	private String address;
	private String phoneNo;

	public Customer(long customerId, String name, String address, String phoneNo) {
		this.customerId = customerId;
		this.name = name;
		this.address = address;
		this.phoneNo = phoneNo;
	}

	public Customer(){
	}

	public long getId() {
		return customerId;
	}

	public void setId(long id) {
		this.customerId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
}
