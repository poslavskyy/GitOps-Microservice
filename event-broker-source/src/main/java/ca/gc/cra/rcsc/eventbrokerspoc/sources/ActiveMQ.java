// package ca.gc.cra.rcsc.eventbrokerspoc.sources;

// import javax.enterprise.context.ApplicationScoped;
// // import org.eclipse.microprofile.reactive.messaging.Message;
// import io.vertx.mutiny.amqp.AmqpMessage;
// import io.vertx.mutiny.amqp.AmqpMessageBuilder;
// import org.eclipse.microprofile.reactive.messaging.Channel;
// import org.eclipse.microprofile.reactive.messaging.Emitter;
// import ca.gc.cra.rcsc.eventbrokerspoc.Utils;
// import io.vertx.core.json.JsonObject;


// @ApplicationScoped
// public class ActiveMQ {

//     @Channel("test-topic-out")
//     Emitter<AmqpMessage> emitter;

//     private int instanceId;
    
//     public ActiveMQ() {
//         this(4);
//     }

//     public ActiveMQ(int instanceId) {
//         this.instanceId = instanceId;
//     }

//      public void sendMessage() {
//         String toSend = Utils.buildMessage(instanceId);
//         JsonObject props = new JsonObject();
//         props.put("HDR_DUPLICATE_DETECTION_ID", "343abcd");

//         AmqpMessageBuilder amqbuilder= AmqpMessageBuilder.create();
//         AmqpMessage output = amqbuilder
//                             .withBody(toSend)
//                             .applicationProperties(props)
//                             .build();
        
//         emitter.send(output);
//         System.out.println("ActiveMQ-MESSAGE SENT: " + toSend);
//     }

// }
