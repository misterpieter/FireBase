package be.volders.firebase.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Vaccin implements Serializable {

    private String naam;
    private String info;

    private Boolean boolZwangereVrouw;
    private Boolean boolCombiVaccin;
    private Boolean boolMultipleVaccinOptie;
    private boolean isSelected;

    private List<String> ziektes;
    private List<String> combiVaccins;
    private List<String> minLeeftijden;

    public Vaccin(String naam) {
        this.naam = naam;
        this.ziektes = new ArrayList<>();
        this.combiVaccins = new ArrayList<>();
        this.minLeeftijden = new ArrayList<>();
        this.boolZwangereVrouw = false;
        this.boolCombiVaccin = false;
        this.boolMultipleVaccinOptie = false;
        info = "";
    }

    public Vaccin() {
    }

    public Boolean getBoolCombiVaccin() {
        return boolCombiVaccin;
    }

    public void setCombiVaccin() {
        if (this.getZiektes().isEmpty() || this.getZiektes().size() == 1) {
            this.boolCombiVaccin = false;
        } else this.boolCombiVaccin = true;
    }

    public Boolean getBoolMultipleVaccinOptie() {
        return boolMultipleVaccinOptie;
    }

    public void setBoolMultipleVaccinOptie() {
        if (this.getCombiVaccins().isEmpty()) {
            this.boolMultipleVaccinOptie = false;
        } else this.boolMultipleVaccinOptie = true;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Boolean getBoolZwangereVrouw() {
        return boolZwangereVrouw;
    }

    public void setBoolZwangereVrouw(Boolean boolZwangereVrouw) {
        this.boolZwangereVrouw = boolZwangereVrouw;
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

    public void addZiekte(String ziekte) {
        ziektes.add(ziekte);
        this.setCombiVaccin();
    }

    public void deleteZiekte(String ziekte) {
        ziektes.remove(ziekte);
        this.setCombiVaccin();
    }

    public List<String> getMinLeeftijden() {
        return minLeeftijden;
    }

    public void setMinLeeftijden(List<String> minLeeftijden) {
        this.minLeeftijden = minLeeftijden;
    }

    public void addWeekInterval(int interval) {
        minLeeftijden.add(interval + " weken");
    }

    public void addWeekInterval(String interval) {
        minLeeftijden.add(interval);
    }


    public void addMaandInterval(int interval) {
        minLeeftijden.add(interval + " maand");
    }

    public void addMaandInterval(int interval, int interval2) {
        minLeeftijden.add(interval + " - " + interval2 + " maand");
    }

    public void addMaandInterval(String interval) {
        minLeeftijden.add(interval);
    }


    public void addJaarInterval(int interval, int interval2) {
        minLeeftijden.add(interval + " - " + interval2 + " jaar");
    }

    public void addJaarInterval(int interval) {
        minLeeftijden.add(interval + " jaar");
    }

    public void addJaarInterval(String interval) {
        minLeeftijden.add(interval);
    }

    public void addVaccins(List<String> vaccins) {
        this.combiVaccins = vaccins;
        this.setBoolMultipleVaccinOptie();
    }

    public void addVaccin(String vaccin) {
        combiVaccins.add(vaccin);
        this.setBoolMultipleVaccinOptie();
    }

    public void deleteVaccin(String vaccin) {
        ziektes.remove(vaccin);
        this.setBoolMultipleVaccinOptie();
    }

    public String showZiektes() {
        String str = "";
        for (String ziekte : ziektes) {
            str += "\t" + ziekte + "\n";
        }

        return str;
    }

    public String showIntervallen() {
        String str = "Vaccin te geven op volgende minLeeftijden:\n";
        for (String x : minLeeftijden) {
            str += "\t" + x + "\n";
        }
        return str;
    }

    public String showAllInfo() {
        String str = "";

        if (ziektes != null) {
            str += "\n" + showZiektes();
        }

        if (minLeeftijden != null) {
            str += "\n" + showIntervallen();
        }

        if (boolMultipleVaccinOptie == true) {
            str += "\n(Combo vaccin mogelijk met: ";
            str += "\t" + getCombiVaccins() + ")\n";
        }

        str += "\nInfo:\n" + info;

        return str;
    }

    public void setBoolCombiVaccin(Boolean boolCombiVaccin) {
        this.boolCombiVaccin = boolCombiVaccin;
    }

    public void setBoolMultipleVaccinOptie(Boolean boolMultipleVaccinOptie) {
        this.boolMultipleVaccinOptie = boolMultipleVaccinOptie;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setCombiVaccins(List<String> combiVaccins) {
        this.combiVaccins = combiVaccins;
    }

}
