package tw.org.iii.brad.brad25;

import java.util.ArrayList;
import java.util.LinkedList;

public class Member {
    private ArrayList<String> names;
    private int age;

    public Member(){
        names = new ArrayList<String>();
        age = 0;
    }

    public void addName(String name){
        names.add(name);
    }

    public ArrayList<String> getNames(){
        return names;
    }

    public void setAge(int age){
        this.age = age;
    }
    public int getAge(){
        return age;
    }
}
