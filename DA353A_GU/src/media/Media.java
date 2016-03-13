package media;

import java.util.Date;

/**
 * This class represents a Media object
 * @author Ninjakids
 */
public class Media {

	private int id;
	private boolean isBorrowed;
	private int year;
	private String title;
	private Date borrowDate;
	
	/**
	 * This is a constructor
	 * @param id
	 * @param title
	 * @param year
	 * @param isBorrowed
	 */
	public Media(int id, String title, int year, boolean isBorrowed) 
	{
		this.id = id;
		this.title = title;
		this.year = year;
		this.isBorrowed = isBorrowed;
	}
	/**
	 * Gets id
	 * @return id
	 */
	public int getId() 
	{
		return this.id;
	}
	
	/**
	 * Gets title
	 * @return Title
	 */
	public String getTitle() 
	{
		return this.title;
	}
	
	/**
	 * Gets year
	 * @return year
	 */
	public int getYear()
	{
		return this.year;
	}
	
	/**
	 * Sets borrowed to isBorrowed
	 * @param isBorrowed
	 */
	public void setBorrowed(boolean isBorrowed) 
	{
		this.isBorrowed = isBorrowed;
	}
	
	/**
	 * returns isBorrowed
	 * @return isBorrowed
	 */
	public boolean isBorrowed() 
	{
		return isBorrowed; 
	}
	
	/**
	 * Sets isBorrowed to true and sets borrowDate to borrowDate
	 * @param borrowDate
	 */
	public void setBorrowDate(Date borrowDate)
	{
		this.isBorrowed = true;
		this.borrowDate = borrowDate;
	}
	
	/**
	 * reutn borrowDate
	 * @return borrowDate
	 */
	public Date getBorrowDate()
	{
		return this.borrowDate;
	}
	
	/**
	 * Checks if book is book and if dvd is dvd
	 * @param obj
	 * @return type
	 */
	public String getType() 
	{
		String type = "";
		if(this instanceof Book)
		{
			type = "Book";
		}
		else if(this instanceof DVD)
		{
			type = "DVD";
		}
		return type;
	}
	
	/**
	 * Returns a appropriate string of a media object.
	 * @return string
	 */
	@Override
	public String toString() 
	{
		return "Media [id=" + id + ", isBorrowed=" + isBorrowed + ", year=" + year + ", title=" + title
				+ ", borrowDate=" + borrowDate + "]";
	}
}
