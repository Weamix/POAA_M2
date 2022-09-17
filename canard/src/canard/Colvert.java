package canard;

import comportement.cancanement.Cancan;
import comportement.voler.VolerAvecDesAiles;

public class Colvert extends Canard {

    public Colvert() {
        setComportementVol(new VolerAvecDesAiles());
        setComportementCancan(new Cancan());
    }

    @Override
    public String afficher() {
        return "Je suis un beau Colvert";
    }
}
