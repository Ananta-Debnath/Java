public class Book
{
    private int id;
    private String title;
    private boolean availability;

    Book(int id, String title, boolean availability)
    {
        this.id = id;
        this.title = title;
        this.availability = availability;
    }

    public int getID()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public boolean getAvailability()
    {
        return availability;
    }

    public void setAvailability(boolean availability)
    {
        this.availability = availability;
    }

    public String toString()
    {
        String str = "Book Id: " + id + ", Title: " + title + ", Availability: " + availability;
        return str;
    }
}