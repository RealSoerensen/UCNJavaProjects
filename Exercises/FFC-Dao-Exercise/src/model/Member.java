package model;

import java.util.ArrayList;
import java.util.List;

/**

 * @author Gibe
 * @version Feb. 2023
 */
public class Member {
	private int id; //auto generated
	private String name;
	private String phone;
	private String email;
	
	private List<Dog> dogs;
	
	public Member(int id, String name, String phone, String email) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	
	public Member(String name, String phone, String email) {
		this.id = -1;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public boolean addDog(Dog dog) {
		return (dog != null) && dogs.add(dog);
	}
	
	public void setDogs(List<Dog> dogs) {
		this.dogs = new ArrayList<>(dogs);
	}

	
	public List<Dog> getDogs() {
		return dogs;
	}

	
	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", dogs=" + dogs + "]\n\n";
	}

	
}	
