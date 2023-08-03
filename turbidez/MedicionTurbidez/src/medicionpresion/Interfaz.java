/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicionpresion;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.awt.Color;
import static java.awt.Color.BLACK;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 *
 * @author Pablo
 */
public class Interfaz extends JFrame {

    //Declaraciones
    String niv, tur, se, tu;
    public boolean openedOnce;
    boolean ban = false, band = false;
    double t, l = 0, setp = 0;
    int i;
    char letra[];
    public JButton in;
    public JTextField set;
    peticion pet;
    int d;
    JLabel envase, mens, por, ntu, nombres, titulo, limMax, limMin, manch, no, SET;
    //Llamado a libreria para conexion
    PanamaHitek_Arduino enviar = new PanamaHitek_Arduino();
    //Evento de Medicion
    SerialPortEventListener listener = new SerialPortEventListener() {
        @Override
        public void serialEvent(SerialPortEvent spe) {
            try {
                if (enviar.isMessageAvailable()) //Mientras la cnexion este establecida recibira datos
                {
                    try {
                        tur = enviar.printMessage();
                        for (int i = 0; i < tur.length(); i++) {
                            letra[i] = tur.charAt(i);
                            //Tratamiento del caracter
                        }
                        niv = ("" + (char) letra[0] + "");
                        tu = ("" + letra[1] + "" + letra[2] + "" + letra[3] + "");
                        convercion(niv, tu);
                        System.out.println("nivel: " + i);
                        System.out.println("turbidez: " + l);
                        System.out.println(d);
                        por.setText(+l + "-NTU");
                        ntu.setText(+l+"-NTU");
                        if (ban == true) {
                            changeJar(i);
                            mancha(l);
                            mandar(i, l);
                        }
                    } catch (Exception ex) //En caso de error
                    {
                        System.out.println("Error al convertir");                   //No conversion de analogo a digital
                    }
                }
            } catch (SerialPortException ex) {                                  //Excepciones de conectivilidad

            } catch (ArduinoException ex) {

            }
        }
    };

    public Interfaz() {
        setContentPane(new JLabel(new ImageIcon(getClass().getResource("/imagenes/fon.png"))));
        envase = new JLabel(new ImageIcon(getClass().getResource("/imagenes/full.png")));
        letra = new char[4];
        manch = new JLabel(new ImageIcon(getClass().getResource("/imagenes/m1.png")));
        limMax = new JLabel("Rango Maximo");
        limMin = new JLabel("Rango Minimo");
        nombres = new JLabel("Omar Ildefonso Godinez Quinones");
        titulo = new JLabel("MEDICION DE TURBIDEZ A TRAVES DE DISIPACION DE LUZ");
        mens = new JLabel();
        por = new JLabel("-NTU");
        ntu = new JLabel("-NTU");
        set = new JTextField();
        in = new JButton("Iniciar");
        no = new JLabel("valor no valido");
        SET = new JLabel();
        mens.setSize(300, 50);
        mens.setLocation(500, 200);
        titulo.setLocation(450, 0);
        titulo.setSize(1400, 100);
        nombres.setLocation(700, 80);
        nombres.setSize(550, 40);
        por.setSize(200, 50);
        por.setLocation(500, 500);
        ntu.setSize(500, 50);
        ntu.setLocation(750, 700);
        envase.setLocation(100, 80);
        envase.setSize(320, 420);
        manch.setLocation(100, 500);
        manch.setSize(350, 350);

        mens.setFont(new Font("Arial", Font.PLAIN, 20));
        titulo.setFont(new Font("Georgia", Font.BOLD, 40));
        por.setFont(new Font("Arial", Font.PLAIN, 20));
        ntu.setFont(new Font("Arial", Font.BOLD, 40));
        nombres.setFont(new Font("Georgia", Font.BOLD, 30));
        //
        pet = new peticion();
        //pet.setVisible(true);
        in.setLocation(150, 150);//////////////////////////////////////////////////////////////////////
        in.setSize(200, 50);
        in.setFont(new Font("Arial", Font.PLAIN, 30));

        set.setLocation(200, 100);
        set.setSize(50, 40);
        set.setFont(new Font("Arial", Font.PLAIN, 30));

        no.setLocation(150, 250);
        no.setSize(300, 40);
        no.setFont(new Font("Arial", Font.PLAIN, 30));
        no.setVisible(false);
        
        SET.setLocation(210, 100);
        SET.setSize(50, 40);
        SET.setFont(new Font("Arial", Font.PLAIN, 30));
        SET.setVisible(false);
        pet.add(in);
        pet.add(set);
        pet.add(no);
        pet.add(SET);

        in.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (set.getText() != "") {
                    se = set.getText();
                    setp = Double.parseDouble(se);
                    if (setp <= 4 && setp >= 1) {
                        SET.setText(se);
                        SET.setVisible(true);
                        set.setVisible(false);
                        in.setVisible(false);
                        ban = true;
                    } else {
                        no.setVisible(true);
                    }
                }
            }
        });
        //
        conectar();
        //Vista
        add(titulo);
        add(nombres);
        add(envase);
        add(manch);
        add(mens);
        //add(por);
        add(ntu);
        //setVisible(true);
        setSize(1920, 1080);
        setLayout(null);
        this.setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        if(openedOnce == false){
            new Splash(this, pet);
            openedOnce = true;
        }
    }

    //Conexion Arduino Java
    public void conectar() {
        try {
            enviar.arduinoRXTX("COM6", 9600, listener);
            System.out.println("Conexion exitosa");
        } catch (ArduinoException ex) {
            System.out.println("no se envio nada wey");
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Interfaz getMe()
    {
        return this;
    }
    public peticion getBack()
    {
        return pet;
    }

    public void mandar(int a, double b) {
        try {
            if ((a < 5 && a != 0)) {
                enviar.sendData("1");
                d=1;
                band = true;
            } else {
                if(a==0){
                    band=false;
                }
                if (b > setp && band==false) {
                    enviar.sendData("2");
                    d=2;
                } else if (b < (setp-0.9) && band==false) {
                    enviar.sendData("3");
                    d=3;
                }
            }
        } catch (ArduinoException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SerialPortException ex) {
            Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void changeJar(int c) {
        if (c < 6 && c != 0) {
            envase.setIcon(new ImageIcon(getClass().getResource("/imagenes/full3.png")));
            mens.setForeground(java.awt.Color.RED);
            mens.setText("El nivel de agua llego al limite");
        } else if (c >= 6 && c <= 7) {
            envase.setIcon(new ImageIcon(getClass().getResource("/imagenes/full2.png")));
            mens.setForeground(java.awt.Color.GREEN);
            mens.setText("Nivel de liquido a punto de llegar al limite");
        } else if (c >= 8 || c == 0) {
            envase.setIcon(new ImageIcon(getClass().getResource("/imagenes/full.png")));
            mens.setForeground(java.awt.Color.BLUE);
            mens.setText("Nivel de liquido aceptable");
        }
    }

    public void mancha(double t) {
        if (t < 0.8) {
            manch.setIcon(new ImageIcon(getClass().getResource("/imagenes/m1.png")));
        } else if (t >= 0.9 && t < 1) {
            manch.setIcon(new ImageIcon(getClass().getResource("/imagenes/m2.png")));
        } else if (t >= 1 && t < 1.5) {
            manch.setIcon(new ImageIcon(getClass().getResource("/imagenes/m3.png")));
        } else if (t >= 1.5 && t < 2.5) {
            manch.setIcon(new ImageIcon(getClass().getResource("/imagenes/m4.png")));
        } else if (t >= 2.5 && t < 3.5) {
            manch.setIcon(new ImageIcon(getClass().getResource("/imagenes/m5.png")));
        } else if (t >= 3.5 && t < 4) {
            manch.setIcon(new ImageIcon(getClass().getResource("/imagenes/m6.png")));
        }
    }

    public void convercion(String nive, String turv) {
        t = Integer.parseInt(turv);
        i = Integer.parseInt(nive);
        l = ((t * 0.005) - 5) * -1;
    }
}
