package be.volders.firebase.models;

import java.io.Serializable;

public class User implements Serializable {

    private String name;
    private String gbDt;
    private Boolean zwanger;
    private Boolean risicoGroep;
    private String gender;


    public User(String name, String gbDt) {
        this.name = name;
        this.gbDt = gbDt;
        this.zwanger = false;
        this.risicoGroep = false;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public User() {
        this("", "");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGbDt() {
        return gbDt;
    }

    public void setGbDt(String gbDt) {
        this.gbDt = gbDt;
    }

    public Boolean getZwanger() {
        return zwanger;
    }

    public void setZwanger(Boolean zwanger) {
        this.zwanger = zwanger;
    }

    public Boolean getRisicoGroep() {
        return risicoGroep;
    }

    public void setRisicoGroep(Boolean risicoGroep) {
        this.risicoGroep = risicoGroep;
    }

}
