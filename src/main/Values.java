/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Hashtable;

/**
 *
 * @author Dalib
 */
public class Values {
    
    Hashtable<String, String> table = new Hashtable<String, String>();
    
    public void addValue(String key, String val) {
        this.table.put(key, val);
    }
    
    public boolean hasKey(String key) {
        return this.table.containsKey(key);
    }
    
    public void clear() {
        this.table.clear();
    }
    
    public void debug() {
        for(String key: this.table.keySet()){
            System.out.println(" [ "+key+" ] = "+this.table.get(key));
        }
    }
}
