package Server;

import java.util.ArrayList;

public class Member {
    int id;
    ArrayList<Topic> userTopics = new ArrayList<>();
    public Member(int id){
    this.id=id;

    }

    public int getId(){
        return  id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Topic> getUserTopics(){
        return userTopics;
    }
    public void setUserTopics(ArrayList<Topic> userTopics) {this.userTopics=userTopics;}
    public void addTopic(Topic topic){
        userTopics.add(topic);
    }
}
