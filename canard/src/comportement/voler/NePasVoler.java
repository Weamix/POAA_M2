package comportement.voler;

public class NePasVoler implements ComportementVol{

    @Override
    public void voler() {
        System.out.println("Je ne sais pas voler");
    }
}
