package canard;

import comportement.cancanement.Cancan;
import comportement.voler.VolerAvecDesAiles;

public class Mandarin extends Canard {

    public Mandarin() {
        setComportementVol(new VolerAvecDesAiles());
        setComportementCancan(new Cancan());
    }

    @Override
    public String afficher() {
        return "Je suis un beau Mandarin";
    }
}
