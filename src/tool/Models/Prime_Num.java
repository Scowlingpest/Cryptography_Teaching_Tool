package tool.Models;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 14/10/2015.
 */
//Prime_Num object, class to hold prime numbers
public class Prime_Num {
    private String number;
    private Boolean prime;

    /*Prime_Num constructor, takes in a number and sets the boolean to see if it is prime or not
    parameters : n- number to store and check
    returns : null
     */
    public Prime_Num(int n) {
        this.number=Integer.toString(n);
        prime=isPrime(n);

    }

    /*isPrime,checks to see if a number is prime or not
    parameter : n- number to check
    returns: boolean, true if n is prime
     */
    private boolean isPrime(int n){
        for (int i =2;i<n;i++){
            if(n%i==0){
                return false;
            }
        }
        return true;
    }

    //getters and setters
    public String getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = Integer.toString(number);
        prime=isPrime(number);
    }

    public Boolean getPrime() {
        return prime;
    }
}
