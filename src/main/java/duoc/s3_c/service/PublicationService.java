package duoc.s3_c.service;

import duoc.s3_c.model.Publication;
import duoc.s3_c.model.Comment;
import duoc.s3_c.model.Rating;
import duoc.s3_c.repository.PublicationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.text.DecimalFormat;

@Service
public class PublicationService {
    
    @Autowired
    private PublicationRepository publicationRepository;

    public List<Publication> getAllPublications() {
        return publicationRepository.findAll();
    }

    public Publication createPublication(Publication publication) {
        return publicationRepository.save(publication);
    }

    public Optional<Publication> getPublicationById(Long id) {
        return publicationRepository.findById(id);
    }

    public Optional<Publication> updatePublication(Long id, Publication updatedPublication) {
        if (publicationRepository.existsById(id)) {
            updatedPublication.setId(id); 
            for(Comment comment: updatedPublication.getComments()) {
                comment.setPublication(updatedPublication);
            }
            for(Rating rating: updatedPublication.getRatings()) {
                rating.setPublication(updatedPublication);
            }
            return Optional.of(publicationRepository.save(updatedPublication));
        } else {
            return Optional.empty();
        }
    }

    public void deletePublication(Long id) {
        publicationRepository.deleteById(id);
    }

    public Optional<String> getPublicationAverageById(Long id) {
        Optional<Publication> optionalPublication = publicationRepository.findById(id);
        if (!optionalPublication.isPresent()) {
            return Optional.empty();
        } 
        
        Publication publication = optionalPublication.get();
        int total = 0;
        List<Rating> ratings = publication.getRatings();
        for (Rating rating : publication.getRatings()) {
            total += rating.getValue();
        }
        return Optional.of(formatNumber(total / (double) ratings.size()));
    }

    private String formatNumber(Double number) {
        DecimalFormat df = new DecimalFormat("0.0");
        return df.format(number);
    }
}
