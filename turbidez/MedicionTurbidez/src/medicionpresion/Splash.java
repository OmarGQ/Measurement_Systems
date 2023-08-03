/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicionpresion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Omar xv
 */
public class Splash extends JFrame{
    private Interfaz padre;
    private peticion back;
    private int onNow;
    private Timer tmrTiempo;
    public Splash (Interfaz padre, peticion back)
    {
        super ();
        this.padre = padre;
        this.back = back;
        onNow = 1;
        setSize(792, 612);
        setLocation(100,100);
        setContentPane(new JLabel(new ImageIcon(getClass().getResource("/imagenes/logo.png"))));
        tmrTiempo=new Timer(2000, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                getPadre().setVisible(true);
                getBack().setVisible(true);
                tmrTiempo.stop();
                getMe().dispose();
        }});
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        tmrTiempo.start();
        setLocationRelativeTo(null);
        setUndecorated(true);
        setVisible(true);
    }
    public Splash getMe()
    {
        return this;
    }
    public Interfaz getPadre()
    {
        return padre;
    }
    public peticion getBack()
    {
        return back;
    }
}