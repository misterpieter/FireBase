package be.volders.firebase;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class User {

    private String name;
    private String gbDt;
    private Boolean zwanger;
    private Boolean risicoGroep;


    public User(String name,String gbDt) {
        this.name = name;
        this.gbDt = gbDt;
        this.zwanger = false;
        this.risicoGroep = false;
    }

    public User() {
        this("","");
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
