package tool.Models;

/**
 * Created by Phillipa on 14/10/2015.
 */
public class Prime_Num {
    String number;
    Boolean prime;

    public Prime_Num(int n) {
        this.number=Integer.toString(n);
        prime=isPrime(n);

    }

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
