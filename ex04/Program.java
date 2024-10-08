package ex04;

class Program {
    public static void main(String[] args) {
        User mark = new User("mark", 500);
        User john = new User("john", 300);

        Transaction income = new Transaction(mark, john, "INCOME", 300);
        Transaction outcome = new Transaction(john, mark, "OUTCOME", -300);
    
        TransactionsList markList = mark.getTransactionsList();
        markList.addTransaction(income);
        markList.addTransaction(outcome);
        TransactionsList johnList = john.getTransactionsList();
        johnList.addTransaction(income);
        johnList.addTransaction(outcome);

        Transaction[] markListArray = markList.transformIntoArray();

        System.out.println("-------------MARK---------------");
        for(Transaction transaction: markListArray) {
            System.out.println("******* mark transaction " + transaction.getId() + " *******");
            transaction.toStr();
        }
        System.out.println("//////////////////////////////////");
        Transaction[] johnListArray = johnList.transformIntoArray();

        System.out.println("--------------JOHN--------------");
        for(Transaction transaction: johnListArray) {
            System.out.println("******* john transaction " + transaction.getId() + " *******");
            transaction.toStr();
        }
        System.out.println("//////////////////////////////////");
        markList.removeTransactionById(markListArray[1].getId());
        johnList.removeTransactionById(johnListArray[0].getId());
        johnListArray = johnList.transformIntoArray();
        System.out.println("--------------JOHN AFTER DELETE--------------");
        for(Transaction transaction: johnListArray) {
            System.out.println("******* john transaction " + transaction.getId() + " *******");
            transaction.toStr();
        }
    }
}