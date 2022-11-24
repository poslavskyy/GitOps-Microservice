package ca.gc.cra.rcsc;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import ca.gc.cra.rcsc.converters.JSONConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Optional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.*;
import javax.ws.rs.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
@Path("/injection")
public class ConfigInjection {

    @ConfigProperty(name = "quarkus.kubernetes-client.namespace") 
    String namespace;

    @ConfigProperty(name = "quarkus.openshift.env.configmaps")
    String configMapsList;

    @ConfigProperty(name = "quarkus.openshift.env.secrets")
    String secretsList;

    @ConfigProperty(name = "config.gitops-microservice-build.data")
    ObjectNode gitopsMicroserviceBuildData;


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
        ObjectMapper mapper = new ObjectMapper();
        String message="Initial Message";
        try{
            switch(name){
            case "gitops-microservice-build" :
                String plainGitopsMicroserviceBuildData = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(gitopsMicroserviceBuildData);
                message = "Data from "+name+ " is \n"+plainGitopsMicroserviceBuildData;
                System.out.println("properties.build field is: "+ gitopsMicroserviceBuildData.get("properties.build").asText());
                break;
            default:
                message = "Provided config map does not exist";
        }
        System.out.println(message);

        }catch(JsonProcessingException e){
            System.out.println(e);
        }
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