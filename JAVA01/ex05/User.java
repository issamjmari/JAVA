package ex05;


public class User {

    private Integer Id;

    private String Name;

    private Integer Balance;

    private TransactionsList transactionsList;

    User(String Name, int initBal) {
        if(initBal < 0) {
            System.out.println("Can't commit transaction for user " + Name);
            return;
        }
        this.Id = UserIdsGenerator.getInstance().generateId();
        this.Balance = initBal;
        this.Name = Name;
        transactionsList = new TransactionsLinkedList();
    }

    public int getId() {
        return Id;
    }
    public void setBalance(int balance) {
        System.out.println(Name + " has " + Balance);
        System.out.println(balance + " will be added");
        this.Balance = balance;
    }
    public int getBalance() {
        return this.Balance;
    }
    public void modifyBalance(int amount) {
        if(this.Balance + amount < 0)
            throw new IllegalTransactionException("illegal transaction");
        this.Balance += amount;
    }
    public String getName() {
        return this.Name;
    }
    public void setName(String name) {
        this.Name = name;
    }

    public TransactionsList getTransactionsList() {
        return transactionsList;
    }
    public void toStr() {
        System.out.println((
            "name " + Name + "\n"
            + "Balance " + Balance + "\n"
        ));
    }
}
