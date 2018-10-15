/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package echoclient;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bsc
 */
public class Person {

  
    private String name;
    private int age = 0;
    private int lengthOfName = 0;
    private byte [] serailizedName;
    private byte [] ageBytes;
    private byte [] nameLengthBytes;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        
        this.tryToSerializeName();
        this.lengthOfName = serailizedName.length;
        ageBytes = new byte[4];
        nameLengthBytes = new byte[4];
        
        
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
 
   
    public byte[] getSerializedName(){
        return this.serailizedName;
    }
    
    public byte[] serializeAge(){

        ageBytes = new byte[4];
        ageBytes[0] = (byte) ((byte) age & 0xFF);
        ageBytes[1] = (byte) ((byte) (age >> 8)  & 0xFF);
        ageBytes[2] = (byte) ((byte) (age >> 16) & 0xFF);
        ageBytes[3] = (byte) ((byte) (age >> 24)  & 0xFF);


        return ageBytes;
    }
    
    
    public byte[] serializeNameLength(){

      
        nameLengthBytes[0] = (byte) ((byte) lengthOfName & 0xFF);
        nameLengthBytes[1] = (byte) ((byte) (lengthOfName >> 8)  & 0xFF);
        nameLengthBytes[2] = (byte) ((byte) (lengthOfName >> 16) & 0xFF);
        nameLengthBytes[3] = (byte) ((byte) (lengthOfName >> 24)  & 0xFF);


        return nameLengthBytes;
    }
    
    
    private byte [] tryToSerializeName(){
        try {
            return serializeName();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return null;
    }
    
    private byte[] serializeName() throws UnsupportedEncodingException{
        this.serailizedName = name.getBytes("UTF-8");
        return this.serailizedName;
    }
    
    public byte[] serializePerson(){
        
        byte [] packets = new byte[ageBytes.length+nameLengthBytes.length+serailizedName.length];
        System.arraycopy(this.serializeAge(), 0, packets, 0, ageBytes.length);
        System.arraycopy(this.serializeNameLength(), 0, packets, 4, nameLengthBytes.length);
        System.arraycopy(tryToSerializeName(), 0, packets, 8, serailizedName.length);
        return packets;
    }

}
