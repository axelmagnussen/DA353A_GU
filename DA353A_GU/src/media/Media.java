package media;

import java.util.Date;

public abstract class Media {

    private int id;
    private boolean isBorrowed;
    private int year;
    private String title;
    private Date borrowDate;

    public Media(int id, String title, int year, boolean isBorrowed) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.isBorrowed = isBorrowed;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public int getYear() {
        return this.year;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getBorrowDate() {
        return this.borrowDate;
    }

    @Override
    public String toString() {
        return "Media [id=" + id + ", isBorrowed=" + isBorrowed + ", year=" + year + ", title=" + title
                + ", borrowDate=" + borrowDate + "]";
    }
}
