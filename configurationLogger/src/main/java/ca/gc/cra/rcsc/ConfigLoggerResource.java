package ca.gc.cra.rcsc;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import java.util.Optional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/configurations")
public class ConfigLoggerResource {

    @ConfigProperty(name = "quarkus.resteasy-reactive.path") 
    String apiBasePath;

    @ConfigProperty(name="quarkus.kubernetes-client.master-url")
    String url;

    @GET
    @Path("/config-maps")
    @Produces(MediaType.TEXT_PLAIN)
    public void getConfigMaps() {
        System.out.println("API's base path is: "+ apiBasePath);
    }

    @GET
    @Path("/secrets")
    @Produces(MediaType.TEXT_PLAIN)
    public void getSecrets() {
        System.out.println("OCP url is: "+ url);
    }
}