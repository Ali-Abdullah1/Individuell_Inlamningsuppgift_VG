import java.io.Serializable;

public class BankAccount extends ContactsFromFile implements Serializable {
    private double balance;
    private boolean hasSwish = false;


    public BankAccount(double initialSaldo) {
        this.balance = initialSaldo;
    }

    public boolean isHasSwish() {
        return hasSwish;
    }

    public BankAccount() {

    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "balance=" + balance +
                '}';
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println(amount + " kronor sattes in på ditt konto");
    }

    public void activateSwish() {
       hasSwish = true;
        System.out.println("Swish aktiverad.");
    }


    public void withdraw(double amount) {
        if (balance >= amount) {
            balance = (balance - amount);
            System.out.println(amount + " kronor togs ut från ditt konto");
        } else {
            System.out.println("Du har inte tillräckligt med pengar på kontot");
        }
    }

    public void showBalance() {
        System.out.println("Ditt saldo är: " + setTextYellow + balance + turnOffTextYellow);
    }

    public void pay(double amount) {
        if (balance >= amount) {
            balance = (balance - amount);
            System.out.println("Betalning på " + amount + "kr" + " registrerades");
        } else {
            System.out.println("Du har inte tillräckligt med pengar på kontot");
        }

    }

    public void loan(double amount) {
        balance += amount;
        System.out.println("Du fick lån på " + amount + " kr");
    }
    public void blockAccount(){
        System.out.println("Kontot är spärrat!");
    }



}
