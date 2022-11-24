package ca.gc.cra.rcsc.converters;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.microprofile.config.spi.Converter;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

public class JSONConverter implements Converter<ObjectNode> {

    @Override
    public ObjectNode convert(String initialValue) {
        Config config = ConfigProvider.getConfig();
        String[] dataArr = initialValue.split("\\r?\\n");
        System.out.println("Lenght of dataArr is "+dataArr.length);
        System.out.println("dataArr is "+dataArr[0]);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        try{
            for(String keyValue: dataArr){
                System.out.println("keyValue is "+keyValue);
                int colonIndex = keyValue.indexOf(":");
                String key;
                String value;
                if (colonIndex != -1){
                    key = keyValue.substring(0 , colonIndex).trim();
                    value = keyValue.substring(colonIndex+1,keyValue.length()).trim();
                    System.out.println("key is "+key);
                    System.out.println("value is "+value);
                    node.put(key,value);
                    }
                }
            String nodeToString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
            System.out.println(nodeToString); 
            }
        catch(JsonProcessingException e){
            System.out.println(e);
        }    
        return node;
    }
}