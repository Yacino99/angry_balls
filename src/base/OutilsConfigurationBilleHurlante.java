package base;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import musique.SonLong;
import musique.SonLongFantome;
import musique.javax.SonLongJavax;

/**
 * sert é construire tous les sons qui seront proposés pour animer la bille hurlante
 * 
 *  ICI : IL N'Y A RIEN A CHANGER
 *  
 * */
public class OutilsConfigurationBilleHurlante
{

/**
 * La seule téche de cette méthode est d'ouvrir le fichier de configuration de SonLong de la bile hurlante
 * cf. méthode chargeSons1 juste aprés
 * */
public static Vector<SonLong>  chargeSons(File répertoireBruits, String nomFichierConfigAudio) //throws IOException
{
Vector<SonLong> résultat;
try
    {
    File f = new File(répertoireBruits,nomFichierConfigAudio);
    FileInputStream f1 = new FileInputStream(f);
    BufferedReader fichierConfigBilleHurlante = new BufferedReader(new InputStreamReader(f1));
    
    résultat = OutilsConfigurationBilleHurlante.chargeSons1(répertoireBruits, fichierConfigBilleHurlante);
    fichierConfigBilleHurlante.close();
    }

catch (IOException e)
    {
    résultat = new Vector<SonLong>();
    résultat.add(new SonLongFantome());
    System.err.println("sons indisponibles pour les hurlements");
    }
return résultat;
}

/**
 * Téche : construit une liste de SonLong.
 *  
 * @param répertoireBruits : construit une liste de SonLong é partir de fichiers audio placés sur le dossier défini par le chemin répertoireBruits
 * @param fichierConfigBilleHurlante : indique comment construire les SonLong.
 * 
 * fichierConfigBilleHurlante respecte le format suivant :
 * sur les 8 premiéres lignes est détaillé le format du fichier lui-méme
 * puis chaque ligne suivante permet de construire un SonLong. 
 * 
 * Exemple de fichierConfigBilleHurlante : 
 * 
        configuration des fichiers audios é utiliser pour la bille hurlante. Un fichier audio au format wav par ligne. 
        4 informations sur une ligne : 
        nom du fichier (sans l'extention .wav) début de l'extrait (en centiémes de secondes) fin de l'extrait (en centiémes de secondes) effectif (nombre de morceaux composant l'extrait)
        séparateur : espace. exemple :  sabrelaser 0 150 15  
        Important ===> On doit toujours avoir : effectif^2 >= (finExtrait - débutExtrait) / BilleHurlante.DELAI_MIN
        Important ===> On doit toujours avoir : (finExtrait - débutExtrait) / effectif >= SonJavax.TAILLE_BUFFER_LIGNE
        Les fichiers audio doivent étre dans le méme répertoire que ce fichier
        Les 8 premiéres lignes du fichier sont ignorées
        huey2 1200 1300 10
        spitfire 1100 1700 30
        sabrelaser 0 150 15
        loups 0 600 40
        crapaud 20 120 10
 * 
 * A partir de la 9éme ligne, toute ligne contenant une erreur est ignorée
 * 
 * Si é partir du fichier, on est incapable de construire au moins un SonLong valide, la méthode renvoie un Vector contenant un unique SonLongFantome
 * pour que l'application puisse quand méme tourner. 
 * */
public static Vector<SonLong>  chargeSons1(File répertoireBruits, BufferedReader fichierConfigBilleHurlante)
{
Vector<SonLong> sons = new Vector<SonLong>();

int i;
String ligne = null;

try
    {
    for (i = 0; i < 8; ++i) ligne = fichierConfigBilleHurlante.readLine(); /* on ignore les 8 premiéres lignes du fichier qui contiennent le résumé */
    }
catch (IOException e1)  /* l'entéte du fichier contient un probléme */
    {
    sons.add(new SonLongFantome());         /* on crée un son bidon pour que l'application puisse quand méme tourner sans la diffusion du son */
    return sons;
    }

/* é présent on est sér que la lecture des 8 lignes d'entéte s'est bien passée. le pointeur de ligne de fichierConfigBilleHurlante pointe sur la 9éme ligne */

for ( /* rien é faire ici*/; ligne != null; ++i)
    {
    try
      {
      ligne = fichierConfigBilleHurlante.readLine();        /*ligne est supposée respecter le format suivant : "spitfire 1100 1700 30" */
      
      if (ligne != null) sons.add(SonLongJavax.crée(répertoireBruits, ligne) );
      }
    catch (Exception e)
      {
      /* on ignore la ligne générant une erreur et on passe au prochain son sur la ligne suivante */
      System.err.println("Dans OutilsConfigurationBilleHurlante.chargeSons1() : ligne né " + i + " ignorée car contenant une erreur : " + e);            
      }
    }       // for

if (sons.isEmpty()) sons.add(new SonLongFantome());
return sons;
}
//SonLongJavax( File répertoire, String nomFichier, int débutExtrait, int finExtrait, int effectif)
}
