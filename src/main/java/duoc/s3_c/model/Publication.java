package duoc.s3_c.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;

@Data
@Entity
@Table(name = "publications")
public class Publication {
    private long id;
    private String title;
    private String text;
    private Date date;

    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL)
    private List<Comment> comments;
    
    @OneToMany(mappedBy = "publication", cascade = CascadeType.ALL)
    private List<Rating> ratings;

}