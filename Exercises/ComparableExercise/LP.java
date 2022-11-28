package Exercises.ComparableExercise;

public class LP extends LPIF implements Comparable<LP> {
    private String title;
    private String artist;
    private int year;

    public LP(String title, String artist, int year) {
        this.title = title;
        this.artist = artist;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getYear() {
        return year;
    }

    public void printInfo() {
        System.out.println("Title: " + title + ", Artist: " + artist + ", Year: " + year);
    }

    @Override
    public int compareTo(LP o) {
        return this.title.compareTo(o.title);
    }
}
