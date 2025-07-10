import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    static MovieCollection movieDatabase = new MovieCollection();

    public static void main(String[] args) {
        try {
            movieDatabase.loadFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // movieDatabase.showMovies();

        Scanner sc = new Scanner(System.in);
        int input = 0;

        while (true) {
            showOptions();
            System.out.print("Your input: ");
            input = sc.nextInt();
            sc.nextLine();

            if (input == 1) {
                System.out.print("Movie title: ");
                String title = sc.nextLine();
                List<Movie> movies = movieDatabase.getMovies().stream().filter(m -> m.getTitle().equalsIgnoreCase(title)).collect(Collectors.toList());
                MovieCollection.showMovies(movies);

            } else if (input == 2) {
                System.out.println("Year of release: ");
                int yor = sc.nextInt();
                sc.nextLine();
                List<Movie> movies = movieDatabase.getMovies().stream().filter(m -> m.getYearOfRelease() == yor).collect(Collectors.toList());
                MovieCollection.showMovies(movies);

            } else if (input == 3) {
                System.out.print("Genre: ");
                String genre = sc.nextLine();
                List<Movie> movies = movieDatabase.getMovies().stream().filter(m -> m.getGenre().contains(genre)).collect(Collectors.toList());
                MovieCollection.showMovies(movies);

            } else if (input == 4) {
                System.out.print("Production Company: ");
                String productionCompany = sc.nextLine();
                List<Movie> movies = movieDatabase.getMovies().stream().filter(m -> m.getProductionCompany().equalsIgnoreCase(productionCompany)).collect(Collectors.toList());
                MovieCollection.showMovies(movies);

            } else if (input == 5) {
                System.out.println("Range: \nStart: ");
                int start = sc.nextInt();
                System.out.print("End: ");
                int end = sc.nextInt();
                sc.nextLine();
                List<Movie> movies = movieDatabase.getMovies().stream().filter(m -> m.getRunningTime() <= end && m.getRunningTime() >= start).collect(Collectors.toList());
                MovieCollection.showMovies(movies);

            } else if (input == 6) {
                List<Movie> movies = movieDatabase.getMovies().stream().sorted(Comparator.comparing(Movie::getRevenue).reversed()).collect(Collectors.toList()).subList(0, 10);
                MovieCollection.showMovies(movies);

            } else if (input == 7) {

            } else if (input == 8) {
                System.out.println("Good Bye!");
                break;
            } else {
                System.out.println("Invalid Input!");
            }
        }
    }

    public static void showOptions() {
        System.out.println("1) Search By Movie Title");
        System.out.println("2) Search By Release Year");
        System.out.println("3) Search By Genre");
        System.out.println("4) Search By Production Company");
        System.out.println("5) Search By Running Time");
        System.out.println("6) List of Top 10 Movies");
        System.out.println("7) List of Production Companies and the Count of their Produced Movies");
        System.out.println("8) Exit");
    }

    public static Movie getMovie(String title)
    {
        List<Movie> movies = movieDatabase.getMovies();
        for (Movie movie : movies)
        {
            if (movie.getTitle().equalsIgnoreCase(title)) return movie;
        }
        return null;
    }
}
