package ca.gc.cra.rcsc;
import org.json.simple.JSONObject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import java.util.Optional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.*;
import javax.ws.rs.*;
@Path("/programmatic")
public class ProgrammaticAccess {

    @GET
    // @Path("/properties")
    @Produces(MediaType.TEXT_PLAIN)
    public String getProperties() {
        Config config = ConfigProvider.getConfig();
        String configMaps = config.getValue("quarkus.openshift.env.configmaps", String[].class);
        String namespace = config.getValue("quarkus.kubernetes-client.namespace", String.class);
        String swagger = config.getValue("quarkus.swagger-ui.always-include", String.class);
        String data = config.getValue("config.gitops-microservice-build.data",String.class);
        JSONObject properties = new JSONObject();
        properties.put("quarkus.openshift.env.configmaps",configMaps);
        properties.put("quarkus.kubernetes-client.namespace",namespace);
        properties.put("quarkus.swagger-ui.always-include",swagger);
        properties.put("config.gitops-microservice-build.data",data);

        String toReturn = "List of few application properties accessed programmatically is:  "+ properties;
        System.out.println(toReturn);
        return toReturn;
    }
}