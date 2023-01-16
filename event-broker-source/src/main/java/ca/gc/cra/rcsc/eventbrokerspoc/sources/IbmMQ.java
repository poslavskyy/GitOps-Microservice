// package ca.gc.cra.rcsc.eventbrokerspoc.sources;


// import javax.jms.Destination;
// import javax.jms.JMSContext;
// import javax.jms.JMSException;
// import javax.jms.JMSProducer;
// import javax.jms.TextMessage;

// import com.ibm.msg.client.jms.JmsConnectionFactory;
// import com.ibm.msg.client.jms.JmsFactoryFactory;
// import com.ibm.msg.client.wmq.WMQConstants;

// import ca.gc.cra.rcsc.eventbrokerspoc.Utils;


// public class IbmMQ {
//     private int instanceId;

//     // Create variables for the connection to MQ
//     private String ibmMqHost;
//     private int ibmMqPort;
//     private String ibmMqChannel;
//     private String ibmMqQmgr;
//     private String ibmMqUser;
//     private String ibmMqPassword;
//     private String ibmMqQueueName;
//     private String ibmMqPubName;
//     private String ibmMqTopic;

//     JMSContext context = null;
//     Destination destination = null;
//     JMSProducer producer = null;
    
//     public IbmMQ(int instanceId) {
//         this.instanceId = instanceId;
        
//         loadConfiguration();
//         connect();
//     }


//     public void connect(){
//         try{
//             // Create a connection factory
//             System.out.println("IBM MQ:TRYING CONNECTION");
//             JmsFactoryFactory ff = JmsFactoryFactory.getInstance(WMQConstants.WMQ_PROVIDER);
//             JmsConnectionFactory cf = ff.createConnectionFactory();

//             // Set JMS properties
//             cf.setStringProperty(WMQConstants.WMQ_HOST_NAME, ibmMqHost);
//             cf.setIntProperty(WMQConstants.WMQ_PORT, ibmMqPort);
//             cf.setStringProperty(WMQConstants.WMQ_CHANNEL, ibmMqChannel);
//             cf.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT);
//             cf.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER, ibmMqQmgr);
//             cf.setStringProperty(WMQConstants.WMQ_APPLICATIONNAME, "SimplePub (JMS)");
//             cf.setBooleanProperty(WMQConstants.USER_AUTHENTICATION_MQCSP, true);
//             cf.setStringProperty(WMQConstants.USERID, ibmMqUser);
//             cf.setStringProperty(WMQConstants.PASSWORD, ibmMqPassword);
//             cf.setStringProperty(WMQConstants.CLIENT_ID, ibmMqPubName);

//             // Create JMS objects
//             context = cf.createContext();
//             destination = context.createTopic("topic://" + ibmMqTopic);
//             producer = context.createProducer();
//         } catch (JMSException e) {
//             recordFailure(e);
//         }
//     }
//     public void sendMessage() {
//         String messageText = Utils.buildMessage(instanceId);
//         TextMessage message = context.createTextMessage(messageText);
//         producer.send(destination, message);
//         recordSuccess(String.valueOf(message));
//     }

//     /**
//      * Record this run as successful.
//      */
//     private static void recordSuccess(String message) {
//         System.out.println("IBM MQ Sent message:" + message);
//     }

//     /**
//      * Record this run as failure.
//      */
//     private static void recordFailure(Exception ex) {
//         if (ex != null) {
//             if (ex instanceof JMSException) {
//                 processJMSException((JMSException) ex);
//             } else {
//                 System.out.println(ex);
//             }
//         }
//         System.out.println("IBM MQ: FAILURE");
//     }

//     /**
//      * Process a JMSException and any associated inner exceptions.
//      */
//     private static void processJMSException(JMSException jmsex) {
//         System.out.println(jmsex);
//         Throwable innerException = jmsex.getLinkedException();
//         if (innerException != null) {
//             System.out.println("IBM MQ: Inner exception(s):");
//         }
//         while (innerException != null) {
//             System.out.println(innerException);
//             innerException = innerException.getCause();
//         }
//     }

//     private void loadConfiguration() {
// 		ibmMqHost = Utils.getStringProperty("ibmmq.host");
//         ibmMqPort = Utils.getIntProperty("ibmmq.port");
// 		ibmMqChannel = Utils.getStringProperty("ibmmq.channel");
// 		ibmMqQmgr = Utils.getStringProperty("ibmmq.qmgr");
//         ibmMqUser = Utils.getStringProperty("ibmmq.user");
// 		ibmMqPassword = Utils.getStringProperty("ibmmq.password");
// 		ibmMqQueueName = Utils.getStringProperty("ibmmq.queue.name");
//         ibmMqTopic = Utils.getStringProperty("ibmmq.topic");
//         ibmMqPubName = Utils.getStringProperty("ibmmq.pubname");

// 		printConfiguration();
// 	}

// 	private void printConfiguration() {
// 		System.out.println("IBM MQ Config:");
// 		System.out.println("ibmMqHost=" + ibmMqHost);
// 		System.out.println("ibmMqPort=" + ibmMqPort);
// 		System.out.println("ibmMqChannel=" + ibmMqChannel);
// 		System.out.println("ibmMqQmgr=" + ibmMqQmgr);
// 		System.out.println("ibmMqUser=" + ibmMqUser);
//         System.out.println("ibmMqPassword=" + ibmMqPassword);
//         System.out.println("ibmMqQueueName=" + ibmMqQueueName);
//         System.out.println("ibmMqTopic=" + ibmMqTopic);
//         System.out.println("ibmMqPubName=" + ibmMqPubName);
// 	}

// }