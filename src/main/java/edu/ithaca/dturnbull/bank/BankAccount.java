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
        if(amountValid(startingBalance) == false){
            throw new IllegalArgumentException("invalid starting balance");
        }

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
        if(amountValid(amount) == false){
            throw new IllegalArgumentException("invalid withdrawl amount");
        }
        if (amount <= balance){
            balance -= amount;
        }
        if (amount > balance){
            throw new InsufficientFundsException("Not enough money");
        }
    }

    /**
     * @param amount
     * @throws IllegalArgumentException
     * @post checks if negative or has 2 decimal places
     */
    public static boolean amountValid(double amount){
        //check for negative
        if(amount < 0){
            return false;
        }
        
        //check for decimal place
        String amountString = Double.toString(amount);
        if(amountString.indexOf(".") < amountString.length() - 3){
            return false;
        }

        return true;
    }

    /**
     * @param amount
     * @param bank acc to transfer to
     * @post reduces balance of bank acc and puts into another if withdrawl from BA1 is possible and amount is valid
     */
    public void transfer(double amount, BankAccount toBankAccount) throws InsufficientFundsException{
        if(amountValid(amount) == false){
            throw new IllegalArgumentException();
        }
        
        if (amount > balance){
            throw new InsufficientFundsException("Not enough money");
        }

        else{
            balance-=amount;
            toBankAccount.balance += amount;
            //could also be written with withdraw/deposit methods
        }
    }

    /**
     * @param amount
     * @post adds money to acc - if amount is valid
     */
    public void deposit(double amount){
        if(amountValid(amount) == false){
            throw new IllegalArgumentException();
        }

        balance+=amount;
    }


    public static boolean isEmailValid(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ "[a-zA-Z0-9_+&*-]+)*@" + 
        "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);

        //check if empty
        if (email.isEmpty() == true){
            return false;
        }

        //@ or . first
        if(email.indexOf(".") == 0 || email.indexOf("@") == 0){
            return false;
        } 

        //checks for illegal char direactly before @
        int myChar = email.indexOf("@") - 1;
        char myChar2 = email.charAt(myChar);
        if(myChar2 == '-' || myChar2 == '.' || myChar2 == '_'){
            return false;
        }
        
        else{
            return pat.matcher(email).matches();
        }



        
        //OLD HARDCODING BEFORE REGEX ADDED FOR REFERENCE:

        // //check for special characters
        // for(int i = 0; i < email.length(); i++){
        //     if (email.charAt(i) == '#' || email.charAt(i) == '!' || email.charAt(i) == '&' || email.charAt(i) == '$' || email.charAt(i) == '%' || email.charAt(i) == '^' || email.charAt(i) == '*'){
        //         return false;
        //     }
        // }

        // //two @s
        // if(email.indexOf("@") != email.lastIndexOf("@")){
        //     return false;
        // }

        // //check if first character is a number
        // if(email.indexOf("0") == 0 || email.indexOf("1") == 0 || email.indexOf("2") == 0 || email.indexOf("3") == 0 || email.indexOf("4") == 0 || email.indexOf("5") == 0 || email.indexOf("6") == 0 || email.indexOf("7") == 0 || email.indexOf("8") == 0 || email.indexOf("9") == 0){
        //     return false;
        // } //prob an easier way to do this ^

        // //check for @ and .
        // if (email.indexOf('@') == -1 || email.indexOf(".") == -1){
        //     return false;
        // }
    }
}