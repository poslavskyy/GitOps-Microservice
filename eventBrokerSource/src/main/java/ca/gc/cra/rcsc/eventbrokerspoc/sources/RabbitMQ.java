// package ca.gc.cra.rcsc.eventbrokerspoc.sources;

// import com.rabbitmq.client.ConnectionFactory;
// import com.rabbitmq.client.Connection;
// import com.rabbitmq.client.Channel;

// import java.io.IOException;
// import java.nio.charset.StandardCharsets;
// import java.util.concurrent.TimeoutException;
// import ca.gc.cra.rcsc.eventbrokerspoc.Utils;


// public class RabbitMQ {
//     private int instanceID;

//     Channel channel;
//     private ConnectionFactory factory;
//     private Connection connection;

//     private String rabbitMqHost;
//     private int rabbitMqPort;
//     private String rabbitMqExchange;
//     private String rabbitMqUsername;
//     private String rabbitMqPassword;

//     public RabbitMQ(int instanceID){
//         this.instanceID = instanceID;
        
//         loadConfiguration();
//         connect();
//     }

//     public void sendMessage(){
//         try {
//         	String message = Utils.buildMessage(this.instanceID);
//             channel.basicPublish(rabbitMqExchange, "", null, message.getBytes(StandardCharsets.UTF_8));
//             System.out.println("RabbitMQ-MESSAGE SENT: " + message);
            
//         } catch (IOException e) {
//             throw new RuntimeException(e);
//         }
//     }

//     private void connect() {
//         factory = new ConnectionFactory();
//         factory.setHost(rabbitMqHost);
//         factory.setPort(rabbitMqPort);
//         factory.setUsername(rabbitMqUsername);
//         factory.setPassword(rabbitMqPassword);

//         try {
//             connection = factory.newConnection();
//             channel = connection.createChannel();
//             channel.exchangeDeclare(rabbitMqExchange, "fanout");
//         } catch (IOException | TimeoutException e) {
//             throw new RuntimeException(e);
//         }
//     }

//     private void loadConfiguration() {
// 		rabbitMqHost = Utils.getStringProperty("rabbitmq.host");
// 		rabbitMqPort = Utils.getIntProperty("rabbitmq.port");
//         rabbitMqExchange = Utils.getStringProperty("rabbitmq.exchange");
// 		rabbitMqUsername = Utils.getStringProperty("rabbitmq.username");
// 		rabbitMqPassword = Utils.getStringProperty("rabbitmq.password");

// 		printConfiguration();
// 	}

// 	private void printConfiguration() {
// 		System.out.println("RabbitMq Config");
// 		System.out.println("rabbitMqHost=" + rabbitMqHost);
// 		System.out.println("rabbitMqPort=" + rabbitMqPort);
//         System.out.println("rabbitMqExchange=" + rabbitMqExchange);
// 		System.out.println("rabbitMqUsername=" + rabbitMqUsername);
// 		System.out.println("rabbitMqPassword=" + rabbitMqPassword);
// 	}
// }
