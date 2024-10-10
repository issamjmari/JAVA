package ex05;
import java.util.Scanner;
import java.util.UUID;

import javax.sound.midi.SysexMessage;

public class Menu {
    private TransactionsService transactionsService = new TransactionsService();
    private boolean mode = false;
    Scanner scanner = new Scanner(System.in);

    Menu(boolean mode) {
        this.mode = mode;
        runMenu();
    }

    private void printOptions(String[] options) {
        for(String option: options) {
            System.out.println("  " + option);
        }
    }

    private String[] setOptions() {
        int itemNum = 0;
        String[] options = new String[this.mode == true ? 7 : 5];
        options[itemNum++] = itemNum + ". Add a user";
        options[itemNum++] = itemNum + ". View user balances";
        options[itemNum++] = itemNum + ". Perform a transfer";
        options[itemNum++] = itemNum + ". View all transactions for a specific user";
        if(this.mode == true) {
            options[itemNum++] = itemNum + ". DEV - remove a transfer by ID";
            options[itemNum++] = itemNum + ". DEV - check transfer validity";
        }
        options[itemNum++] = itemNum + ". Finish execution";
        return options;
    }

    private int checkInput(String input) {
        if(this.mode == true) {
            for(int i = 0; i < 7; i++) {
                if(input.equals((i + 1) + "") == true)
                    return i + 1;
            }
        }
        else {
            for(int i = 0; i < 5; i++) {
                if(input.equals((i + 1) + "") == true)
                    return i + 1;
            }
        }
        return 0;
    }

    private void addUser() {
        System.out.println("    Enter a user name and a balance");
        while(true) {
            System.out.print("=> ");
            String input = "";
            input = scanner.nextLine();
            String[] userData = input.split(" ");
            int number = 0;
            try {
                number = Integer.parseInt(userData[1]);
            }
            catch(Exception e) {
                System.out.println("Please enter a correct user and balance of the user");
                continue;
            }
            User user = new User(userData[0], number);
            int userId = this.transactionsService.addUser(user);
            System.out.println("User with id = " + userId + " is added");
            return ;
        }
    }

    private void viewUserBalance() {
        System.out.println("    Enter a user ID");
        while(true) {
            System.out.print("=> ");
            String input = "";
            input = scanner.nextLine();
            int number = 0;
            try {
                number = Integer.parseInt(input);
            }
            catch(Exception e) {
                System.out.println("Please enter a correct userId");
                continue;
            }
            User user = this.transactionsService.getUserById(number);
            int userBalance = this.transactionsService.getUserBalance(user);
            System.out.println(user.getName() + " - " + userBalance);
            return ;
        }
    }

    private void getUserTransactions() {
        System.out.println("    Enter a user ID");
        while(true) {
            System.out.print("=> ");
            String input = "";
            input = scanner.nextLine();
            int userId = 0;
            try {
                userId = Integer.parseInt(input);
            }
            catch(Exception e) {
                System.out.println("Please enter a correct userId");
                continue;
            }
            User user = this.transactionsService.getUserById(userId);
            Transaction[] transactions = this.transactionsService.getUserTransfers(userId);
            if(transactions.length == 0) {
                System.out.println("User doesn't have any transaction");
            }
            for(Transaction transaction: transactions) {
                System.out.print("To " + user.getName() + "(" + "id = " + user.getId() + ")");
                System.out.println(" " + transaction.getAmount() + " with id = " + transaction.getId());
            }
            return ;
        }
    }
    private void removeTransaction() {
        System.out.println("    Enter a user ID and a transfer ID");
        while(true) {
            System.out.print("=> ");
            String input = "";
            input = scanner.nextLine();
            String[] transactionData = input.split(" ");
            int userId = 0;
            String transactionId = transactionData[1];
            int amount = 0;
            try {
                userId = Integer.parseInt(transactionData[0]);
            }
            catch(Exception e) {
                System.out.println("Please enter a correct transaction data");
                continue;
            }
            try {
                UUID trUuid = UUID.fromString(transactionId);
                amount = this.transactionsService.removeTransaction(trUuid, userId);
            }
            catch (Exception e) {
                System.out.println("User can't remove transaction");
                continue;
            }
            User user = this.transactionsService.getUserById(userId);
            System.out.println("Transfer To " + user.getName() + "(id = " + user.getId() + ") " + amount + " removed");
            return ;
        }
    }

    void    printLackingTransaction(User lacked, User lacking, UUID id, int amount) {
        System.out.print(lacking.getName() + "(id = " + lacking.getId() + ") ");
        System.out.println("has an unacknowledged transfer id = " + id + " ");
        System.out.println("from " + lacked.getName() + "(id = " + lacked.getId() + ") for " + amount);
    }
    private void checkTransactionValidity() {
            try {
                Transaction[] unpaired = this.transactionsService.getUnpairedTransactions();
                System.out.println("    Check results:");
                for(Transaction transaction: unpaired) {
                    if(transaction.getLackingPart() == false)
                        printLackingTransaction(transaction.getRecipient(), transaction.getSender(), transaction.getId(), transaction.getAmount());
                    else
                        printLackingTransaction(transaction.getSender(), transaction.getRecipient(), transaction.getId(), transaction.getAmount());
                }
                return ;
            }
            catch(Exception e) {
                System.out.println("problem occured while checking validity, try again please");   
                return;
            }
    }
    private void performTransaction() {
        System.out.println("    Enter a sender ID, a recipient ID, and a transfer amount");
        while(true) {
            System.out.print("=> ");
            String input = "";
            input = scanner.nextLine();
            String[] transactionData = input.split(" ");
            int userId1 = 0;
            int userId2 = 0;
            int amount = 0;
            try {
                userId1 = Integer.parseInt(transactionData[0]);
                userId2 = Integer.parseInt(transactionData[1]);
                amount = Integer.parseInt(transactionData[2]);
            }
            catch(Exception e) {
                System.out.println("Please enter a correct transaction data");
                continue;
            }
            try {
                this.transactionsService.performTransaction(userId2, userId1, amount);
            }
            catch (Exception e) {
                System.out.println("User doesn't have balance to perform transaction");
                continue;
            }
            System.out.println("The transfer is completed");
            return ;
        }
    }
    private void handleDev(int option) {
        if(option == 1)
            addUser();
        if(option == 2)
            viewUserBalance();
        if(option == 3)
            performTransaction();
        if(option == 4)
            getUserTransactions();
        if(option == 5)
            removeTransaction();
        if(option == 6)
            checkTransactionValidity();
    }
    private void handleProd(int option) {
        if(option == 1)
            addUser();
        if(option == 2)
            viewUserBalance();
        if(option == 3)
            performTransaction();
        if(option == 4)
            getUserTransactions();
    }

    private void runMenu() {
        String[] options = setOptions();
        String input = "";
        while(true) {
            printOptions(options);
            System.out.print("=> ");
            input = scanner.nextLine();
            int option = checkInput(input);
            if(option == 0) {
                System.out.println("input invalid, please enter one of the displayed options");
                continue ;
            }
            else {
                if(this.mode == true) {
                    if(option == 7)
                        return;
                    handleDev(option);
                }
                else {
                    if(option == 5)
                        return;
                    handleProd(option);
                }
            }
            System.out.println("---------------------------------------------------------");
        }
    }


}