package org.fundacionjala.at04.moviesrestapi.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Movie class.
 */
@Document(collection = "movies")
public class Movie {
    @Id
    private String id;
    @NotNull
    @Size(min = 1)
    private String title;
    @NotNull
    @Size(min = 4)
    private String year;
    @NotNull
    private Double imDBScore;
    @NotNull
    @Size(min = 1)
    private String synopsis;

    /**
     * Public default constructor.
     */
    public Movie() {

    }

    /**
     * Overloaded constructor that assign the data values.
     *
     * @param title     movie title.
     * @param year      movie year.
     * @param imDBScore ImDB movie score.
     * @param synopsis  movie synopsis.
     */
    public Movie(String title, String year, Double imDBScore, String synopsis) {
        this.title = title;
        this.year = year;
        this.imDBScore = imDBScore;
        this.synopsis = synopsis;
    }

    /**
     * Accessor method for id variable.
     *
     * @return id variable value.
     */
    public String getId() {
        return id;
    }

    /**
     * Setter method for id variable
     *
     * @param id value
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Accessor method for title variable.
     *
     * @return title variable value.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter method for title variable.
     *
     * @param title value.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Accessor method for year variable.
     *
     * @return year variable value.
     */
    public String getYear() {
        return year;
    }

    /**
     * Setter method for year variable.
     *
     * @param year value.
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * Accessor method for imDBScore variable.
     *
     * @return imDBScore variable value.
     */
    public Double getImDBScore() {
        return imDBScore;
    }

    /**
     * Setter method for imDBScore variable.
     *
     * @param imDBScore value.
     */
    public void setImDBScore(Double imDBScore) {
        this.imDBScore = imDBScore;
    }

    /**
     * Accessor method for synopsis variable.
     *
     * @return synopsis variable value.
     */
    public String getSynopsis() {
        return synopsis;
    }

    /**
     * Setter method for synopsis variable.
     *
     * @param synopsis value.
     */
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    /**
     * Evaluate valid fields.
     *
     * @return boolean evaluation value.
     */
    public boolean invalidFields() {
        return title == null || year == null || imDBScore == null || synopsis == null;
    }
}
