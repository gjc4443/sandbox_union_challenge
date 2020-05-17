import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Theater {

    private List<Movie> movies;
    private List<List<Screening>> screens;

    Theater(){
        screens = new ArrayList<>(5);
        screens.add(new ArrayList<Screening>());
        screens.add(new ArrayList<Screening>());
        screens.add(new ArrayList<Screening>());
        screens.add(new ArrayList<Screening>());
        screens.add(new ArrayList<Screening>());

        movies = createListOfMovies();
    }

    public List<Movie> getMovies() {
        return movies;
    }

    /**
     * Creates a list of movies using the movies.txt file
     *
     * @return a list of Movie objects
     */
    private List<Movie> createListOfMovies() {
        String data = "";

        try {
            File movieFile = new File("src/movies.txt");
            Scanner reader = new Scanner(movieFile);
            while (reader.hasNextLine()){
                data = reader.nextLine();
            }
            reader.close();
        } catch (FileNotFoundException e){
            System.out.println("File could not be found.");
            e.printStackTrace();
        }

        String[] movieTitles = data.split(",");
        ArrayList<Movie> movies = new ArrayList<Movie>();

        Random random = new Random();
        int count = random.nextInt( 10 - 5) + 10;

        for (int i = 0; i < count; i++) {
            int randomIndex = random.nextInt(28 - 0) + 0;
            Movie temp = new Movie(movieTitles[randomIndex]);
            temp.setScreenings(createScreenings(temp));
            if (!(movies.contains(temp))) {
                movies.add(temp);
            }
            else {
                i--;
            }
        }

        movies.removeIf(i -> i.getScreenings().isEmpty());

        return movies;
    }

    /**
     * Creates a list of movie screenings.
     *
     * @param movie
     * @return
     */
    private List<Screening> createScreenings(Movie movie){
        List<Screening> temp = new ArrayList<>();
        Random random = new Random();
        int count = random.nextInt(3 - 0) + 3;

        int hour = random.nextInt(10 -6) + 6;
        int minute = (random.nextInt(10 -0) + 10) * 5 ;

        for (int i = 0; i < count; i++) {
            int screen = random.nextInt(6 - 1) + 1;
            int capacity = random.nextInt(100 - 20) + 20;
            Time time = new Time(hour+(2*i), minute, 0);

            Screening screening = new Screening(movie, screen, time, 0, capacity);
            temp.add(screening);

            if(screen == 1){
                screens.get(0).add(screening);
            }
            if(screen == 2){
                screens.get(1).add(screening);
            }
            if(screen == 3){
                screens.get(2).add(screening);
            }
            if(screen == 4){
                screens.get(3).add(screening);
            }
            if(screen == 5){
                screens.get(4).add(screening);
            }
        }

        return temp;
    }
}
