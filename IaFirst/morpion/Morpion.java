import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

public class Morpion implements ActionListener{

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel titre_panel = new JPanel();
    JPanel bouton_panel = new JPanel();
    JLabel text = new JLabel();
    JButton[] boutons = new JButton[9];
    boolean joueur1;
    Ia bot = new Ia();
    
    Morpion(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        
        text.setBackground(Color.BLUE);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Ink Free",Font.BOLD,75));
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setText("MORPION ");
        text.setOpaque(true);

        titre_panel.setLayout(new BorderLayout());
        titre_panel.setBounds(0,0,800,100);

        bouton_panel.setLayout(new GridLayout(3,3));
        bouton_panel.setBackground(Color.white);

        for(int i=0;i<9;i++){
            boutons[i]= new JButton();
            bouton_panel.add(boutons[i]);
            boutons[i].setFont(new Font("MV Boli",Font.BOLD,120));
            boutons[i].setFocusable(false);
            boutons[i].addActionListener(this);
        }
        
        bot.iaMorpion(boutons,"0","X");
        titre_panel.add(text);
        frame.add(titre_panel,BorderLayout.NORTH);
        frame.add(bouton_panel);
        saisieUtilisateur();
        
    }
    
    /*Fonction permettant l'utilisation de l'ia */
    public void actionPerformed(ActionEvent e){
        for(int i=0;i<9;i++)
        {
            if(e.getSource()==boutons[i]){
                if(boutons[i].getText()==""){
                    boutons[i].setForeground(Color.BLACK);
                    boutons[i].setText("X");
                    text.setText("Joueur 0");
                    victoire();
                    
                    bot.iaMorpion(boutons,"0","X");
                    text.setText("Joueur X");
                    victoire();
                }
            }
        }
    }
    
    public void saisieUtilisateur(){
        try{
        Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        if(random.nextInt(2)==0){
            joueur1=true;
            text.setText("Joueur X");
        }
        else{
            joueur1=false;
            text.setText("Joueur 0");

        }
    }
    
    public void victoire(){
        // test des lignes 
        if((boutons[0].getText()=="X")&&(boutons[1].getText()=="X")&&(boutons[2].getText()=="X"))
        {
         xWin(0,1,2);
        }
        else if((boutons[3].getText()=="X")&&(boutons[4].getText()=="X")&&(boutons[5].getText()=="X"))
        {
         xWin(3,4,5);
        }
        else if((boutons[6].getText()=="X")&&(boutons[7].getText()=="X")&&(boutons[8].getText()=="X"))
        {
         xWin(6,7,8);
        }    
        // test des colonnes
        else if((boutons[0].getText()=="X")&&(boutons[3].getText()=="X")&&(boutons[6].getText()=="X"))
        {
         xWin(0,3,6);
        }
        else if((boutons[1].getText()=="X")&&(boutons[4].getText()=="X")&&(boutons[7].getText()=="X"))
        {
         xWin(1,4,7);
        }
        else if((boutons[2].getText()=="X")&&(boutons[5].getText()=="X")&&(boutons[8].getText()=="X"))
        {
         xWin(2,5,8);
        }
        //test de la diagonale
        else if((boutons[0].getText()=="X")&&(boutons[4].getText()=="X")&&(boutons[8].getText()=="X"))
        {
         xWin(0,4,8);
        }
        //test de la diagonale inverse
        else if((boutons[2].getText()=="X")&&(boutons[4].getText()=="X")&&(boutons[6].getText()=="X"))
        {
            xWin(2,4,6);
        }


        // test des lignes de 0 
        else if((boutons[0].getText()=="0")&&(boutons[1].getText()=="0")&&(boutons[2].getText()=="0"))
        {
         oWin(0,1,2);
        }
        else if((boutons[3].getText()=="0")&&(boutons[4].getText()=="0")&&(boutons[6].getText()=="0"))
        {
         oWin(3,4,5);
        }
        else if((boutons[6].getText()=="0")&&(boutons[7].getText()=="0")&&(boutons[8].getText()=="0"))
        {
         oWin(6,7,8);
        }
            
    // test des colonnes
    else if((boutons[0].getText()=="0")&&(boutons[3].getText()=="0")&&(boutons[6].getText()=="0"))
    {
     oWin(0,3,6);
    }
    else if((boutons[1].getText()=="0")&&(boutons[4].getText()=="0")&&(boutons[7].getText()=="0"))
    {
        oWin(1,4,7);
    }
   else  if((boutons[2].getText()=="0")&&(boutons[5].getText()=="0")&&(boutons[8].getText()=="0"))
    {
        oWin(2,5,8);
    }
    
    //test de la diagonale de 0
    else if((boutons[0].getText()=="0")&&(boutons[4].getText()=="0")&&(boutons[8].getText()=="0"))
    {
      oWin(0,4,8);
    }
    //test de la diagonale inverse 0
    else if((boutons[2].getText()=="0")&&(boutons[4].getText()=="0")&&(boutons[6].getText()=="0"))
    {
         oWin(2,4,6);
    }
    else{
        int p=0;
        for(int i=0;i<9;i++){
            if(boutons[i].getText()!=""){
                p++;
            }
        }
        if(p==9){
        text.setText("Match Null");
        for(int i=0;i<9;i++)
        {
        boutons[i].setBackground(Color.GRAY);
        }
    }

    }

    }

    public void xWin(int a,int b,int c){
        boutons[a].setBackground(Color.GREEN);
        boutons[b].setBackground(Color.GREEN);
        boutons[c].setBackground(Color.GREEN);
        for(int i=0;i<9;i++)
        {
            boutons[i].setEnabled(false);
        }
        text.setText("Vainqueur X ");
    } 

    public void oWin(int a,int b,int c){
        boutons[a].setBackground(Color.GREEN);
        boutons[b].setBackground(Color.GREEN);
        boutons[c].setBackground(Color.GREEN);
        for(int i=0;i<9;i++)
        {
            boutons[i].setEnabled(false);
        }
        text.setText("Vainqueur 0 ");
    }

}
