package org.cloudfoundry.samples.music.repositories;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.transaction.Transactional;
import org.cloudfoundry.samples.music.domain.Album;
import org.cloudfoundry.samples.music.repositories.jpa.JpaAlbumRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Objects;

@Priority(1)
@Alternative
@ApplicationScoped
public class AlbumRepositoryPopulator extends JpaAlbumRepository {

    private static final Logger logger = LoggerFactory.getLogger(AlbumRepositoryPopulator.class);

    @PostConstruct
    public void populateAlbumsJson() {
        populateAlbums();
    }

    @Transactional
    public void populateAlbums() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("albums.json");

            if (inputStream == null) {
                throw new IllegalArgumentException("file not found! ");
            } else {
                if (super.count() == 0) {
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                    Album[] albums = mapper.readValue(inputStream, Album[].class);
                    if(Objects.nonNull(albums) && albums.length != 0){
                        for(Album album : albums){
                            super.persist(album);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("error is {}", e);
        }
    }
}
