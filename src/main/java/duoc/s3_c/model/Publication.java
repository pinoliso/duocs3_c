package duoc.s3_c.model;

import java.util.List;

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

    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL)
    private List<Comment> comments;
    
    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL)
    private List<Rating> ratings;

    public List<Comment> getComments() {
        return this.comments;
    }

    public List<Rating> getRatings() {
        return this.ratings;
    }

}