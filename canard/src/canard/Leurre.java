package canard;

import comportement.cancanement.CanardMuet;
import comportement.voler.NePasVoler;

public class Leurre extends Canard{

    public Leurre() {
        setComportementCancan(new CanardMuet());
        setComportementVol(new NePasVoler());
    }

    @Override
    public String afficher() {
        return "canard.Leurre";
    }
}
