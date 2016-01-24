package tool.Models;

/**
 * Author: Phillipa Russell
 * Student Number: 0900772r
 * Creation: 14/10/2015.
 */
//class to hold prime numbers
public class Prime_Num {
    String number;
    Boolean prime;

    //takes in a number and sets the boolean to see if it is prime or not
    public Prime_Num(int n) {
        this.number=Integer.toString(n);
        prime=isPrime(n);

    }

    //checks to see if a number is prime or not
    public boolean isPrime(int n){
        for (int i =2;i<n;i++){
            if(n%i==0){
                return false;
            }
        }
        return true;
    }

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
