package comportement.cancanement;

public class CanardMuet implements ComportementCancan{

    @Override
    public void cancanner() {
        System.out.println("Silence radio.. Aucun son n'est émis, le canard est muet");
    }
}
