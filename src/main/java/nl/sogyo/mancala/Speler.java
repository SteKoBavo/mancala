package nl.sogyo.mancala;

class Speler {
    private boolean aanDeBeurt = false;
    private boolean magNogEenKeer = false;

    protected boolean isAanDeBeurt() {
        return aanDeBeurt;
    }
    protected void setAanDeBeurt(boolean aanDeBeurt) {
        this.aanDeBeurt = aanDeBeurt;
    }

    protected boolean magNogEenKeer() {
        return magNogEenKeer;
    }
    protected void setMagNogEenKeer(boolean magNogEenKeer) {
        this.magNogEenKeer = magNogEenKeer;
    }
}