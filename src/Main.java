import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Theater theater = new Theater();

        System.out.println("Welcome to Omni Theater\n");
        System.out.println("This week, we are showing these movies:\n----------------------------------------------------\n");
        for (Movie movie: theater.getMovies()) {
            System.out.println(movie.getName());
        }
        Scanner input = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\n----------------------------------------------------\n");
            System.out.println("1.) Purchase tickets");
            System.out.println("2.) Swap tickets");
            System.out.println("3.) End the day\n");

            System.out.print("How may I help you today?: ");

            choice = input.nextInt();

            switch(choice){
                case 1:
                    buyTickets(theater);
                    break;

                case 2:
                    swapTickets(theater);
                    break;

                case 3:
                    endTheDay(theater);
                    break;
            }
        } while (choice != 3);
    }

    public static void buyTickets(Theater theater){
        Scanner input = new Scanner(System.in);
        int movieChoice;
        int screeningChoice;
        int quantity;

        List<Movie> movies = theater.getMovies();

        System.out.println("\nWe are currently showing these movies: \n");
        for (int i = 0; i < movies.size(); i++) {
            System.out.println(String.format("%s.) %-30.30s %s", i + 1, movies.get(i).getName(),
                    movies.get(i).getScreenings().toString().replace("[", "").replace("]", "")));

        }

        System.out.print("\nWhich movie would you like to see?: ");
        movieChoice = input.nextInt();
        Movie movieTemp = movies.get(movieChoice - 1);

        for (int i = 0; i < movieTemp.getScreenings().size(); i++) {
            System.out.print(String.format("\n%s.) %-10s %s tickets left",i + 1, movieTemp.getScreenings().get(i), movieTemp.getScreenings().get(i).getTicketsAvailable()));
        }

        System.out.print(String.format("\nWhich screening of %s would you like to see?: ", movieTemp.getName()));
        screeningChoice = input.nextInt() - 1;

        System.out.print("\nHow many tickets would you like to purchase?: ");
        quantity = input.nextInt();

        if (movieTemp.getScreenings().get(screeningChoice).getTicketsAvailable() < quantity){
            System.out.println("Sorry, we don't have that many tickets available");
        }
        else {
            movieTemp.getScreenings().get(screeningChoice).updateTicketsAvailable(quantity * -1);
            movieTemp.getScreenings().get(screeningChoice).updateTicketsSold(quantity);
            System.out.println(String.format("\nYour tickets for %s at %s have been reserved. Enjoy the movie!", movieTemp.getName(), movieTemp.getScreenings().get(screeningChoice)));
        }
    }

    public static void swapTickets(Theater theater){
        Scanner input = new Scanner(System.in);
        int movie1Choice;
        int movie2Choice;
        int screening1Choice;
        int screening2Choice;
        int quantity;

        List<Movie> movies = theater.getMovies();

        System.out.println("\nWe are currently showing these movies: \n");
        for (int i = 0; i < movies.size(); i++) {
            System.out.println(String.format("%s.) %-30.30s %s", i + 1, movies.get(i).getName(),
                    movies.get(i).getScreenings().toString().replace("[", "").replace("]", "")));

        }

        System.out.print("\nWhich movie do you have tickets to see?: ");
        movie1Choice = input.nextInt();
        Movie movie1Temp = movies.get(movie1Choice - 1);

        for (int i = 0; i < movie1Temp.getScreenings().size(); i++) {
            System.out.print(String.format("\n%s.) %-10s %s tickets left",i + 1, movie1Temp.getScreenings().get(i), movie1Temp.getScreenings().get(i).getTicketsAvailable()));
        }

        System.out.print(String.format("\nWhich screening of %s were the tickets for?: ", movie1Temp.getName()));
        screening1Choice = input.nextInt() - 1;

        for (int i = 0; i < movies.size(); i++) {
            System.out.println(String.format("%s.) %-30.30s %s", i + 1, movies.get(i).getName(),
                    movies.get(i).getScreenings().toString().replace("[", "").replace("]", "")));

        }

        System.out.print("\nWhich movie would you like to see instead?: ");
        movie2Choice = input.nextInt();
        Movie movie2Temp = movies.get(movie2Choice - 1);

        for (int i = 0; i < movie2Temp.getScreenings().size(); i++) {
            System.out.print(String.format("\n%s.) %-10s %s tickets left",i + 1, movie2Temp.getScreenings().get(i), movie2Temp.getScreenings().get(i).getTicketsAvailable()));
        }

        System.out.print(String.format("\nWhich screening of %s would you like to see?: ", movie2Temp.getName()));
        screening2Choice = input.nextInt() - 1;

        System.out.print("How many tickets would you like to swap?: ");
        quantity = input.nextInt();

        if (movie1Temp.getScreenings().get(screening1Choice).getTicketsSold() < quantity){
            System.out.println("It seems you entered the wrong amount. Please try again.");
        }
        else {
            movie1Temp.getScreenings().get(screening1Choice).updateTicketsSold(-1 * quantity);
            movie2Temp.getScreenings().get(screening2Choice).updateTicketsSold(quantity);

            movie1Temp.getScreenings().get(screening1Choice).updateTicketsAvailable(quantity);
            movie2Temp.getScreenings().get(screening2Choice).updateTicketsAvailable(-1 * quantity);

            System.out.println(String.format("\nYour tickets for %s at %s have been swapped for %s at %s. Enjoy the movie!\n",
                    movie1Temp.getName(), movie1Temp.getScreenings().get(screening1Choice),
                    movie2Temp.getName(), movie2Temp.getScreenings().get(screening2Choice)));
        }
    }

    public static void endTheDay(Theater theater){
        List<Movie> movies = theater.getMovies();
        List<Screening> screen1 = new ArrayList<>();
        List<Screening> screen2 = new ArrayList<>();
        List<Screening> screen3 = new ArrayList<>();
        List<Screening> screen4 = new ArrayList<>();
        List<Screening> screen5 = new ArrayList<>();

        int screen1Total = getTotalForScreen(screen1);
        int screen2Total = getTotalForScreen(screen2);
        int screen3Total = getTotalForScreen(screen3);
        int screen4Total = getTotalForScreen(screen4);
        int screen5Total = getTotalForScreen(screen5);
        int totalSales = screen1Total + screen2Total + screen3Total + screen4Total + screen5Total;

        for (Movie movie : movies) {
            for (Screening screening : movie.getScreenings()) {
                if (screening.getScreen() == 1){
                    screen1.add(screening);
                }
                if (screening.getScreen() == 2){
                    screen2.add(screening);
                }
                if (screening.getScreen() == 3){
                    screen3.add(screening);
                }
                if (screening.getScreen() == 4){
                    screen4.add(screening);
                }
                if (screening.getScreen() == 5){
                    screen5.add(screening);
                }
            }
            //System.out.println(String.format("%s sold %s tickets and had %s screenings.", ));
        }

        System.out.println("\n----------------------------------------------------\n");
        if (totalSales > 20){
            System.out.println("Today was a profitable day for Omni Theatre!\n");
        }
        System.out.println(String.format("We sold %s tickets today.\n", totalSales));

        System.out.println(String.format("Screen One\t\t\t\t\t\t%s tickets sold", screen1Total));
        for (Screening screening : screen1) {
            System.out.println(String.format("\t%-30.30s %s tickets sold out of %s",
                    screening.getMovie().getName(), screening.getTicketsSold(),
                    screening.getTicketsSold() + screening.getTicketsAvailable()));
        }

        System.out.println(String.format("\nScreen Two\t\t\t\t\t\t%s tickets sold", screen2Total));
        for (Screening screening : screen2) {
            System.out.println(String.format("\t%-30.30s %s tickets sold out of %s",
                    screening.getMovie().getName(), screening.getTicketsSold(),
                    screening.getTicketsSold() + screening.getTicketsAvailable()));
        }

        System.out.println(String.format("\nScreen Three\t\t\t\t\t%s tickets sold", screen3Total));
        for (Screening screening : screen3) {
            System.out.println(String.format("\t%-30.30s %s tickets sold out of %s",
                    screening.getMovie().getName(), screening.getTicketsSold(),
                    screening.getTicketsSold() + screening.getTicketsAvailable()));
        }

        System.out.println(String.format("\nScreen Four\t\t\t\t\t\t%s tickets sold", screen4Total));
        for (Screening screening : screen4) {
            System.out.println(String.format("\t%-30.30s %s tickets sold out of %s",
                    screening.getMovie().getName(), screening.getTicketsSold(),
                    screening.getTicketsSold() + screening.getTicketsAvailable()));
        }
        System.out.println(String.format("\nScreen Five\t\t\t\t\t\t%s tickets sold", screen5Total));
        for (Screening screening : screen5) {
            System.out.println(String.format("\t%-30.30s %s tickets sold out of %s",
                    screening.getMovie().getName(), screening.getTicketsSold(),
                    screening.getTicketsSold() + screening.getTicketsAvailable()));
        }

        System.out.println("\n----------------------------------------------------\n");
        System.out.println("System shutting down. See you tomorrow!");
    }

    /**
     * Returns the of tickets sold for a screen.
     *
     * @param screenings the screenings for the screen
     * @return the number of tickets sold
     */
    private static int getTotalForScreen(List<Screening> screenings){
        int result = 0;

        for(Screening screening : screenings){
            result += screening.getTicketsSold();
        }
        return result;
    }

}