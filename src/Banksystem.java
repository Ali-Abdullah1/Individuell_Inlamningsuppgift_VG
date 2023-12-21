import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
public class Banksystem {

    private static Banksystem instance;
    private static File memberFile = new File("src/Customer.ser");
    private final String logInBirth = "Personnummer: ";
    private final String logInName = "Namn: ";
    private List<Customer> customers;
    private Customer currentCustomer;
    private Banksystem(){
        initBankSystem();
    }


    public static Banksystem getInstance(){
        if(instance == null){
            instance = new Banksystem();
        }
        return instance;
    }
    private void initBankSystem() {
        try {
            customers = new ArrayList<>();
            customers.addAll(IO.deserializeFromFile(memberFile));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public String getLogInBirth() {
        return logInBirth;
    }

    public String getLogInName() {
        return logInName;
    }

    private boolean personnummerExists(String personnummer){
        for(Customer customer : customers){
            if(customer.getPersonnummer().equals(personnummer)) return true;
        }
        return false;
    }
    public void saveMember(String personNumber, String customerName){

            if(personnummerExists(personNumber)){
                System.out.println("Personnumret existerar redan.");
            }
            else{
                BankAccount account = new BankAccount();
                Customer customer = new Customer(customerName, personNumber, account);
                customers.add(customer);
            }


    }
    public void activateOnlinePurchases() throws InterruptedException {
        System.out.println("Online köp är aktiverat");
        Thread.sleep(1000);
    }

    public void pay(double amount){
        currentCustomer.getAccount().pay(amount);
    }
    public void withdraw(double amount){
        currentCustomer.getAccount().withdraw(amount);
    }
    public void blockAccount(){
       currentCustomer.getAccount().blockAccount();
    }


    public void loan(double amount){
        currentCustomer.getAccount().loan(amount);
    }
    public void deposit(double amount){
        currentCustomer.getAccount().deposit(amount);
    }
    public void showBalance(){
        currentCustomer.getAccount().showBalance();
    }
    public void activateSwish(){
        currentCustomer.getAccount().activateSwish();
    }
    public boolean isHasSwish(){
        return currentCustomer.getAccount().isHasSwish();
    }

    public boolean login(String personNumber, String customerName) {
        for(Customer customer : customers){
            if(
                    customer.getPersonnummer().equals(personNumber) &&
                    customer.getNamn().equals(customerName)){
                currentCustomer = customer;
                return true;
            }
        }
        return false;
    }
    public List<Customer> getCustomers(){
       return customers;
    }
    public Customer getCurrentCustomer(){
        return currentCustomer;
    }

}

