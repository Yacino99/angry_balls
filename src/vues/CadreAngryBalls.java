package vues;

import java.awt.*;
import java.awt.event.ItemListener;
import java.awt.image.BufferStrategy;
import java.util.Vector;

import modele.Bille;
import musique.SonLong;
import outilsvues.EcouteurTerminaison;

import outilsvues.Outils;

/**
 * Vue dessinant les billes et contenant les 3 boutons de contréle (arrét du
 * programme, lancer les billes, arréter les billes) et contenant la ligne des
 * boutons de choix des sons pour la bille hurlante
 * 
 * ICI : IL N'Y A RIEN A CHANGER
 * 
 * 
 */
public class CadreAngryBalls extends Frame implements VueBillard {
	TextField présentation;
	public Billard billard;
	public Button lancerBilles, arréterBilles;
	Panel haut, centre, bas, ligneBoutonsLancerArrét;
	PanneauChoixHurlement ligneBoutonsChoixHurlement;

	EcouteurTerminaison ecouteurTerminaison;

	public CadreAngryBalls(String titre, String message, Vector<Bille> billes, SonLong[] hurlements,
			int choixHurlementInitial) throws HeadlessException {
		super(titre);
		Outils.place(this, 0.33, 0.33, 0.5, 0.5);
		this.ecouteurTerminaison = new EcouteurTerminaison(this);

		this.haut = new Panel();
		this.haut.setBackground(Color.LIGHT_GRAY);
		this.add(this.haut, BorderLayout.NORTH);

		this.centre = new Panel();
		this.add(this.centre, BorderLayout.CENTER);

		this.bas = new Panel();
		this.bas.setBackground(Color.LIGHT_GRAY);
		this.add(this.bas, BorderLayout.SOUTH);

		this.présentation = new TextField(message, 100);
		this.présentation.setEditable(false);
		this.haut.add(this.présentation);

		this.billard = new Billard(billes);
		this.add(this.billard);

//------------------- placement des composants du bas du cadre -------------------------------

		int nombreLignes = 2, nombreColonnes = 1;

		this.bas.setLayout(new GridLayout(nombreLignes, nombreColonnes));

//---------------- placement des boutons lancer - arréter ------------------------------------

		this.ligneBoutonsLancerArrét = new Panel();
		this.bas.add(this.ligneBoutonsLancerArrét);

		this.lancerBilles = new Button("lancer les billes");
		this.ligneBoutonsLancerArrét.add(this.lancerBilles);
		this.arréterBilles = new Button("arréter les billes");
		this.ligneBoutonsLancerArrét.add(this.arréterBilles);

//---------------- placement de la ligne de boutons de choix des sons pour le hurlement ------

		this.ligneBoutonsChoixHurlement = new PanneauChoixHurlement(hurlements, choixHurlementInitial);
		this.bas.add(this.ligneBoutonsChoixHurlement);

	}

	public double largeurBillard() {
		return this.billard.getWidth();
	}

	public double hauteurBillard() {
		return this.billard.getHeight();
	}

	@Override
	public void miseAJour() {
		//this.billard.repaint();

		// BufferStrategy pour l'Active Rendering

		BufferStrategy buffer = billard.getBufferStrategy();



		Graphics graph = buffer.getDrawGraphics();
		graph.clearRect(0,0,getWidth(),getHeight());
		this.billard.paint(graph);
		graph.dispose();
		buffer.show();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see exodecorateur.vues.VueBillard#montrer()
	 */
	@Override
	public void montrer() {
		this.setVisible(true);
		// Active Rendering
		this.billard.createBufferStrategy(2);
		this.billard.setIgnoreRepaint(true);
	}

	public void addChoixHurlementListener(ItemListener écouteurChoixHurlant) {
		int i;

		for (i = 0; i < this.ligneBoutonsChoixHurlement.boutons.length; ++i)
			this.ligneBoutonsChoixHurlement.boutons[i].addItemListener(écouteurChoixHurlant);

	}

}