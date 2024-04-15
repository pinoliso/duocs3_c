package duoc.s3_c.service;

import duoc.s3_c.model.Publication;
import duoc.s3_c.repository.PublicationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Publication updatePublication(Long id, Publication updatedPublication) {
        if (publicationRepository.existsById(id)) {
            updatedPublication.setId(id); 
            return publicationRepository.save(updatedPublication);
        } else {
            return null; 
        }
    }

    public void deletePublication(Long id) {
        publicationRepository.deleteById(id);
    }
}
