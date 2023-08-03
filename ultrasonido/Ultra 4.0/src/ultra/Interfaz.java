/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultra;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Omar xv
 */
public class Interfaz extends JFrame{
    JLabel envase, mens, por,nombres,titulo,limMax,limMin;
    UltraAD UAD;
    boolean flagYa = false;
    JButton barra;
    String strn;
    int i, t, l;
    public Interfaz(){
        //constructores
        super();
        UAD = new UltraAD();
        envase = new JLabel(new ImageIcon(getClass().getResource("imagenies/EMPTY.png")));
        barra = new JButton("");
        limMax=new JLabel("Rango Maximo");
        limMin=new JLabel("Rango Minimo");
        nombres=new JLabel("");
        titulo=new JLabel("MEDICION DE NIVEL ATRAVEZ DE ULTRASONIDO");
        barra.setVisible(true);
        mens = new JLabel();
        //SETTERS
        setLayout(null);
        setVisible(true);
        mens.setSize(150, 50);
        mens.setLocation(500, 200);
        mens.setFont(new Font("Arial",Font.PLAIN,20));
        por= new JLabel("");
        titulo.setLocation(700,0);
        titulo.setSize(1200,100);
        titulo.setFont(new Font("Georgia",Font.BOLD,40));
        nombres.setLocation(700,20);
        nombres.setText("<html>Christian Axel Hernandez Cardenas<br><br>Omar Ildefonso Godinez Quinones<br><br>David Uziel Guevara Hernandez</html>");
        nombres.setSize(700,400 );
        nombres.setFont(new Font("Georgia",Font.BOLD,30));
        por.setSize(150, 50);
        por.setLocation(500, 300);
        por.setFont(new Font("Arial",Font.PLAIN,20));
        barra.setBackground(Color.red);
        barra.setSize(20, 400); //??????????????????????????????????????
        barra.setLocation(450, 150);
        add(titulo);
        add(nombres);
        add(envase);
        add(barra);
        add(mens);
        add(por);
        envase.setLocation(100,140);
        envase.setSize(320, 420);
        setSize(1920, 1080);
        //btnStart.addActionListener(new ActionListener() {
           // @Override
           // public void actionPerformed(ActionEvent e) {
                try{
                    while(flagYa != true){
                        for(int j=0; j<=20; j++){
                            strn = (""+(char)UAD.in.read()+""+(char)UAD.in.read()+"");
                            try{
                                i=Integer.parseInt(strn);
                                l=26-i;
                                por.setText(+l+"/20cm");
                                getMe().changeJar(l);
                                i=l*20;
                                barra.setSize(20, i);
                                barra.setLocation(450, 550-i);
                                System.out.println(i);
                            }catch(NumberFormatException ex){
                            }
                        }
                    }
                    UAD.in.close();
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            //}
        //});
        this.getContentPane().setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void changeJar(int c){
        if(c>15){
           envase.setIcon(new ImageIcon(getClass().getResource("imagenies/FULL.png")));
           mens.setForeground(java.awt.Color.RED);
           mens.setText("Demasiada agua");
        }
        else if(c > 10 && c<= 15){
           envase.setIcon(new ImageIcon(getClass().getResource("imagenies/FULL2.png")));
           mens.setForeground(java.awt.Color.GREEN);
           mens.setText("Nivel aceptable");
        }
        else if(c > 5 && c<=10){
           envase.setIcon(new ImageIcon(getClass().getResource("imagenies/FULL3.png")));
           mens.setForeground(java.awt.Color.GREEN);
           mens.setText("Nivel aceptable");
        }
        else if(c<= 5 && c > 1){
            envase.setIcon(new ImageIcon(getClass().getResource("imagenies/FULL4.png")));
            mens.setForeground(java.awt.Color.BLUE);
            mens.setText("Poca agua");
        }
        else{
            envase.setIcon(new ImageIcon(getClass().getResource("imagenies/EMPTY.png")));
            mens.setForeground(java.awt.Color.BLUE);
            mens.setText("Poca agua");
        }
    }
    public Interfaz getMe(){
        return this;
    }
}
