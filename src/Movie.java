import java.util.List;

public class Movie {

    private final String name;
    private List<Screening> screenings;

    Movie(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Screening> getScreenings() {
        return screenings;
    }

    public void setScreenings(List<Screening> screenings) {
        this.screenings = screenings;
    }

    /**
     * Returns true if the object is the same or if the name of the object matches this movie's name.
     * @param obj the object being compared
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }

        if (!(obj instanceof Movie)){
            return false;
        }

        Movie movie = (Movie) obj;
        return movie.getName() == this.name;
    }
}
