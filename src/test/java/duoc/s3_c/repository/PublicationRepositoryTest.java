package duoc.s3_c.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import duoc.s3_c.model.Publication;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PublicationRepositoryTest {
    
    @Mock
    private PublicationRepository publicationRepositoryMock;

    @Test
    public void savePublicationRepositoryTest() {
        Publication publication = new Publication();
        publication.setTitle("Publicación Save");

        when(publicationRepositoryMock.save(any())).thenReturn(publication);

        Publication savedPublication = publicationRepositoryMock.save(publication);

        assertEquals("Publicación Save", savedPublication.getTitle());
    }

    @Test
    public void deletePublicationRespositoryTest() {
        Publication publication = new Publication();
        publication.setTitle("Publicación 1");
        when(publicationRepositoryMock.save(any())).thenReturn(publication);
        Publication savedPublication = publicationRepositoryMock.save(publication);

        publicationRepositoryMock.deleteById(savedPublication.getId());
        
        verify(publicationRepositoryMock, times(1)).deleteById(savedPublication.getId());
    }

}
