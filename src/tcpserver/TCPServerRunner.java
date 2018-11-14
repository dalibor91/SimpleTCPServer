/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpserver;

import java.io.IOException;

/**
 *
 * @author Dalib
 */
public class TCPServerRunner implements Runnable {

    private TCPServerSocket socket;
    
    public void setSocket(TCPServerSocket s) {
        socket = s;
    }
    
    public TCPServerSocket getSocket() {
        return this.socket;
    }
    
    @Override
    public void run() {
        
        try {
            
            this.socket.sendWithEnd("Hello there!");
            
            while (true) {
                String k = this.getSocket().readTrim();

                if (k.equalsIgnoreCase("close") || k.equalsIgnoreCase("exit") || k.equalsIgnoreCase("quit")) {
                    this.getSocket().sendWithEnd("bye!");
                    break;
                }
                
                this.getSocket().sendWithEnd("You wrote: '"+ k+"'");
            }
            
            this.getSocket().close();
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
    }
}
