package tool.Models;

import java.util.HashMap;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 04/01/2016.
 */
//MonitoringMap object, used for monitoring
public class MonitoringMap {
    private HashMap<String,Integer> monitoring;

    //headings are in same order as CSV in main
    private String[] headings = new String[]{"RSA1","RSA2","RSA3","RSA4"
            ,"DH1","DH2","EG1","EG2",
            "AES1","AES2","AES3","AES4",
            "PN","GR","IM","AS",
            "ED","SB","VC","DT"};

    /*MonitoringMap constructor, makes a hashmap for recording monitoring
    parameters: null
    returns: null
     */
    public MonitoringMap(){
        monitoring = new HashMap<>();
        for(String h:headings){
            monitoring.put(h,0);
        }


    }

    /*incrementValue, increments the keys value by 1
    parameters: k-key of value to increment
    returns: null
     */
    public void incrementValue(String k){
        monitoring.put(k,monitoring.get(k)+1);
    }


    /*lineGenerate, transforms the monitoring hashmap into a string line for csv
    parameters: null
    returns: the csv line as a string
     */
    public String lineGenerate(){
        String temp ="";
        for(String h:headings){
            temp+=monitoring.get(h)+",";
        }
        return (temp.substring(0, temp.length()-1));
    }
}

