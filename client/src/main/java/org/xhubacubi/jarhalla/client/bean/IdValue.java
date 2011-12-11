/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package org.xhubacubi.jarhalla.client.bean;

/**
 *Bean para manera duplas de manera sencilla
 * @author rugi
 */
public class IdValue {
    /*
     * 
     */
    private byte id;
    /*
     * 
     */
    private String value;
    
    /*
     * 
     */
    public IdValue(byte id, String value){
        super();
        this.id    = id;
        this.value = value;        
    }
    
    @Override
    /*
     * 
     */
    public String toString(){
        return this.value;
    }
    
}
