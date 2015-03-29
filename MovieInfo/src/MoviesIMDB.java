
import java.awt.Component;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
MoviesIMDB-Class
------------------------

MoviesIMDB-Class is made purely for storing OMDb query-data. 

Authors: Mikko Pakkanen, Mikko Tella 
Date: 23.02.2015
**/
public class MoviesIMDB implements ListCellRenderer<Object> {
    private String movieName, movieYear, movieRated, movieReleased, movieRuntime,
            movieGenre, movieDirector, movieWriter, movieActors, moviePlot, 
            movieLanguage, movieCountry, movieAwards, moviePoster, movieMetascore,
            movieIMDBRating, movieIMDBVotes, movieIMDBid;

    public MoviesIMDB() {
    
    }

    public String getMoviePlot() {
        return moviePlot;
    }

    public void setMoviePlot(String moviePlot) {
        this.moviePlot = moviePlot;
    }
    
    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieYear() {
        return movieYear;
    }

    public void setMovieYear(String movieYear) {
        this.movieYear = movieYear;
    }

    public String getMovieRated() {
        return movieRated;
    }

    public void setMovieRated(String movieRated) {
        this.movieRated = movieRated;
    }

    public String getMovieReleased() {
        return movieReleased;
    }

    public void setMovieReleased(String movieReleased) {
        this.movieReleased = movieReleased;
    }

    public String getMovieRuntime() {
        return movieRuntime;
    }

    public void setMovieRuntime(String movieRuntime) {
        this.movieRuntime = movieRuntime;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public String getMovieWriter() {
        return movieWriter;
    }

    public void setMovieWriter(String movieWriter) {
        this.movieWriter = movieWriter;
    }

    public String getMovieActors() {
        return movieActors;
    }

    public void setMovieActors(String movieActors) {
        this.movieActors = movieActors;
    }

    public String getMovieLanguage() {
        return movieLanguage;
    }

    public void setMovieLanguage(String movieLanguage) {
        this.movieLanguage = movieLanguage;
    }

    public String getMovieCountry() {
        return movieCountry;
    }

    public void setMovieCountry(String movieCountry) {
        this.movieCountry = movieCountry;
    }

    public String getMovieAwards() {
        return movieAwards;
    }

    public void setMovieAwards(String movieAwards) {
        this.movieAwards = movieAwards;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    public String getMovieMetascore() {
        return movieMetascore;
    }

    public void setMovieMetascore(String movieMetascore) {
        this.movieMetascore = movieMetascore;
    }

    public String getMovieIMDBRating() {
        return movieIMDBRating;
    }

    public void setMovieIMDBRating(String movieIMDBRating) {
        this.movieIMDBRating = movieIMDBRating;
    }

    public String getMovieIMDBVotes() {
        return movieIMDBVotes;
    }

    public void setMovieIMDBVotes(String movieIMDBVotes) {
        this.movieIMDBVotes = movieIMDBVotes;
    }

    public String getMovieIMDBid() {
        return movieIMDBid;
    }

    public void setMovieIMDBid(String movieIMDBid) {
        this.movieIMDBid = movieIMDBid;
    }

    @Override
    public String toString() {
        return this.movieName + " (" + this.movieYear + ")";
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
