package ca.gc.cra.rcsc;

import ca.gc.cra.rcsc.config.InterfaceConfiguration;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.inject.Inject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
@Path("/interfaceConfig")
public class AnnotatingInterface {

    @Inject
    InterfaceConfiguration config;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String pocDetails() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode details = mapper.createObjectNode();
        details.put("poc.name",config.name());
        details.put("poc.team.name",config.team().name());
        details.put("poc.team.gitops-developer-count",config.team().gitopsDeveloperCount());
        details.put("poc.phase.name",config.phase().name());
        details.put("poc.phase.status",config.phase().status());

        System.out.println("POC Details: "+ details);
        return details.toString();
    }
}