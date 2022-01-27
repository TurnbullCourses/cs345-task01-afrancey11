package edu.ithaca.dturnbull.bank;

// Java program to check if an email address
// is valid using Regex.
import java.util.regex.Pattern;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if (amount <= balance){
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }


    public static boolean isEmailValid(String email){
        // String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ "[a-zA-Z0-9_+&*-]+)*@" + 
        // "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
        // Pattern pat = Pattern.compile(emailRegex);

        // return pat.matcher(email).matches();

        //check for special characters
        for(int i = 0; i < email.length(); i++){
            if (email.charAt(i) == '#' || email.charAt(i) == '!' || email.charAt(i) == '&' || email.charAt(i) == '$' || email.charAt(i) == '%' || email.charAt(i) == '^' || email.charAt(i) == '*' || email.charAt(i) == '-' ){
                return false;
            }
        }

        //check if first character is a number
        if(email.indexOf("0") == 0 || email.indexOf("1") == 0 || email.indexOf("2") == 0 || email.indexOf("3") == 0 || email.indexOf("4") == 0 || email.indexOf("5") == 0 || email.indexOf("6") == 0 || email.indexOf("7") == 0 || email.indexOf("8") == 0 || email.indexOf("9") == 0){
            return false;
        } //prob an easier way to do this ^

        //@ or . first
        if(email.indexOf(".") == 0 || email.indexOf("@") == 0){
            return false;
        } 

        //check for @ and .
        if (email.indexOf('@') == -1 || email.indexOf(".") == -1){
            return false;
        }
        
        //check if empty
        if (email.isEmpty() == true){
            return false;
        }
        
        else{
            return true;
        }
    }
}