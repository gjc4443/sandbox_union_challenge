import java.sql.Time;

public class Screening {

    private final Movie movie;
    private int screen;
    private Time time;
    private int ticketsSold;
    private int ticketsAvailable;

    Screening(Movie movie, int screen, Time time, int ticketsSold, int ticketsAvailable){
        this.movie = movie;
        this.screen = screen;
        this.time = time;
        this.ticketsSold = ticketsSold;
        this.ticketsAvailable = ticketsAvailable;
    }

    public Movie getMovie(){
        return movie;
    }

    public int getScreen() {
        return screen;
    }

    public int getTicketsSold() {
        return ticketsSold;
    }

    public int getTicketsAvailable() {
        return ticketsAvailable;
    }

    /**
     * Increases or decreases the number of tickets sold for the screening.
     *
     * @param change positive if increasing the number of tickets sold and negative if decreasing the number of tickets sold
     */
    public void updateTicketsSold(int change){
        ticketsSold += change;
    }

    /**
     * Increases or decreases the number of tickets available for the screening.
     *
     * @param change positive if increasing the number of tickets available and negative if decreasing the number of tickets available
     */
    public void updateTicketsAvailable(int change){
        ticketsAvailable += change;
    }

    @Override
    public String toString() {
        int hour = time.getHours();
        String minute = "";
        String suffix = "AM";
        if (time.getHours() > 12){
            hour = time.getHours() - 12;
            suffix = "PM";;
        }
        if (time.getHours() == 0 || time.getHours() == 12) {
            hour = 12;
            suffix = "PM";
        }
        if (time.getMinutes() < 10){
            minute = "0" + time.getMinutes();
        }
        else {
            minute = String.format("%s", time.getMinutes());
        }

        return String.format("%s:%s %s", hour, minute, suffix);
    }
}
