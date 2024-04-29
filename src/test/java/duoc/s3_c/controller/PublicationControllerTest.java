package duoc.s3_c.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import duoc.s3_c.model.Publication;
import duoc.s3_c.service.PublicationService;

import java.util.List;

@WebMvcTest(PublicationController.class)
public class PublicationControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PublicationService publicationServiceMock;

    @Test
    public void testGetAllPublications() throws Exception {
        Publication publication1 = new  Publication();
        publication1.setTitle("Titanic");
        Publication publication2 = new  Publication();
        publication2.setTitle("Spiderman");
        List< Publication> publications = List.of(publication1, publication2);
        when(publicationServiceMock.getAllPublications()).thenReturn(publications);

        mockMvc.perform(MockMvcRequestBuilders.get("/publications"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.publicationList.[0].title").value("Titanic"))
            .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.publicationList.[1].title").value("Spiderman"));
    }

    @Test
    void savePublicationTest() throws Exception {
        Publication publicationToSave = new Publication();
        publicationToSave.setTitle("Sample Title");
        publicationToSave.setText("Sample Text");

        when(publicationServiceMock.createPublication(any(Publication.class))).thenReturn(publicationToSave);

        mockMvc.perform(post("/publications")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"title\": \"Sample Title\", \"text\": \"Sample Text\" }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Sample Title"))
                .andExpect(jsonPath("$.text").value("Sample Text"));
    }
}
