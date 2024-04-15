package duoc.s3_c.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import duoc.s3_c.model.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

}
