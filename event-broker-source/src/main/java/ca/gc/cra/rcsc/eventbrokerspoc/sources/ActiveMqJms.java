// package ca.gc.cra.rcsc.eventbrokerspoc.sources;

// import javax.enterprise.context.ApplicationScoped;
// import javax.inject.Inject;
// import javax.jms.ConnectionFactory;
// import javax.jms.JMSContext;
// import ca.gc.cra.rcsc.eventbrokerspoc.Utils;
// import javax.jms.*;
// import java.util.UUID;


// @ApplicationScoped
// public class ActiveMqJms {

//     @Inject
//     ConnectionFactory connectionFactory;

//    private int instanceId;
    
//     public ActiveMqJms() {
//         this(7);
//     }

//     public ActiveMqJms(int instanceId) {
//         this.instanceId = instanceId;
//     }

//     public void sendMessage(String uniqueId){
//         String myUniqueID;
//         if(uniqueId.isEmpty()) {
//             myUniqueID = UUID.randomUUID().toString();
//         } else {
//           myUniqueID = uniqueId; 
//         }

//         String toSend = Utils.buildMessage(instanceId);
        
//         try {
//             JMSContext context = connectionFactory.createContext(JMSContext.AUTO_ACKNOWLEDGE);
//             Message jmsMessage = context.createTextMessage(toSend);
            
//             jmsMessage.setStringProperty("_AMQ_DUPL_ID", myUniqueID);
//             context.createProducer().send(context.createQueue("test-topic"), jmsMessage);

//             System.out.println("ActiveMQ-JMS-MESSAGE SENT: " + toSend + " (_AMQ_DUPL_ID=" + myUniqueID + ")");
//         } catch(Exception e) {
//            e.printStackTrace();
//         }
//     }
// }