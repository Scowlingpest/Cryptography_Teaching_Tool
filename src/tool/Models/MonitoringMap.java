package tool.Models;

import java.util.HashMap;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 04/01/2016.
 */
public class MonitoringMap {
    HashMap<String,Integer> monitoring;
    String[] headings = new String[]{"RSA1","RSA2","RSA3","RSA4"
            ,"DH1","DH2","EG1","EG2",
            "AES1","AES2","AES3","AES4",
            "PN","GR","IM","AS",
            "ED","SB","VC","DT"};

    public MonitoringMap(){
        monitoring = new HashMap<>();
        for(String h:headings){
            monitoring.put(h,0);
        }


    }

    public void incrementValue(String k){
        monitoring.put(k,monitoring.get(k)+1);
    }


    public String lineGenerate(){
        String temp ="";
        for(String h:headings){
            temp+=monitoring.get(h)+",";
        }
        return (temp.substring(0, temp.length()-1));
    }
}

