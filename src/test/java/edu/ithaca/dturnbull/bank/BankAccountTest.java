package edu.ithaca.dturnbull.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        assertEquals(200, bankAccount.getBalance(), 0.001);

        BankAccount bankAccount2 = new BankAccount("a@b.com", 0);
        assertEquals(0, bankAccount2.getBalance(), 0.001); //test for 0 balance
        BankAccount bankAccount3 = new BankAccount("a@b.com", 100.57);
        assertEquals(100.57, bankAccount3.getBalance(), 0.001); //tests w decimal
        BankAccount bankAccount4 = new BankAccount("a@b.com", -200);
        assertEquals(200, bankAccount.getBalance(), 0.001); //tests w negative value
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        /**
         * 
         */
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        bankAccount.withdraw(0); // test for withdrawing 0
        assertEquals(200, bankAccount.getBalance(), 0.001);
        bankAccount.withdraw(100); // test for proper withdrawl
        assertEquals(100, bankAccount.getBalance(), 0.001);
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300)); //withdraw too much - boarder case
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-100)); // negative amount - boarder case
       
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));   // valid email address

        assertFalse( BankAccount.isEmailValid(""));         // empty string - boarder case
        assertFalse( BankAccount.isEmailValid("a@bc")); // invalid email - missing .com/etc. - boarder case
        assertFalse(BankAccount.isEmailValid("@b.com")); //invalid email - missing prefix - boarder case

        assertFalse( BankAccount.isEmailValid("a-@b.com"));    // invalid email address with dash at end in prefix   
        assertFalse( BankAccount.isEmailValid(".a@b.com"));     // invalid email address with period starting the prefix
        assertFalse( BankAccount.isEmailValid("a#a@b.com"));    // invalid email address with hash symbol in the prefix
        assertTrue( BankAccount.isEmailValid("a_b@c.com"));     //valid email - proper use of special char
        //dbl char
    }


    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance(), 0.001);
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}