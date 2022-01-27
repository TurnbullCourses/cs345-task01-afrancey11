package edu.ithaca.dturnbull.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance(), 0.001);
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance(), 0.001);
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));   // valid email address
        assertFalse( BankAccount.isEmailValid(""));         // empty string - boarder case

        assertFalse( BankAccount.isEmailValid("a-@b.com"));    // invalid email address with dash in prefix   
        assertFalse( BankAccount.isEmailValid(".a@b.com"));     // invalid email address with period starting the prefix
        assertFalse( BankAccount.isEmailValid("a#a@b.com"));    // invalid email address with hash symbol in the prefix
        assertTrue( BankAccount.isEmailValid("a_b@c.com"));     //valid email - proper use of special char
        assertTrue( BankAccount.isEmailValid("a@bc")); // invalid email - missing .com/etc. - boarder case
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