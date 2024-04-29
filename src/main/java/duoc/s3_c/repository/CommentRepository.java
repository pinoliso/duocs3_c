package duoc.s3_c.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import duoc.s3_c.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
