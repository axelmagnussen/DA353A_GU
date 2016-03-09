package media;

public class Media {
	
	private int id;
	private boolean isBorrowed;
	private int year;
	private String title;

	public Media(int id, String title, int year, boolean isBorrowed) 
	{
		this.id = id;
		this.title = title;
		this.year = year;
		this.isBorrowed = isBorrowed;
	}
	
	public int getId() 
	{
		return this.id;
	}
	
	public String getTitle() 
	{
		return this.title;
	}
	
	public int getYear()
	{
		return this.year;
	}
	
	public boolean isBorrowed() 
	{
		return isBorrowed; 
	}
}
