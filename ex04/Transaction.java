package ex04;

import java.util.UUID;


public class Transaction {
    
    private UUID id;

    private User recipient;

    private User sender; 

    private String transferType; 

    private Integer amount; 

    Transaction(User recipient, User sender, String transferType, int amount) {
        if (transferType.equals("OUTCOME") && amount <= 0) {
            this.amount = amount;
            recipient.modifyBalance(amount);
        }
        else if (transferType.equals("INCOME") && amount >= 0) {
            this.amount = amount;
            recipient.modifyBalance(amount);
        }
        else {
            System.out.println("Transaction invalid");
            return ;
        }
        this.recipient = recipient;
        this.sender = sender;
        this.transferType = transferType;
        this.id = UUID.randomUUID();
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public UUID getId() {
        return id;
    }
    public void toStr() {
        System.out.println((
            sender.getName() + " -> " 
            + recipient.getName() + ", " + amount + ", " + transferType + ", " + "transaction " + id + "\n"
        ));
    }
}

// Mark -300DH -> +300 JOHN 
