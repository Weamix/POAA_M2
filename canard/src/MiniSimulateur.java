import canard.*;
import comportement.voler.PropulsionAReaction;

public class MiniSimulateur {
    public static void main(String[] args) {

        Canard colvert = new Colvert();
        System.out.println("-------"+colvert.getClass()+"-------");
        colvert.effectuerCancan();
        colvert.effectuerVol();

        Canard mandardin = new Mandarin();
        System.out.println("-------"+mandardin.getClass()+"-------");
        mandardin.effectuerCancan();
        mandardin.effectuerVol();

        Canard leurre = new Leurre();
        System.out.println("-------"+leurre.getClass()+"-------");
        leurre.effectuerCancan();
        leurre.effectuerVol();

        Canard jaune = new CanardEnPlastique();
        System.out.println("-------"+jaune.getClass()+"-------");
        jaune.effectuerVol();
        jaune.effectuerCancan();

        Canard prototypeCanard = new PrototypeCanard();
        System.out.println("-------"+prototypeCanard.getClass()+"-------");
        prototypeCanard.effectuerVol();
        prototypeCanard.setComportementVol(new PropulsionAReaction());
        prototypeCanard.effectuerVol();
    }
}
