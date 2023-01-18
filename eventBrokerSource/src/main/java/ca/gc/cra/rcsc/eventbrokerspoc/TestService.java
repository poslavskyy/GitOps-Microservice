package ca.gc.cra.rcsc.eventbrokerspoc;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import ca.gc.cra.rcsc.eventbrokerspoc.sources.SolacePubSub;



@Path("/test")
public class TestService {

    private SolacePubSub solace;
   
	@GET
    @Path("/solace")
    public void testSolace() {
        if (solace == null) {
            solace = new SolacePubSub(2);
        }
    	solace.sendMessage();
    }

}