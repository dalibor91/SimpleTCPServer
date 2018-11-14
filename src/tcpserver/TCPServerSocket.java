/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpserver;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author Dalib
 */
public class TCPServerSocket {

    private Socket socket;
    
    private int messageLimit = 1000000;
    
    private String endRecieve = "\n";
    
    private String endSend = "\r\n";
    
    TCPServerSocket(Socket s) {
        this.socket = s;
    }
    
    public void setMessageLimit(int limit) {
        this.messageLimit = limit;
    }
    
    public void setEndRecieved(String delim) {
        this.endRecieve = delim;
    }
    
    public void setEndSent(String delim) {
        this.endSend = delim;
    }
    
    public InputStream getInputStream() throws IOException {
        return this.socket.getInputStream();
    }
    
    public OutputStream getOutputStream() throws IOException {
        return this.socket.getOutputStream();
    }
    
    public void send(String message) throws IOException {
        this.getOutputStream().write(message.getBytes());
    }
    
    public void sendWithEnd(String message) throws IOException {
        this.send(message+this.endSend);
    }
    
    public String read() throws IOException {
        
        byte[] msg = new byte[1024];
        String readed = "";
        DataInputStream in = new DataInputStream(this.getInputStream());
 
        while (true) {
            int readedLen = in.read(msg);
            
            readed += new String(msg, 0, readedLen);
            
            //System.out.println("Length: "+readed.length());
            //System.out.println("Ends: "+readed.endsWith(this.end));
            
            if (readed.length() >= 1 && (readed.endsWith(this.endRecieve) || readed.length() >= this.messageLimit))
                break;
        }
        
        //System.out.println("Send message to runner");
        return readed;
    }
    
    public String readTrim() throws IOException {
        return this.read().trim();
    }
    
    public void close() throws IOException {
        this.socket.close();
    }
    
    public boolean isOpen() {
        return this.socket.isClosed();
    }
    
}
