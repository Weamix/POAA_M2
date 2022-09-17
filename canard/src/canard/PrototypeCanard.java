package canard;

import comportement.cancanement.Cancan;
import comportement.voler.NePasVoler;

public class PrototypeCanard extends Canard{
    public PrototypeCanard() {
        setComportementCancan(new Cancan());
        setComportementVol(new NePasVoler());
    }

    @Override
    public String afficher() {
        return "Je suis un proto";
    }
}
