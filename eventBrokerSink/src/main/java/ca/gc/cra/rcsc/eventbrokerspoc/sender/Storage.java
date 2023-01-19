package ca.gc.cra.rcsc.eventbrokerspoc.sender;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import ca.gc.cra.rcsc.eventbrokerspoc.Utils;

@ApplicationScoped
public class Storage {

    @Inject
    @RestClient
    StorageService storageService;
    
    public void sendData(String data) {
        try {
            String jsonString = Utils.buildJson(data);
            
            storageService.saveJsonRecord(jsonString);
        } catch (Exception e) {
            System.out.println("Storage exception" + e);
        }
    }
    
}
