package org.cloudfoundry.samples.music.web;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.cloudfoundry.samples.music.domain.Album;
import org.cloudfoundry.samples.music.repositories.jpa.JpaAlbumRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

@Path("albums")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class AlbumController {
    private static final Logger logger = LoggerFactory.getLogger(AlbumController.class);

    @Inject
    private JpaAlbumRepository repository;

    @GET
    public Iterable<Album> albums() {
        return repository.listAll();
    }

    @Transactional
    @POST
    public Album add(Album album) {
        logger.info("Adding album " + album.getId());
        return repository.getEntityManager().merge(album);
    }

    @Transactional
    @PUT
    public Album update(Album album) {
        logger.info("Updating album " + album.getId());
        return repository.getEntityManager().merge(album);
    }

    @GET
    @Path("/{id}")
    public Album getById(@PathParam("id") String id) {
        logger.info("Getting album " + id);
        return repository.findByIdOptional(id).orElse(null);
    }

    @Transactional
    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") String id) {
        logger.info("Deleting album " + id);
        repository.deleteById(id);
    }

    @Transactional
    @DELETE
    @Path("deleteByArtist")
    public Response deleteAlbumsByArtist(@QueryParam("artist") String artist){
        logger.info("Inside @Class AlbumController @Method deleteAlbumsByArtist");
        List<Album> albums = repository.find("artist",artist).list();
        if(Objects.nonNull(albums) && !albums.isEmpty()){
            albums.forEach(album -> repository.delete(album));
        }
        return Response
                .status(Response.Status.OK)
                .build();
    }
}