package model;

public class Dog {
    private String name;
    private int feeYear;
    
    public Dog (String name, int feeYear) {
        this.name = name;
        this.feeYear = feeYear;
    }
    
    public Dog (String name) {
        this.name = name;
    }
    
    public boolean isFeeDue(int year) {
        return year > feeYear;
//        boolean result = false;
//        if(year > this.feeYear) {
//            result = true;
//        }
//        return result;
    }
    
    
    public int dueYearsCount(int year) {
        return year - feeYear;
        //int result = year - feeYear;
        //return result;
    }
    
    public void setName (String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setFeeYear(int feeYear) {
        this.feeYear = feeYear;
    }
    
    public int getFeeYear() {
        return feeYear;
    }

	@Override
	public String toString() {
		return "Dog [name=" + name + ", feeYear=" + feeYear + "]";
	}
    
    
}