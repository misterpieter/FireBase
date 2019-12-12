package be.volders.firebase;

import java.util.ArrayList;
import java.util.List;

public class Vaccin {

    /*
    - id
	- naam
	- Array ziektes					            (check: meerdere ziektes = gecombineerd vaccin)
	- Array namen					            (check: meerdere namen = extra gecombineerd vaccin)
	- uiterlijkeBeginDatum/minimum leeftijd		(check to input)
	- List<maandIntervallen (int)>			        (check to input)
    */

    private String naam;
    private Boolean vrouw;
    private List<String> ziektes;
    private List<String> combiVaccins;
    private List<String> intervallen;
    private Boolean combiOptie;

    public Vaccin(String naam) {
        this.naam = naam;
        this.ziektes = new ArrayList<>();
        this.combiVaccins = new ArrayList<>();
        this.intervallen = new ArrayList<>();
        this.vrouw = false;
        this.combiOptie = false;
    }

    public Vaccin() {
    }

    public Boolean getCombiOptie() {
        return combiOptie;
    }

    public void setCombiOptie() {
        if (this.getCombiVaccins().isEmpty()){
            this.combiOptie = false;
        }
        else this.combiOptie = true;
    }

    public Boolean getVrouw() {
        return vrouw;
    }

    public void setVrouw(Boolean vrouw) {
        this.vrouw = vrouw;
    }


    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public List<String> getZiektes() {
        return ziektes;
    }

    public void setZiektes(List<String> ziektes) {
        this.ziektes = ziektes;
    }

    public List<String> getCombiVaccins() {
        return combiVaccins;
    }

    public void addVaccins(List<String> vaccins) {
        this.combiVaccins = vaccins;
        this.setCombiOptie();
    }

    public void addVaccin(String vaccin){
        combiVaccins.add(vaccin);
        this.setCombiOptie();
    }
    public void deleteVaccin(String vaccin){
        ziektes.remove(vaccin);
        this.setCombiOptie();
    }

    public void addZiekte(String ziekte){
        ziektes.add(ziekte);
    }

    public void deleteZiekte(String ziekte){
        ziektes.remove(ziekte);
    }

    public List<String> getIntervallen() {
        return intervallen;
    }

    public void setIntervallen(List<String> intervallen) {
        this.intervallen = intervallen;
    }

    public void addWeekInterval(int interval){
        intervallen.add(interval+" weken");
    }

    public void addMaandInterval(int interval){
        intervallen.add(interval+" maand");
    }

    public void addMaandInterval(int interval, int interval2){
        intervallen.add(interval+" - "+ interval2+" maand");
    }

    public void addJaarInterval(int interval, int interval2){
        intervallen.add(interval+" - "+ interval2+" jaar");
    }

    public void addJaarInterval(int interval){
        intervallen.add(interval+" jaar");
    }

    public void addJaarInterval(String interval){
        intervallen.add(interval);
    }


    @Override
    public String toString() {
        return "\nVaccin\n" +
                "\n\tnaam: " + naam +
                "\n\tziektes: " + getZiektes() +
                "\n\tcombiVaccins: " + getCombiVaccins() +
                "\n\tntervallen: " + getIntervallen();
    }
}