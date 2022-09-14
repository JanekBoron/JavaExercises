package Server;

import java.util.ArrayList;
import java.util.HashMap;

public class Topic {
    String description;
    String topic;

    public Topic(String topic,String description){
        this.topic=topic;
        this.description=description;

    }

    public String getTopic(){
        return topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString(){
        return topic+":"+description;
    }






}
