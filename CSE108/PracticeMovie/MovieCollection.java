import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class MovieCollection
{
    private static final String INPUT_FILE_NAME = "src/movies.txt";

    private List<Movie> movies;

    public MovieCollection()
    {
        movies = new LinkedList<>() ;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public void loadFromFile() throws Exception
    {
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String [] values = line.split(",");
            movies.add(new Movie(values));
        }
        br.close();
    }

    public static void showMovie(Movie movie)
    {
        if (movie != null) System.out.println(movie);

        else System.out.println("No movie found");
    }

    public static void showMovies(List<Movie> movies)
    {
        if (movies == null)
        {
            System.out.println("No movie found");
            return;
        }
        for (Movie movie : movies)
        {
            System.out.println(movie);
        }
    }

    public void showMovies()
    {
        for (Movie movie : movies)
        {
            System.out.println(movie);
        }
    }
}
