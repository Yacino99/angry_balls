package vues;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.HeadlessException;

import musique.SonLong;

/**
 * représente une case é cocher (ou bouton de radio) pour choisir un son pour la
 * bille hurlante
 * 
 * ICI : IL N'Y A RIEN A CHANGER
 * 
 */
public class BoutonChoixHurlement extends Checkbox {
	public SonLong sonLong;

	/**
	 * @param sonLong : son associé é ce bouton
	 */
	public BoutonChoixHurlement(CheckboxGroup group, boolean state, SonLong sonLong) throws HeadlessException {
		super(sonLong.getNom(), group, state);
		this.sonLong = sonLong;
	}

}
