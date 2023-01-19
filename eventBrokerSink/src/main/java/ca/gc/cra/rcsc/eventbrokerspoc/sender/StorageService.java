package ca.gc.cra.rcsc.eventbrokerspoc.sender;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/json")
@RegisterRestClient
public interface StorageService {
    
    @POST
    String saveJsonRecord(String data);

}
