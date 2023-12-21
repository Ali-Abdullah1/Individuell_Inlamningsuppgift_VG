import java.io.File;
import java.io.IOException;

public class BankFacade {

    private ContactsFromFile contactsFromFile;
    public Banksystem banksystem;

    private UserInterface ui;



    public BankFacade(){
        contactsFromFile = new ContactsFromFile();
        banksystem = Banksystem.getInstance();
        //ui = new UserInterface();

    }
    public boolean login(String personnummer, String customerName) {
        return banksystem.login(personnummer, customerName);

    }

    public void save(String personNumber, String customerName){
        banksystem.saveMember(personNumber, customerName);
    }


    public void Contacts(){
        contactsFromFile.getContacts();
    }

    public void deposit(double amount){
        banksystem.deposit(amount);
    }

    public void withdraw(double amount){
         banksystem.withdraw(amount);
    }

    public void showBalance(){
        banksystem.showBalance();
    }


    public void blockCard(){
        banksystem.blockAccount();
    }

    public void pay(double amount){
        banksystem.pay(amount);
    }

    public void loan(double amount){
        banksystem.loan(amount);
    }

    public void activateOnlinePurchases() throws InterruptedException {
        banksystem.activateOnlinePurchases();
    }
    public void activateSwish(){
        banksystem.activateSwish();
    }
    public boolean isHasSwish(){
        return banksystem.isHasSwish();
    }
    public boolean userChoice() throws InterruptedException {
        //return ui.userChoice();
        return true;
    }

    public void CurrencyConversion() throws InterruptedException {
        //ui.currencyConversion();
    }
    public void storeAccounts(){
        File file = new File("src/Customer.ser");
        try {
            IO.serializeToFile(file,banksystem.getCustomers());
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

