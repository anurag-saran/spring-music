package org.cloudfoundry.samples.music;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.cloudfoundry.samples.music.domain.Album;
import org.cloudfoundry.samples.music.repositories.jpa.JpaAlbumRepository;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@TestMethodOrder(OrderAnnotation.class)
@QuarkusTest
public class AlbumControllerTest {

    @Inject
    private JpaAlbumRepository albumRepository;

    private static final Logger logger = LoggerFactory.getLogger(AlbumControllerTest.class);

    @Test
    @Order(1)
    public void testCreateAlbumEndpoint() {
        given().contentType(ContentType.JSON)
                .body(new Album("Barfi", "Test", "2012", "classic"))
                .when().post("/albums")
                .then().statusCode(200)
                .body("title", is("Barfi"))
                .body("artist", is("Test"))
                .body("releaseYear", is("2012"))
                .body("genre", is("classic"));
    }

    @Test
    @Order(2)
    public void testUpdateAlbumEndpoint() {
        given().contentType(ContentType.JSON)
                .body(new Album("Rock On", "Test", "2008", "pop-rock"))
                .when().put("/albums")
                .then().statusCode(200)
                .body("title", is("Rock On"))
                .body("artist", is("Test"))
                .body("releaseYear", is("2008"))
                .body("genre", is("pop-rock"));
    }

    @Test
    @Order(3)
    public void testListAllAlbumsEndpoint() {
        given().contentType(ContentType.JSON)
                .when().get("/albums")
                .then().statusCode(200);
    }

    @Test
    @Order(4)
    public void testGetByIdAlbumEndpoint() {
        Album album = this.albumRepository.find("artist", "Test").firstResult();
        given().contentType(ContentType.JSON)
                .pathParam("id", album.getId())
                .when().get("/albums/{id}")
                .then().statusCode(200);
    }

    @Test
    @Order(5)
    public void testDeleteByIdAlbumEndpoint() {
        Album album = this.albumRepository.find("artist", "Test").firstResult();
        given().contentType(ContentType.JSON)
                .pathParam("id", album.getId())
                .when().delete("/albums/{id}")
                .then().statusCode(204);
    }

    @Test
    @Order(6)
    public void deleteAlbumsByArtistEndPoint() {
        given().contentType(ContentType.JSON)
                .queryParam("artist", "Test")
                .when().delete("/albums/deleteByArtist")
                .then().statusCode(200);
    }
}

