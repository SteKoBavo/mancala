package nl.sogyo.mancala;

public abstract class abstractBakje {
    private int steentjes;
    private Speler eigenaar;
    private abstractBakje volgendBakje;

    //
    // Constructor
    //
    protected abstractBakje(int steentjes, abstractBakje volgendBakje, Speler eigenaar) {
        this.steentjes = steentjes;
        this.volgendBakje = volgendBakje;
        this.eigenaar = eigenaar;
    }

    //
    // Verbind de bakjes van beide spelers.
    //
    protected void verbindBakjes(abstractBakje anderBakje) {
        this.getKalaha().volgendBakje = anderBakje;
        anderBakje.getKalaha().volgendBakje = this;
    }

    //
    // Getters
    //
    protected int getSteentjes() {
        return steentjes;
    }
    protected abstractBakje getVolgendBakje() {
        return volgendBakje;
    }
    protected abstractBakje getVolgendBakje(int stapjes) {
        while (stapjes<1) stapjes += 14;        // Zodat negatieve stapjes ook werken. 14 stapjes is een rondje op het bord.
        if (stapjes == 1) {
            return volgendBakje;
        }
        else {
            return volgendBakje.getVolgendBakje(stapjes-1);
        }
    }
    protected Speler getEigenaar() {
        return eigenaar;
    }

    //
    // Pas het aantal steentjes aan.
    //
    protected void leeg() {
        this.steentjes = 0;
    }
    protected void voegSteentjeToe() {
        this.steentjes++;
    }

    //
    // Abstracte methodes
    //
    protected abstract abstractBakje getKalaha();
    protected abstract void voegGestolenSteentjesToeAanKalaha(int aantal);
    protected abstract void geefDoor(int aantal);
    protected abstract boolean dezeEnAlleVolgendeLeeg();
    protected abstract void actieBijLaatsteSteentje();
}