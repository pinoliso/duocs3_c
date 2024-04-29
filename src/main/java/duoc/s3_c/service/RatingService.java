package duoc.s3_c.service;

import duoc.s3_c.model.Rating;
import duoc.s3_c.repository.RatingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {
    
    @Autowired
    private RatingRepository ratingRepository;

    public Rating createRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    public Optional<Rating> getRatingById(Long id) {
        return ratingRepository.findById(id);
    }

    public Rating updateRating(Long id, Rating updatedRating) {
        if (ratingRepository.existsById(id)) {
            updatedRating.setId(id); 
            return ratingRepository.save(updatedRating);
        } else {
            return null; 
        }
    }

    public void deleteRating(Long id) {
        ratingRepository.deleteById(id);
    }
}
