/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpserver;

import java.net.ServerSocket;

/**
 *
 * @author Dalib
 */
public class TCPServerThread extends Thread {
    
    ServerSocket socket;
    
    Thread th;
    
    public TCPServerThread(ServerSocket s, TCPServerRunner runner) {
        super(runner);
        this.socket = s;
    }
    
    public TCPServerThread(ServerSocket s, TCPServerRunner runner, String name) {
        super(runner, name);
        this.socket = s;
    }
}
