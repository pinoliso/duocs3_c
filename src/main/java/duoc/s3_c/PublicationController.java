package duoc.s3_c;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import java.text.DecimalFormat;

@RestController
public class PublicationController {

    private ArrayList<Publication> publications = new ArrayList<Publication>();

    public PublicationController() {
        publications.add(new Publication(1, "Nueva Casa", "Se han comprado una casa los Mesas en Melipilla.", new Date(), List.of(new Comment(1, "Hermosa la casa."), new Comment(2, "Muy Amplia y con buen comedor.")), List.of(4, 5, 3, 1)));
        publications.add(new Publication(2, "Nueva Ley 2342", "Se ha publicado la nueva ley 2342, que habla de.", new Date(), List.of(new Comment(3, "Cuando se aplica la ley?"), new Comment(4, "Sería ideal deja el enlace de la Ley.")), List.of(3, 2, 3, 3)));
        publications.add(new Publication(3, "Aplicación de impuesto a la belleza", "El impuesto a la belleza se impone por la región metropolitana.", new Date(), List.of(new Comment(5, "Que pasa si soy feo?"), new Comment(6, "Se busca referencia de la escala de belleza para catalogar.")), List.of(4, 1, 2)));
    }

    @GetMapping("/publications")
    public ArrayList<Publication> getPublications() {
        System.out.println("Respondiendo publications");
        return publications;
    }

    @GetMapping("/publications/{id}")
    public Publication getPublicationById(@PathVariable Long id) {
        for (Publication publication : publications) {
            if (publication.getId() == id) {
                System.out.println("Respondiendo publications " + id);
                return publication;
            }
        }
        return null;
    }

    @GetMapping("/publications/{id}/average_rating")
    public Map<String, Object> getAverageRatingByPublicationId(@PathVariable Long id) {

        Double average = 0.0;

        for (Publication publication : publications) {
            if (publication.getId() == id) {
                ArrayList<Integer> ratings = publication.getRatings();
                int total = 0;
                for(int rating: ratings) {
                    total += rating;
                }
                average = total / (double) ratings.size();
                break;
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("average", formatNumber(average));
        System.out.println("Respondiendo average_rating " + id);
        return response;
    }

    private String formatNumber(Double number) {
        DecimalFormat df = new DecimalFormat("0.0");
        return df.format(number);
    }
}
