package modele.accelerations;

import modele.Bille;
import modele.DecorateurBille;

import java.util.Vector;

// Je ne sais pas si cette classe est necessaire puisque le mouvement est rectiligne uniforme par defaut
public class RU extends DecorateurBille {
    public RU(Bille billeDecoree) {
        super(billeDecoree);
    }

    public void gestionAccélération(Vector<Bille> billes) {
        super.gestionAccélération(billes);
    }
}
