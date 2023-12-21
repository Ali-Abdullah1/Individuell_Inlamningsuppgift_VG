import javax.swing.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface extends ContactsFromFile {
    private Scanner scanner;
    private BankFacade bf;

    public UserInterface() {
        scanner = new Scanner(System.in);
        bf = new BankFacade();
    }
    public void run() throws InterruptedException {
        int choice;
        while (true){
            System.out.println("1. Logga in \n2. Skapa nytt konto \n3. Avsluta");
            choice = scanner.nextInt();
            scanner.nextLine();
            String personnummer;
            String name;
            switch (choice){
                case 1:

                    System.out.println("Ange personnummer: ");
                    personnummer = scanner.nextLine();
                    System.out.println("Ange namn: ");
                    name = scanner.nextLine();
                    if(bf.login(personnummer, name)){
                        System.out.println("Logged in.");
                        userChoice();
                    }
                    else System.out.println("Failed to login");
                    break;

                case 2:

                    System.out.println("Ange personnummer: ");
                    personnummer = scanner.nextLine();
                    System.out.println("Ange namn: ");
                    name = scanner.nextLine();
                    bf.save(personnummer, name);
                    bf.login(personnummer, name);
                    userChoice();
                    break;

                case 3:
                    System.exit(0);

            }

        }
    }

    public void showMenu() {
        System.out.println("1. Insättning\n" +
                "2. Uttag\n" +
                "3. Se saldo\n" +
                "4. Gör betalning\n" +
                "5. Hantera lån\n" +
                "6. Spärra kort\n" +
                "7. Aktivera online-köp\n" +
                "8. Kontakta oss\n" +
                "9. Valuta\n" +
                "10. Aktivera Swish\n" +
                "11. Logga ut\n" +
                "12. Stäng");
    }

    private void userChoice() throws InterruptedException {
        int userChoice = 0;
        while (userChoice != 11) { // logga ut
            showMenu();
           userChoice = getUserChoice();

            switch (userChoice) {
                case 1 -> {
                    depositOption();
                    Thread.sleep(1500);
                }
                case 2 -> {
                    bf.showBalance();
                    System.out.print("Skriv in belopp att ta ut: ");
                    double withdrawAmount = scanner.nextDouble();
                    scanner.nextLine();
                    bf.withdraw(withdrawAmount);
                }
                case 3 -> bf.showBalance();
                case 4 -> payment();
                case 5 -> handleLoan();
                case 6 -> suspendCard();
                case 7 -> bf.activateOnlinePurchases();
                case 8 -> contactUs();
                case 9 -> currencyConversion();
                case 10 -> {
                    if (bf.isHasSwish()) System.out.println("Redan aktiverat swish");
                    else bf.activateSwish();
                    Thread.sleep(1500);
                    }
                case 11 -> bf.storeAccounts(); //Logga ut
                case 12 ->{
                    System.out.println("Exit application");
                    bf.storeAccounts();
                    System.exit(0);
                }
            }
        }
    }
    private void depositOption(){
        bf.showBalance();
        System.out.print("Skriv belopp att sätta in: ");
        double depositAmount = scanner.nextDouble();
        scanner.nextLine();
        bf.deposit(depositAmount);
    }

    private int getUserChoice() {
        try {
            System.out.print("Välj en handling: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            return choice;
        } catch (InputMismatchException e) {
            System.out.println("Ogiltig inmatning. Vänligen ange ett heltal.");
            scanner.nextLine();
            return -1;
        }
    }

    private void contactUs() {
        getContacts();
    }

    private boolean logOut() throws InterruptedException {
        System.out.println("Du är utloggad");
        Thread.sleep(1000);
        System.out.println("Välkommen åter");
        return true;
    }

    private void handleLoan() throws InterruptedException {
        System.out.println("Välj typ av lån\n" +
                "1. Bolån\n" +
                "2. privatlån");
        String typeOfLoan = scanner.nextLine();
        if (typeOfLoan.equals("1")) {
            System.out.println("Hur mycket vill du låna för bolån");
            double loanAmount = scanner.nextDouble();
            if (loanAmount <= 0) {
                System.out.println("Ogiltigt belopp");
            } else {
                scanner.nextLine();
                bf.loan(loanAmount);
                Thread.sleep(1500);
            }
        } else if (typeOfLoan.equals("2")) {
            System.out.println("Hur mycket vill du låna för privatlån");
            double loanAmount = scanner.nextDouble();
            if (loanAmount <= 0) {
                System.out.println("Ogiltigt belopp");
            } else {
                bf.loan(loanAmount);
                Thread.sleep(1500);
            }
        }

    }

    private void suspendCard() throws InterruptedException {
        System.out.println("1. Spärra kort\n2. Avbryt");
        String choice = scanner.nextLine();
        if (choice.equals("1")) {
            bf.blockCard();
        } else {
            System.out.println("Du avbröt");
        }
        Thread.sleep(1500);
    }


    private void payment() throws InterruptedException {
        System.out.print("OCR/Kommentar: ");
        String ocrKommentar = scanner.nextLine();
        System.out.print("Summa: ");
        double paymentAmount = scanner.nextDouble();
        scanner.nextLine();
        bf.pay(paymentAmount);
        Thread.sleep(1500);
    }
    private void currencyConversion() throws InterruptedException {
        System.out.println("Välj valuta\n" +
                "1. Dollar\n" +
                "2. Euro");
        String typeOfCurrency = scanner.nextLine();
        System.out.println("Summa:");
        double amount = scanner.nextDouble();

        if (amount < 0) {
            System.out.println("Ogiltigt belopp. Beloppet måste vara positivt.");
            return;
        }

        double dollarConversionRate = 10.86;
        double euroConversionRate = 11.68;

        if (typeOfCurrency.equals("1")) {
            double amountConversion = amount * dollarConversionRate;
            System.out.println(amount + "kr i dollar blir " + amountConversion + " dollar");
            Thread.sleep(1500);
        } else if (typeOfCurrency.equals("2")) {
            double amountConversion = amount * euroConversionRate;
            System.out.println(amount + "kr i euro blir " + amountConversion + " euro");
            Thread.sleep(1500);
        } else {
            System.out.println("Ogiltig valuta. Vänligen välj 1 för Dollar eller 2 för Euro.");
        }
    }
}
