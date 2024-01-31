import java.io.*;

/**
 * TestBankAccounts.java:
 *  This class will test aspects of inheritance for the BankAccount class
 *  and its subclasses.
 */

public class TestBankAccounts {
    
    public static void main(String[] args) {
      
        BankAccount bacc0 = new BankAccount(0);
        System.out.println(bacc0.toString());
        
        BankAccount bacc1 = new BankAccount(5000);
        System.out.println(bacc1.toString());
        
        CheckingAccount chacc1 = new CheckingAccount(500.0);
        System.out.println(chacc1.toString());
                          
        SavingsAccount sacc1 = new SavingsAccount(1000.0, 1.0);
        System.out.println(sacc1.toString()); 
        
        //-------------------------------------------------------
        
        // Testing deposit and withdraw on BankAccount
        bacc0.deposit(100);
        System.out.println("After deposit: " + bacc0.toString());
        bacc0.withdraw(50);
        System.out.println("After withdrawal: " + bacc0.toString());

        // Testing deposit and deductFees on CheckingAccount
        chacc1.deposit(200);
        System.out.println("CheckingAccount after deposit: " + chacc1.toString());
        chacc1.deductFees();
        System.out.println("CheckingAccount after deducting fees: " + chacc1.toString());

        // Testing calculateInterest on SavingsAccount
        sacc1.calculateInterest();
        System.out.println("SavingsAccount after interest calculation: " + sacc1.toString()); 
        
    }
    
}
