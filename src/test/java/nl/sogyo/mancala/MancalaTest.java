package nl.sogyo.mancala;

import org.junit.Assert;
import org.junit.Test;

public class MancalaTest {

    @Test
    public void naSpelenIsEenBakjeLeeg() {
        Bakje testBakje = new Bakje();
        testBakje.speelBakje();
        Assert.assertTrue(testBakje.getSteentjes()==0);
    }

    @Test
    public void erIsEenKalaha() {
        Bakje testBakje = new Bakje();
        Assert.assertTrue(testBakje.getKalaha() instanceof Kalaha);
    }

    @Test
    public void hetZevendeBakjeIsEenKalaha() {
        Bakje testBakje = new Bakje();
        Assert.assertTrue(testBakje.getVolgendBakje(6) instanceof Kalaha);
    }

    @Test
    public void hetVeertiendeBakjeIsEenKalaha() {
        Bakje testBakje = new Bakje();
        Assert.assertTrue(testBakje.getVolgendBakje(13) instanceof Kalaha);
    }

    @Test
    public void hetVijftiendeBakjeBenJeZelf() {
        Bakje testBakje = new Bakje();
        Assert.assertTrue(testBakje == testBakje.getVolgendBakje(14));
    }

    @Test
    public void negatieveStapjesWerkenOok() {
        Bakje testBakje = new Bakje();
        Assert.assertTrue((testBakje.getVolgendBakje(-1) == testBakje.getVolgendBakje(13)) && (testBakje.getVolgendBakje(0) == testBakje));
    }

    @Test
    public void testSpelerInitialisatie() {
        Bakje testBakje = new Bakje();
        Speler speler1 = testBakje.getEigenaar();
        Speler speler2 = testBakje.getVolgendBakje(7).getEigenaar();

        boolean testGelukt = true;
        for (int i=0; i<14; i++) {
            abstractBakje huidigBakje = testBakje.getVolgendBakje(i);
            if (i<7) {
                if (huidigBakje.getEigenaar()!=speler1) {
                    testGelukt = false;
                }
            }
            else {
                if (huidigBakje.getEigenaar()!=speler2) {
                    testGelukt = false;
                }
            }
        }

        Assert.assertTrue(testGelukt);
    }

    @Test
    public void testSteentjesInitialisatie() {
        Bakje testBakje = new Bakje();

        boolean testGelukt = true;
        for (int i=0; i<14; i++) {
            abstractBakje huidigBakje = testBakje.getVolgendBakje(i);
            if (i==6 || i==13) {
                if (huidigBakje.getSteentjes()!=0) {
                    testGelukt = false;
                }
            }
            else {
                if (huidigBakje.getSteentjes()!=4) {
                    testGelukt = false;
                }
            }
        }

        Assert.assertTrue(testGelukt);
    }

    @Test
    public void voegGestolenSteentjesToeAanKalaha() {
        Bakje testBakje = new Bakje();
        int beginAantal = testBakje.getKalaha().getSteentjes();
        testBakje.voegGestolenSteentjesToeAanKalaha(10);
        Assert.assertTrue(testBakje.getKalaha().getSteentjes() == (beginAantal+10));
    }

    @Test
    public void testDatWeHetGoedeTegenoverliggendeBakjeKrijgen() {
        Bakje testBakje = new Bakje();
        Bakje bakje2 = (Bakje) testBakje.getVolgendBakje();
        Assert.assertTrue(bakje2.getTegenoverliggendBakje() == testBakje.getVolgendBakje(11));
    }

    @Test
    public void alsErTweeSteentjesDoorgegevenWordenDanKrijgtHetVolgendeBakje1SteentjeErbij() {
        Bakje testBakje = new Bakje();
        testBakje.getVolgendBakje().geefDoor(2);
        Assert.assertTrue(testBakje.getVolgendBakje().getSteentjes()==5);
    }

    @Test
    public void bijHetDoorgevenKrijgtDeEigenKalahaErEenSteentjeBij() {
        Bakje testBakje = new Bakje();
        testBakje.getEigenaar().setAanDeBeurt(true);
        testBakje.getVolgendBakje().geefDoor(10);
        Assert.assertTrue(testBakje.getKalaha().getSteentjes()==1);
    }

    @Test
    public void bijHetDoorgevenKrijgtDeAndereKalahaErGeenSteentjeBij() {
        Bakje testBakje = new Bakje();
        testBakje.getEigenaar().setAanDeBeurt(false);
        testBakje.getVolgendBakje().geefDoor(10);
        Assert.assertTrue(testBakje.getKalaha().getSteentjes()==0);
    }

    @Test
    public void kanCorrectTestenDatAlleBakjesVanDezeSpelerLeegZijn() {
        Bakje testBakje = new Bakje();
        for (int i=0; i<6; i++) {
            testBakje.getVolgendBakje(i).leeg();
        }
        Assert.assertTrue(testBakje.dezeEnAlleVolgendeLeeg());
    }

    @Test
    public void kanCorrectTestenDatDeBakjesNietLeegZijn() {
        Bakje testBakje = new Bakje();
        for (int i=0; i<6; i++) {
            testBakje.getVolgendBakje(i).leeg();
        }
        testBakje.getVolgendBakje(5).voegSteentjeToe();
        Assert.assertFalse(testBakje.dezeEnAlleVolgendeLeeg());
    }

    @Test
    public void alsLaatsteSteentjeInEigenKalahaEindigtDanMagJeNogEenKeer() {
        Bakje testBakje = new Bakje();
        testBakje.getEigenaar().setAanDeBeurt(true);
        testBakje.getVolgendBakje().geefDoor(6);
        Assert.assertTrue(testBakje.getEigenaar().magNogEenKeer());
    }

    @Test
    public void alsLaatsteSteentjeInEigenKalahaEindigtDanMagJeNogEenKeerMaarNietAlsHetNietJeEigenKalahaIs() {
        Bakje testBakje = new Bakje();
        testBakje.getEigenaar().setAanDeBeurt(false);
        testBakje.getVolgendBakje().geefDoor(6);
        Assert.assertFalse(testBakje.getEigenaar().magNogEenKeer());
    }

    @Test
    public void alsJeSteeltBelandenDeSteentjesVanHetTegenoverliggendeBakjeEnHetLaatsteSteentjeInJeEigenKalaha() {
        Bakje testBakje = new Bakje();
        testBakje.getEigenaar().setAanDeBeurt(true);
        testBakje.getVolgendBakje().leeg();
        testBakje.getVolgendBakje().geefDoor(1);
        Assert.assertTrue(testBakje.getKalaha().getSteentjes()==5);
    }

    @Test
    public void alsJeSteeltBlijftHetLaatsteBakjeLeeg() {
        Bakje testBakje = new Bakje();
        testBakje.getEigenaar().setAanDeBeurt(true);
        testBakje.getVolgendBakje().leeg();
        testBakje.getVolgendBakje().geefDoor(1);
        Assert.assertTrue(testBakje.getVolgendBakje().getSteentjes()==0);
    }

    @Test
    public void alsJeSteeltWordtHetTegenoverliggendeBakjeLeeg() {
        Bakje testBakje = new Bakje();
        testBakje.getEigenaar().setAanDeBeurt(true);
        testBakje.getVolgendBakje().leeg();
        testBakje.getVolgendBakje().geefDoor(1);
        Assert.assertTrue(testBakje.getVolgendBakje(11).getSteentjes()==0);
    }

    @Test
    public void speelEenPaarZettenEnKijkOfHetKlopt() {
        Bakje testBakje = new Bakje();
        testBakje.speelBakje(3);
        Assert.assertTrue(testBakje.getSteentjes()==4);
        Assert.assertTrue(testBakje.getVolgendBakje(2).getSteentjes()==0);
        Assert.assertTrue(testBakje.getKalaha().getSteentjes()==1);
        Assert.assertTrue(testBakje.getEigenaar().magNogEenKeer());
        testBakje.speelBakje(6);
        Assert.assertTrue(testBakje.getSteentjes()==4);
        Assert.assertTrue(testBakje.getVolgendBakje(7).getSteentjes()==5);
        Assert.assertTrue(testBakje.getKalaha().getSteentjes()==2);
        Assert.assertFalse(testBakje.getEigenaar().magNogEenKeer());
        testBakje.speelBakje(13);
        Assert.assertTrue(testBakje.getSteentjes()==5);
        Assert.assertTrue(testBakje.getVolgendBakje(2).getSteentjes()==1);
        Assert.assertTrue(testBakje.getKalaha().getSteentjes()==2);
        Assert.assertFalse(testBakje.getEigenaar().magNogEenKeer());
        testBakje.speelBakje(1);
        Assert.assertTrue(testBakje.getSteentjes()==0);
        Assert.assertTrue(testBakje.getVolgendBakje(2).getSteentjes()==2);
        Assert.assertTrue(testBakje.getKalaha().getSteentjes()==8);
        Assert.assertFalse(testBakje.getEigenaar().magNogEenKeer());
        testBakje.speelBakje(9);
        Assert.assertTrue(testBakje.getSteentjes()==0);
        Assert.assertTrue(testBakje.getVolgendBakje(3).getSteentjes()==6);
        Assert.assertTrue(testBakje.getKalaha().getSteentjes()==8);
        Assert.assertFalse(testBakje.getEigenaar().magNogEenKeer());
        testBakje.speelBakje(13);
        testBakje.speelBakje(12);
        testBakje.speelBakje(2);
        testBakje.speelBakje(13);
        testBakje.speelBakje(10);
        testBakje.speelBakje(6);
        Assert.assertTrue(testBakje.getSteentjes()==2);
        Assert.assertTrue(testBakje.getVolgendBakje(3).getSteentjes()==7);
        Assert.assertTrue(testBakje.getKalaha().getSteentjes()==10);
        Assert.assertTrue(testBakje.getEigenaar().magNogEenKeer());
        testBakje.speelBakje(3);
        testBakje.speelBakje(2);
        testBakje.speelBakje(13);
        testBakje.speelBakje(12);
        Assert.assertTrue(testBakje.getSteentjes()==0);
        Assert.assertTrue(testBakje.getVolgendBakje(3).getSteentjes()==8);
        Assert.assertTrue(testBakje.getKalaha().getSteentjes()==19);
        Assert.assertFalse(testBakje.getEigenaar().magNogEenKeer());
        testBakje.speelBakje(9);        // Negeer beurtvolgorde.
        testBakje.speelBakje(8);
    }
}
