/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpserver;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class TCPServer {
    
    protected String host;
    
    protected int port;
    
    protected int backlog = 10;
    
    private ServerSocket socket = null;
    
    public TCPServer() {
        this.host = "localhost";
        this.port = 8888;
    }
           
    public TCPServer(String host, int port) {
        this.host = host;
        this.port = port;
    }
    
    public TCPServer(int port) {
        this();
        this.port = port;
    }
    
    public TCPServer(String host) {
        this();
        this.host = host;
    }
   
    public ServerSocket getSocket() throws Exception {
        if (this.socket == null) {
            this.socket = new ServerSocket(this.port, this.backlog, InetAddress.getByName(this.host));// new TCPServerSocket(this.port, this.host, this.backlog);
        }
       
        return this.socket;
    }
    
    public void listen(Class TcpRunner) throws Exception {
        while(true) {
            
            Socket clientSock = this.getSocket().accept();
            
            TCPServerRunner runner = (TCPServerRunner) TcpRunner.newInstance();
            
            runner.setSocket(new TCPServerSocket(clientSock));
            
            Thread th = new Thread(runner);
            th.start();
        }
    }
    
    public void debug() {
        System.out.println("TCPServer\n\thost: "+this.host+"\n\tport: "+this.port);
        
        try {
            System.out.println("\tinet addr: "+InetAddress.getByName(this.host));
        } catch (UnknownHostException ex) {
            System.out.println("Unable to resolve "+this.host);
        }
    }
    
}
