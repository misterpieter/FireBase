package be.volders.firebase;

import java.util.HashMap;
import java.util.Map;

public class User {

    private String name;
    private String info;
    private String age;


    public User(String name,String age,String info) {
        this.name = name;
        this.age = age;
        this.info = info;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User:\n" + name + ' ' + age + ' ' + info + ' ';
    }
}
