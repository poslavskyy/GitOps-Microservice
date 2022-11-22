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

    Config config = ConfigProvider.getConfig();

    @GET
    @Path("/properties")
    @Produces(MediaType.TEXT_PLAIN)
    public String getProperties() {
        // Config config = ConfigProvider.getConfig();
        String configMaps = config.getValue("quarkus.openshift.env.configmaps", String[].class);
        String secrets = config.getValue("quarkus.openshift.env.configmaps", String[].class);
        String namespace = config.getValue("quarkus.kubernetes-client.namespace", String.class);
        String swagger = config.getValue("quarkus.swagger-ui.always-include", String.class);
        String data = config.getValue("config.gitops-microservice-build.data",String.class);
        JSONObject properties = new JSONObject();
        properties.put("quarkus.openshift.env.configmaps",configMaps);
        properties.put("quarkus.kubernetes-client.namespace",namespace);
        properties.put("quarkus.swagger-ui.always-include",swagger);
        properties.put("config.gitops-microservice-build.data",data);
        properties.put("quarkus.openshift.env.secrets",secrets);
        String toReturn = "List of few application properties accessed programmatically is:  "+ properties;
        System.out.println(toReturn);
        return toReturn;
    }

     @GET
    @Path("/config-maps")
    @Produces(MediaType.TEXT_PLAIN)
    public String getConfigMaps() {
        String configMaps = config.getValue("quarkus.openshift.env.configmaps", String[].class);
        System.out.println("List of Config Maps is: "+ configMaps);
        return "List of Config Maps is: "+ configMaps;
    }

    @GET
    @Path("/config-maps/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String configMapData(@PathParam("name") String name){
        String message;
        switch(name){
            case "gitops-microservice-build" :
                message = "Data from "+name+ " is \n"+config.getValue("config.gitops-microservice-build.data",String.class);
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
        String secrets = config.getValue("quarkus.openshift.env.configmaps", String[].class);
        System.out.println("List of Secrets is: "+ secrets);
        return "List of Secrets is: "+ secrets;
    }
}