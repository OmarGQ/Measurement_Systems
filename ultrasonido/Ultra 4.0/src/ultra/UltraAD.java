/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultra;

import com.fazecast.jSerialComm.SerialPort;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Usuario
 */
public class UltraAD {
    public static SerialPort conn;
    static InputStream in;
    JFrame jf;
    public UltraAD(){
        jf = new JFrame();
        conn = SerialPort.getCommPorts()[0];
        conn.openPort();
        conn.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 100, 0);
        in = conn.getInputStream();
        
    }
}
