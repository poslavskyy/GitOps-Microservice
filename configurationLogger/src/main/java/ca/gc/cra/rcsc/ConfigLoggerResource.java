package ca.gc.cra.rcsc;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import java.util.Optional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.*;
import javax.ws.rs.*;
@Path("/injection")
public class ConfigLoggerResource {
    

    // Config config = ConfigProvider.getConfig();
    // Map configValues = config.getValue("config.gitops-microservice-build.data", Map.class);

    @ConfigProperty(name = "quarkus.kubernetes-client.namespace") 
    String namespace;

    @ConfigProperty(name = "quarkus.openshift.env.configmaps")
    String configMapsList;

    @ConfigProperty(name = "quarkus.openshift.env.secrets")
    String secretsList;

    @ConfigProperty(name = "config.gitops-microservice-build.data")
    String gitopsMicroserviceBuildData;


    @GET
    @Path("/config-maps")
    @Produces(MediaType.TEXT_PLAIN)
    public String getConfigMaps() {
        System.out.println("List of Config Maps is: "+ configMapsList);
        return "List of Config Maps is: "+ configMapsList;
    }

    @GET
    @Path("/config-maps/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String configMapData(@PathParam("name") String name){
        String message;
        switch(name){
            case "gitops-microservice-build" :
                message = "Data from "+name+ " is \n"+gitopsMicroserviceBuildData;
                break;
            default:
                message = "Provided config map does not exist";
        }
        System.out.println(message);
        return message;
    }

    @GET
    @Path("/secrets")
    @Produces(MediaType.TEXT_PLAIN)
    public String getSecrets() {
        System.out.println("List of Secrets is: "+ secretsList);
        return "List of Secrets is: "+ secretsList;
    }
}