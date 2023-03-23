package model;

/**
 * Write a description of class Dog here.
 *
 * @author MHI
 * @author Gianna
 * @version Feb. 2023
 */
public class Dog {
	private int id; // the auto-generated id
	private String chip; // the microchip identifying string
	private String name;
	private int feeYear;

	
	public Dog(int id, String chip, String name, int feeYear) {
		this.id = id;
		this.chip = chip;
		this.name = name;
		this.feeYear = feeYear;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getChip() {
		return chip;
	}

	public void setChip(String chipId) {
		this.chip = chipId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFeeYear() {
		return feeYear;
	}

	public void setFeeYear(int feeYear) {
		this.feeYear = feeYear;
	}

	
    public boolean isFeeDue(int year) {
        return year > feeYear;
    }
    
    
    public int dueYearsCount(int year) {
        return year - feeYear;
    }
	
	
	@Override
	public String toString() {
		return String.format("Dog [id=%d, chip=%s, name= %s, feeYear=%d]", id, chip, name, feeYear);
	}
	
}
