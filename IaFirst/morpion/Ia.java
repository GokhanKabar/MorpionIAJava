import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

public class Ia{

    int[] coins = new int[]{ 0,2,8,6 }; 
    int[] bords = new int[]{ 1,5,7,3 }; 

    Ia(){
        }
    
    public void iaMorpion(JButton[] boutons, String signe, String opposant){
        int nbTour = getNbTour(boutons);
        if(nbTour == 0){
            boutons[8].setText(signe);
            return;
        }
        
        //Regarde si il peux gagner
        int placement = getPlaceManquant(boutons,signe);
        if(placement >= 0){
            boutons[placement].setText(signe);
            return;
        }
        
        //Empêche l'adversaire de gagner
        placement = getPlaceManquant(boutons,opposant);
        if(placement >= 0){
            boutons[placement].setText(signe);
            return;
        }
        
        if(nbTour == 2){
            for(int i = 0; i< 4 ; i++){
                if(boutons[bords[i]].getText() == opposant){
                    boutons[4].setText(signe);
                    return;
                }
            }
            if(boutons[0].getText() != "" ){
                boutons[2].setText(signe);
                return;
            }
            else{
                boutons[0].setText(signe);
                return;
            }
        }
        
        if(nbTour == 1){
            for(int i = 0; i< 4 ;i++){
                if(boutons[coins[i]].getText() == opposant){
                    boutons[4].setText(signe);
                    return;
                }
            }
            for(int i = 0; i< 4 ;i++){
                if(boutons[bords[i]].getText() == opposant){
                    boutons[coins[i]].setText(signe);
                    return;
                }
            }
        }
        
        if(nbTour == 3){
            for(int i = 0; i< 4 ;i++){
                if(boutons[coins[i]].getText() == opposant){
                int place = (i+2)%4;
                    if(boutons[coins[place]].getText() == ""){
                        boutons[coins[place]].setText(signe);
                        return;
                    }
                    else if(boutons[coins[place]].getText() == opposant){
                        boutons[bords[place]].setText(signe);
                    }
                }
            }
        }
            
        for(int i = 0; i< 4 ;i++){
            if(boutons[coins[i]].getText() == ""){
                boutons[coins[i]].setText(signe);
                return;
            }
        }
        
        //si il reste une case vide pour arriver au nul
        for(int i = 0; i< 9 ;i++){
            if(boutons[i].getText() == ""){
                boutons[i].setText(signe);
                return;
            }
        }
    }
    
    
    
    //retourne le numéro de la case à entrer pour compléter une ligne de 3
    private int getPlaceManquant(JButton[] boutons, String signe){
        //check ligne
        for(int i = 0; i < 3; i++){
            int similar = 0;
            for(int j = 0; j< 3; j++){
                if(boutons[i*3+j].getText() == signe ){
                    similar++;
                }
                else if(boutons[i*3+j].getText() != "" ){
                    j = 3;
                    similar = 0;
                    continue;
                }
            }
            if(similar == 2){
                int place = 0;
                for(int j = 0; j< 3; j++){
                    if(boutons[i*3+j].getText() == "" ){
                        place = i*3+j;
                        j=3;
                        continue;
                    }
                }
                return place;
            }
        }
        //check colonnes
        for(int i = 0; i < 3; i++){
            int similar = 0;
            for(int j = 0; j< 3; j++){
                if(boutons[j*3+i].getText() == signe ){
                    similar++;
                }
                else if(boutons[j*3+i].getText() != "" ){
                    j = 3;
                    similar = 0;
                    continue;
                }
            }            

            if(similar == 2){
                int place = 0;
                for(int j = 0; j< 3; j++){
                    if(boutons[j*3+i].getText() == "" ){
                        place = j*3+i;
                        j=3;
                        continue;
                    }
                }
                return place;
            }
        }
        
        int similarFirst = 0;
        int similarSec = 0;
        int place = -1;
        if(boutons[0].getText() == signe){
            similarFirst++;
        }
        else if(boutons[0].getText() == ""){
            place = 0;
        }
        if(boutons[8].getText() == signe){
            similarFirst++;
        }
        else if(boutons[8].getText() == ""){
            place = 8;
        }
        if(boutons[4].getText() == signe){
            similarFirst++;
            similarSec++;
        }
        else if(boutons[4].getText() == ""){
            place = 4;
        }
        if(similarFirst == 2 && place >= 0){
            return place;
        }
        
        if(boutons[2].getText() == signe){
            similarSec++;
        }
        else if(boutons[2].getText() == ""){
            place = 2;
        }
        
        if(boutons[6].getText() == signe){
            similarSec++;
        }
        else if(boutons[6].getText() == ""){
            place = 6;
        }
        if(similarSec == 2){
            return place;
        }
        
        return -1;
    }
    
    
    //Regarde le nombre de tours écoulé
    private int getNbTour(JButton[] boutons){
        int nb = 0;
        for(int i = 0; i < 9; i++){
            if(boutons[i].getText() != ""){
                nb++;
            }
        }
        return nb;
    }
}
