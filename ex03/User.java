package ex03;


public class User {

    private Integer Id;

    private String Name;

    private Integer Balance;

    User(String Name, int initBal) {
        if(initBal < 0) {
            System.out.println("Can't commit transaction for user " + Name);
            return;
        }
        this.Id = UserIdsGenerator.getInstance().generateId();
        this.Balance = initBal;
        this.Name = Name;
    }

    public int getId() {
        return Id;
    }
    public void setBalance(int balance) {
        this.Balance = balance;
    }
    public void modifyBalance(int amount) {
        this.Balance += amount;
    }
    public String getName() {
        return this.Name;
    }
    public void setName(String name) {
        this.Name = name;
    }
    public void toStr() {
        System.out.println((
            "name " + Name + "\n"
            + "Balance " + Balance + "\n"
        ));
    }
}
