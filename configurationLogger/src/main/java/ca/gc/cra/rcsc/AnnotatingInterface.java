package ca.gc.cra.rcsc;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONObject;
import javax.inject.Inject;

@Path("/interfaceConfig")
public class AnnotatingInterface {

    @Inject
    InterfaceConfiguration config;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String pocDetails() {
        JSONObject details = new JSONObject();
        details.put("poc.name",config.name());
        details.put("poc.team.name",config.team().name());
        details.put("poc.team.gitOpsDeveloperCount",config.team().developerCount());
        details.put("poc.phase.name",config.phase().name());
        details.put("poc.phase.status",config.phase().status());

        System.out.println("POC Details: "+ details);
        return details;
    }
}