package canard;

import comportement.cancanement.ComportementCancan;
import comportement.voler.ComportementVol;

abstract public class Canard{

    ComportementVol comportementVol;
    ComportementCancan comportementCancan;

    public void nager() {
        System.out.println("Je nage");
    }

    public void effectuerCancan() {
        comportementCancan.cancanner();
    }

    public void effectuerVol() {
        comportementVol.voler();
    }

    public String afficher(){
        return this.toString();
    }

    public void setComportementVol(ComportementVol comportementVol) {
        this.comportementVol = comportementVol;
    }

    public void setComportementCancan(ComportementCancan comportementCancan) {
        this.comportementCancan = comportementCancan;
    }
}
