/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicionpresion;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Omar xv
 */
public class peticion extends JFrame{
    public JLabel tex, lmax, lmin, ntu, max, min ,set;
    
    public peticion(){
        super();
        
        tex=new JLabel("ingrese el valor deseado");
        tex.setLocation(50, 10);
        tex.setSize(400, 40);
        tex.setFont(new Font("Arial", Font.PLAIN, 35));
        lmax=new JLabel("1 NTU");
        lmax.setLocation(50, 100);
        lmax.setSize(100, 40);
        lmax.setFont(new Font("Arial", Font.PLAIN, 35));
        lmin=new JLabel("4 NTU");
        lmin.setLocation(350, 100);
        lmin.setSize(100, 40);
        lmin.setFont(new Font("Arial", Font.PLAIN, 35));
        ntu=new JLabel("NTU");
        ntu.setLocation(250, 100);
        ntu.setSize(70, 40);
        ntu.setFont(new Font("Arial", Font.PLAIN, 30));
        max=new JLabel("max");
        max.setLocation(350, 50);
        max.setSize(400, 40);
        max.setFont(new Font("Arial", Font.PLAIN, 35));
        min=new JLabel("min");
        min.setLocation(50, 50);
        min.setSize(400, 40);
        min.setFont(new Font("Arial", Font.PLAIN, 35));
        set=new JLabel("setpoint");
        set.setLocation(200, 50);
        set.setSize(400, 40);
        set.setFont(new Font("Arial", Font.PLAIN, 35));
        
        this.add(tex);
        this.add(lmax);
        this.add(lmin);
        this.add(ntu);
        this.add(max);
        this.add(min);
        this.add(set);
        this.setSize(600, 400);
        setLayout(null);
        this.setAlwaysOnTop(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
    }
    
}
