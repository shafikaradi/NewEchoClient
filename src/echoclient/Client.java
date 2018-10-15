/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package echoclient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 *
 * @author bsc
 */
public class Client {
    
    
    private SocketChannel client;
    private ByteBuffer buffer;
    private Person person;
    
    Client(Person person) {
        try {
            
            client = SocketChannel.open(new InetSocketAddress("localhost", 5000));
            
            this.person = person;
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  
    public void stop() throws IOException {
        client.close();
        buffer = null;
    }
 
 
    
    public Client sendRequest() throws UnsupportedEncodingException, IOException{
       
        
        for(byte bytes: person.serializePerson())
            System.out.println(bytes);
        
        buffer = ByteBuffer.wrap(person.serializePerson());
        System.out.println(buffer.limit());
       // buffer.flip();
        System.out.println(buffer.limit());
        client.write(buffer);
        buffer.clear();
   
        
        
        return this;
    }
 
   
}
