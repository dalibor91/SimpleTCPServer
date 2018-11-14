package main;

import tcpserver.TCPServer;

public class Main {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        //TCPServer s = ;
        //s.debug();
        new TCPServer().listen(MyRunner.class);
    }
}