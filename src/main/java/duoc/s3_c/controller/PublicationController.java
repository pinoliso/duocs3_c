package duoc.s3_c.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.dao.DataAccessException; 

import duoc.s3_c.model.Rating;
import duoc.s3_c.model.Comment;
import duoc.s3_c.model.Publication;
import duoc.s3_c.service.PublicationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.annotation.PostConstruct;

@RestController
public class PublicationController {

    private static final Logger log = LoggerFactory.getLogger(PublicationController.class);

    @Autowired
    private PublicationService publicationService;

    @PostConstruct
    public void initialize() {
        for (int i = 1; i <= 10; i++) {
            Publication publication = new Publication();
            publication.setTitle("Publicación " + i);
            publication.setText("Texto publicación " + i);
            
            List<Comment> comments = new ArrayList<>();
            for (int j = 1; j <= 3; j++) {
                Comment comment = new Comment();
                comment.setText("Comentario " + j + " para Publicación " + i);
                comment.setPublication(publication); 
                comments.add(comment); 
            }
            publication.setComments(comments);

            List<Rating> ratings = new ArrayList<>();
            Random random = new Random();
            for (int k = 1; k <= 5; k++) {
                Rating rating = new Rating();
                rating.setValue(random.nextInt(5) + 1); 
                rating.setPublication(publication); 
                ratings.add(rating); 
            }
            publication.setRatings(ratings);
            
            publicationService.createPublication(publication);
            
        }
    }

    @GetMapping("/publications")
    public ResponseEntity<?> getPublications() {
        log.info("GET /publications");
        try {
            return ResponseEntity.ok(publicationService.getAllPublications());
        } catch (DataAccessException e) {
            log.info("Error al acceder a la base de datos");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Error al acceder a la base de datos"));
        }
    }

    @GetMapping("/publications/{id}")
    public ResponseEntity<?> getPublicationById(@PathVariable Long id) {
        log.info("GET /publications/" + id);

        if (id == null || id <= 0) {
            log.info("Error de parámetro");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("El ID de la publicación no es válido"));
        }

        try {
            Optional<Publication> optionalPublication = publicationService.getPublicationById(id);
            if (!optionalPublication.isPresent()) {
                log.info("No se encontro el registro " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No se encontró ninguna publicación con el ID proporcionado"));
                
            } 

            Publication publication = optionalPublication.get();
            return ResponseEntity.ok(publication);
        } catch (DataAccessException e) {
            log.info("Error al acceder a la base de datos");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Error al acceder a la base de datos"));
        }
    }

    @GetMapping("/publications/{id}/average_rating")
    public ResponseEntity<?> getAverageRatingByPublicationId(@PathVariable Long id) {

        if (id == null || id <= 0) {
            log.info("Error de parámetro");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("El ID de la publicación no es válido"));
        }

        try {

            Optional<String> optionalAverage = publicationService.getPublicationAverageById(id);
            if (!optionalAverage.isPresent()) {
                log.info("No se encontro el registro " + id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("No se encontró ninguna publicación con el ID proporcionado"));
                
            } 

            return ResponseEntity.ok(new MessageResponse("Respondiendo average_rating " + optionalAverage.get()));  
        } catch (DataAccessException e) {
            log.info("Error al acceder a la base de datos");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("Error al acceder a la base de datos"));
        }
    }

    static class MessageResponse {
        private final String message;
    
        public MessageResponse(String message) {
            this.message = message;
        }
    
        public String getMessage() {
            return message;
        }
    }
}
