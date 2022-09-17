package canard;

import comportement.cancanement.Coincoin;
import comportement.voler.NePasVoler;

public class CanardEnPlastique extends Canard{

    public CanardEnPlastique() {
        setComportementCancan(new Coincoin());
        setComportementVol(new NePasVoler());
    }

    @Override
    public String afficher() {
        return "Plastoc canard";
    }
}
