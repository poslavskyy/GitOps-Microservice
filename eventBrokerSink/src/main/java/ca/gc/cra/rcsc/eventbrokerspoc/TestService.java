package ca.gc.cra.rcsc.eventbrokerspoc;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ca.gc.cra.rcsc.eventbrokerspoc.sender.Storage;
import ca.gc.cra.rcsc.eventbrokerspoc.sinks.ActiveMQ;
import ca.gc.cra.rcsc.eventbrokerspoc.sinks.ApacheKafka;
import ca.gc.cra.rcsc.eventbrokerspoc.sinks.IbmMQ;
import ca.gc.cra.rcsc.eventbrokerspoc.sinks.NatsBroker;
import ca.gc.cra.rcsc.eventbrokerspoc.sinks.RabbitMQ;
import ca.gc.cra.rcsc.eventbrokerspoc.sinks.SolacePubSub;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Path("/connect")
public class TestService {

	@Inject
	Storage storage;

	@Inject
	ActiveMQ activeMq;
	@Inject
	ApacheKafka kafka;

	private SolacePubSub solace;
	private NatsBroker nats;
	private RabbitMQ rabbitMq;
	private IbmMQ ibmMq;
	
	
	@GET
    @Path("/solace")
    public void connectToSolace() {
		if (solace == null) {
			solace = new SolacePubSub(storage);
		}
		solace.connectToTopic();
    }


	@GET
	@Path("/status")
	@Produces(MediaType.TEXT_PLAIN)
	public String getStatus() {
		String result = "Status:\n";
		
		result += "Solace: " + Utils.isConnectedNullCheck(solace) + "\n";
		
		return result;
	}


	@GET
	@Path("/disconnectAll")
	public void disconnectAll() throws IOException, TimeoutException, InterruptedException {
		
		if (solace != null){
			solace.disconnect();
			solace = null;
		}
	}
}