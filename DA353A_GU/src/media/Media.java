package media;

import java.util.Date;

/**
 * This class represents a Media object.
 * @author Ninjakids
 * @since 2016-03-13
 */
public class Media {

	private int id;
	private boolean isBorrowed;
	private int year;
	private String title;
	private Date borrowDate;
	
	/**
	 * This is a constructor with arguments that define a media object.
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
	 * Gets id.
	 * @return id
	 */
	public int getId() 
	{
		return this.id;
	}
	
	/**
	 * Gets title.
	 * @return title
	 */
	public String getTitle() 
	{
		return this.title;
	}
	
	/**
	 * Gets year.
	 * @return year
	 */
	public int getYear()
	{
		return this.year;
	}
	
	/**
	 * Sets borrowed to the value of isBorrowed.
	 * @param isBorrowed
	 */
	public void setBorrowed(boolean isBorrowed) 
	{
		this.isBorrowed = isBorrowed;
	}
	
	/**
	 * Returns true if media is borrowed.
	 * @return isBorrowed
	 */
	public boolean isBorrowed() 
	{
		return isBorrowed; 
	}
	
	/**
	 * Sets isBorrowed to true and sets date of loan to borrowDate.
	 * @param borrowDate
	 */
	public void setBorrowDate(Date borrowDate)
	{
		this.isBorrowed = true;
		this.borrowDate = borrowDate;
	}
	
	/**
	 * Gets date when media was borrowed.
	 * @return borrowDate
	 */
	public Date getBorrowDate()
	{
		return this.borrowDate;
	}
	
	/**
	 * Checks if book is book and if dvd is dvd.
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
