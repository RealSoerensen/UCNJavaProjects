package model;

/**
 * Class for LPs.
 * 
 * @author Patrick Sørensen
 * @version initial draft version
 */
public class LP {
    // instance variables
    private long barcode;
    private String title;
    private String artist;
    private String year;

    /**
     * Constructor for objects of class LP
     * 
     * @param barcode
     * @param title
     * @param artist
     * @param year
     */
    public LP(long barcode, String title, String artist, String year) {
        // initialise instance variables
        this.barcode = barcode;
        this.title = title;
        this.artist = artist;
        this.year = year;
    }

    /**
     * returns the barcode of the LP.
     * 
     * @return the barcode
     */
    public long getBarcode() {
        return barcode;
    }

    /**
     * returns the title of the LP.
     * 
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * returns the artist of the LP.
     * 
     * @return the artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * returns the year of the LP.
     * 
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * sets the barcode of the LP.
     * 
     * @param barCode
     */
    public void setBarcode(long barcode) {
        this.barcode = barcode;
    }

    /**
     * sets the title of the LP.
     * 
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * sets the artist of the LP.
     * 
     * @param artist
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * sets the year of the LP.
     * 
     * @param year
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * Prints the infomation of the LP.
     */
    public void printInfo() {
        System.out.println("Bar code: " + getBarcode());
        System.out.println("Title: " + getTitle());
        System.out.println("Kunster: " + getArtist());
        System.out.println("Udgivelsesår: " + getYear());
    }
}
