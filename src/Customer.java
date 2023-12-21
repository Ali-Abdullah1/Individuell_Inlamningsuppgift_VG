import java.io.Serializable;

public class Customer implements Serializable {

    private String namn;
    private String personnummer;
    private BankAccount account;




    public Customer(String namn, String personnummer, BankAccount account){
        this.namn = namn;
        this.personnummer = personnummer;
        this.account = account;
    }
    public BankAccount getAccount(){
        return account;
    }
    public String getNamn() {
        return namn;
    }



    public String getPersonnummer() {
        return personnummer;
    }

    public void setPersonnummer(String personnummer) {
        this.personnummer = personnummer;
    }


    @Override
    public String toString() {
        return "Person{" +
                "namn='" + namn + '\'' +
                ", personnummer=" + personnummer +
                '}';
    }

}
