package nl.sogyo.mancala;

public class Kalaha extends abstractBakje {
    //
    // Constructor
    //
    protected Kalaha(Speler speler) {
        super(0,null,speler);
    }

    //
    // Implementeer abstracte methodes
    //
    protected abstractBakje getKalaha() {
        return this;
    }
    protected void voegGestolenSteentjesToeAanKalaha(int aantal) {
        for (int i=0; i<aantal; i++) voegSteentjeToe();
    }
    protected void geefDoor(int aantal) {
        if (aantal>1) {
            if (this.getEigenaar().isAanDeBeurt()) {
                this.voegSteentjeToe();
                this.getVolgendBakje().geefDoor(aantal - 1);
            }
            else {
                this.getVolgendBakje().geefDoor(aantal);
            }
        }
        else if (aantal == 1) {
            this.actieBijLaatsteSteentje();
        }
        else {
            System.out.println("Verkeerd aantal doorgegeven steentjes!");
        }
    }
    protected boolean dezeEnAlleVolgendeLeeg() {
        return true;
    }
    protected void actieBijLaatsteSteentje() {
        if (this.getEigenaar().isAanDeBeurt()) {
            this.voegSteentjeToe();
            this.getEigenaar().setMagNogEenKeer(true);
        }
        else {
            this.getVolgendBakje().geefDoor(1);
        }
    }
}