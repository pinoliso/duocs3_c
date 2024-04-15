package duoc.s3_c.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Publication {
    private long id;
    private String title;
    private String text;
    private Date date;
    private ArrayList<Comment> comments;
    private ArrayList<Integer> ratings;

    public Publication(long id, String title, String text, Date date, List<Comment> comments, List<Integer> ratings) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;
		this.comments = new  ArrayList<Comment>();
        this.comments.addAll(comments);
        this.ratings = new ArrayList<Integer>();
        this.ratings.addAll(ratings);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<Integer> getRatings() {
        return ratings;
    }

    public void setRatings(ArrayList<Integer> ratings) {
        this.ratings = ratings;
    }
}