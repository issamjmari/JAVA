package ex04;

class Program {
    public static void main(String[] args) {
        TransactionsService transactionsService = new TransactionsService();
        User mark = new User("mark", 1000);
        User john = new User("john", 800);

        transactionsService.addUser(john);
        transactionsService.addUser(mark);

        System.out.print("john balance: ");
        int johnBalance = transactionsService.getUserBalance(john);
        System.out.println(johnBalance);

        System.out.println("performing transaction between john and mark. . .");
        transactionsService.performTransaction(john.getId(), mark.getId(), 300);
        System.out.println("Finished.");

        System.out.print("john balance: ");
        johnBalance = transactionsService.getUserBalance(john);
        System.out.println(johnBalance);

        System.out.print("mark balance: ");
        int markBalance = transactionsService.getUserBalance(mark);
        System.out.println(markBalance + "\n");

        System.out.print("mark transfers: ");
        Transaction[] markTransactions = transactionsService.getUserTransfers(mark.getId());
        for(Transaction transaction: markTransactions) {
            transaction.toStr();
        }

        System.out.print("john transfers: ");
        Transaction[] johnTransactions = transactionsService.getUserTransfers(john.getId());
        Transaction jTransaction = new Transaction();
        for(Transaction transaction: johnTransactions) {
            jTransaction = transaction;
            transaction.toStr();
        }

        System.out.println("transaction remove: ");
        transactionsService.removeTransaction(jTransaction.getId(), john.getId());
    
        johnTransactions = transactionsService.getUserTransfers(john.getId());
        System.out.print("john transfers: ");
        boolean isThereTransac = false;
        for(Transaction transaction: johnTransactions) {
            isThereTransac = true;
            jTransaction = transaction;
            transaction.toStr();
        }
        if(isThereTransac == false)
            System.out.print("EMPTY");
        System.out.println("\n");

        Transaction[] unpaired = transactionsService.getUnpairedTransactions();
        System.out.println("List of unpaired transactions: ");
        for(Transaction transaction: unpaired) {
            isThereTransac = true;
            jTransaction = transaction;
            transaction.toStr();
        }
    }
}