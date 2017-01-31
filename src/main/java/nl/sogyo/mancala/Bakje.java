package nl.sogyo.mancala;

public class Bakje extends abstractBakje {
    //
    // Constructors
    //
    public Bakje() {
        this(1, new Speler());
        Bakje bakjeVanAndereSpeler = new Bakje(1,new Speler());
        this.verbindBakjes(bakjeVanAndereSpeler);
    }
    private Bakje(int bakjeNummer, Speler speler) {
        super(4, (bakjeNummer<6) ? (new Bakje(bakjeNummer+1,speler)) : (new Kalaha(speler)), speler);
    }

    //
    // Implementeer abstracte methodes
    //
    protected abstractBakje getKalaha() {
        return getVolgendBakje().getKalaha();
    }
    protected void voegGestolenSteentjesToeAanKalaha(int aantal) {
        getVolgendBakje().voegGestolenSteentjesToeAanKalaha(aantal);
    }
    protected void geefDoor(int aantal) {
        if (aantal>1) {
            this.voegSteentjeToe();
            this.getVolgendBakje().geefDoor(aantal-1);
        }
        else if (aantal == 1) {
            this.actieBijLaatsteSteentje();
        }
        else {
            System.out.println("Verkeerd aantal doorgegeven steentjes!");
        }
    }
    protected boolean dezeEnAlleVolgendeLeeg() {
        if (this.getSteentjes() == 0) {
            return getVolgendBakje().dezeEnAlleVolgendeLeeg();
        }
        else {
            return false;
        }
    }
    protected void actieBijLaatsteSteentje() {
        if ((this.getSteentjes() == 0) && (this.getEigenaar().isAanDeBeurt())) {
            if (this.getTegenoverliggendBakje().getSteentjes()>0) {
                int gestolenSteentjes = this.getTegenoverliggendBakje().besteel();
                this.voegGestolenSteentjesToeAanKalaha(gestolenSteentjes+1);
            }
            else {
                this.voegSteentjeToe();
            }
        }
        else {
            this.voegSteentjeToe();
        }
    }

    //
    // Eigen methodes
    //
    protected Bakje getTegenoverliggendBakje() {
        for (int i=1; i<=6; i++) {
            if (this.getVolgendBakje(i) instanceof Kalaha) {
                return (Bakje) this.getVolgendBakje(2*i);
            }
        }
        return null;
    }
    private int besteel() {
        int gestolenSteentjes = this.getSteentjes();
        this.leeg();
        return gestolenSteentjes;
    }
    protected void speelBakje() {
        int doorTeGevenSteentjes = this.getSteentjes();
        if (doorTeGevenSteentjes<1) {
            System.out.println("Je kan geen leeg bakje spelen!");
            return;
        }

        this.getEigenaar().setAanDeBeurt(true);
        this.getEigenaar().setMagNogEenKeer(false);
        this.getVolgendBakje(7).getEigenaar().setAanDeBeurt(false);

        this.leeg();
        this.getVolgendBakje().geefDoor(doorTeGevenSteentjes);
        this.testEindeSpel();
    }
    public void speelBakje(int bakjeNummer) {
        abstractBakje teSpelenBakje = this.getVolgendBakje(bakjeNummer-1);
        if (teSpelenBakje instanceof Bakje) {
            ((Bakje) teSpelenBakje).speelBakje();
        }
        else {
            System.out.println("Je kan geen Kalaha spelen!");
        }
    }
    private void testEindeSpel() {
        if (this.getVolgendBakje(7).getKalaha().getVolgendBakje().dezeEnAlleVolgendeLeeg()) {
            System.out.println("Je hebt geen steentjes meer in je bakjes.");
            int punten = this.getKalaha().getSteentjes();
            System.out.println("Je hebt "+punten+" punten en je tegenstander heeft "+(48-punten)+" punten.");
            if (punten>24) {
                System.out.println("Je hebt gewonnen!");
            }
            else if (punten == 24) {
                System.out.println("Het is gelijkspel!");
            }
            else {
                System.out.println("Je hebt verloren!");
            }
        }
        else if (this.getKalaha().getVolgendBakje().dezeEnAlleVolgendeLeeg()) {
            System.out.println("Je tegenstander heeft geen steentjes meer in de bakjes.");
            int punten = 48 - this.getVolgendBakje(7).getKalaha().getSteentjes();
            System.out.println("Je hebt "+punten+" punten en je tegenstander heeft "+(48-punten)+" punten.");
            if (punten>24) {
                System.out.println("Je hebt gewonnen!");
            }
            else if (punten == 24) {
                System.out.println("Het is gelijkspel!");
            }
            else {
                System.out.println("Je hebt verloren!");
            }
        }
    }
}