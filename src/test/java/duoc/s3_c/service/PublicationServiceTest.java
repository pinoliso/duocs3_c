package duoc.s3_c.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;

import duoc.s3_c.model.Publication;
import duoc.s3_c.repository.PublicationRepository;

@ExtendWith(MockitoExtension.class)
public class PublicationServiceTest {

    @InjectMocks
    private PublicationService publicationService;

    @Mock
    private PublicationRepository publicationRepositoryMock;

    @Test
    public void createMovieServiceTest() {
        
        Publication publication = new Publication();
        publication.setTitle("Publicación Save");

        when(publicationRepositoryMock.save(any())).thenReturn(publication);

        Publication savedPublication = publicationService.createPublication(publication);

        assertEquals("Publicación Save", savedPublication.getTitle());
    }

    @Test
    void deletePublicationTest() {

        Publication publication = new Publication();
        publication.setTitle("Publicación 1");
        when(publicationRepositoryMock.save(any())).thenReturn(publication);
        Publication savedPublication = publicationService.createPublication(publication);

        publicationService.deletePublication(savedPublication.getId());
        
        verify(publicationRepositoryMock, times(1)).deleteById(savedPublication.getId());
    }
    
}