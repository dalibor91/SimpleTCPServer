/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tcpserver.TCPServerRunner;

/**
 *
 * @author Dalib
 */
public class MyRunner extends TCPServerRunner {
    
    private void ok(String msg) throws IOException {
        this.getSocket().sendWithEnd("DONE: "+msg);
    }
    
    private void fail(String msg) throws IOException {
        this.getSocket().sendWithEnd("FAIL: "+msg);
    }
    
    public void run() {
        
        Values table = new Values();
        
        try {
            ok("connected");

            String key = null;
            String val = null;
            
            while (true) {
                String msg = this.getSocket().readTrim();
                
                if (msg.equalsIgnoreCase("!close") || msg.equalsIgnoreCase("!exit") || msg.equalsIgnoreCase("!quit")) {
                    this.getSocket().sendWithEnd("bye!");
                    break;
                }
                
                if (key == null) {  
                    key = msg;
                    ok("Key created!");
                } else {
                    val = msg;
                    table.addValue(key, val);
                    key = null;
                    ok("Value inserted for key");
                }
                
                //table.debug();
            }
            
            table.clear();
            this.getSocket().close();

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
