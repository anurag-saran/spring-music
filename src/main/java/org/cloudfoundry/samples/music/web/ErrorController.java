package org.cloudfoundry.samples.music.web;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.ArrayList;
import java.util.List;

@Path("/errors")
public class ErrorController {

    private List<int[]> junk = new ArrayList<>();

    @GET
    @Path("/kill")
    public void kill() {
        System.exit(1);
    }

    @GET
    @Path("/fill-heap")
    public void fillHeap() {
        while (true) {
            junk.add(new int[9999999]);
        }
    }

    @GET
    @Path("/throw")
    public void throwException() {
        throw new NullPointerException("Forcing an exception to be thrown");
    }

}
