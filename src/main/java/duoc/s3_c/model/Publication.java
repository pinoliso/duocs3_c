package duoc.s3_c.model;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

@Data
@Entity
@Table(name = "publications")
public class Publication {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;
    private String title;
    private String text;

    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments  = new ArrayList<>();
    
    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings  = new ArrayList<>();

    public List<Comment> getComments() {
        return this.comments;
    }

    public List<Rating> getRatings() {
        return this.ratings;
    }

}