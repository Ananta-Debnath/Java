import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie
{
    private String title;
    private int yearOfRelease;
    private List<String> genre;
    private int runningTime; // (in minutes)
    private String productionCompany;
    private long budget;
    private long revenue;

    {
        genre = new ArrayList<>();
    }

    public Movie(String[] str)
    {
        if (str.length != 9) throw new RuntimeException("Insufficient arguments");

        this.title = str[0];
        this.yearOfRelease = Integer.parseInt(str[1]);
        this.genre.add(str[2]);
        this.genre.add(str[3]);
        this.genre.add(str[4]);
        this.runningTime = Integer.parseInt(str[5]);
        this.productionCompany = str[6];
        this.budget = Integer.parseInt(str[7]);
        this.revenue = Integer.parseInt(str[8]);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public String getProductionCompany() {
        return productionCompany;
    }

    public void setProductionCompany(String productionCompany) {
        this.productionCompany = productionCompany;
    }

    public long getBudget() {
        return budget;
    }

    public void setBudget(long budget) {
        this.budget = budget;
    }

    public long getRevenue() {
        return revenue;
    }

    public void setRevenue(long revenue) {
        this.revenue = revenue;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", yearOfRelease=" + yearOfRelease +
                ", genre=" + genre +
                ", runningTime=" + runningTime +
                ", productionCompany='" + productionCompany + '\'' +
                ", budget=" + budget +
                ", revenue=" + revenue +
                '}';
    }
}
