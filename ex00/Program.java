package ex00;
class Program {
    public static void main(String[] args) {
        User mark = new User("mark", 500);
        User john = new User("john", 300);
        System.out.println("---------------------------------------------\n");
        System.out.println("Mark and John before: ");
        mark.toStr();
        john.toStr();
        System.out.println("---------------------------------------------\n");
        Transaction income = new Transaction(mark, john, "INCOME", 300);
        Transaction outcome = new Transaction(john, mark, "OUTCOME", -300);

        System.out.println("Income transaction\n");
        income.toStr();
        System.out.println("Outcome transaction\n");
        outcome.toStr();
        System.out.println("---------------------------------------------\n");
        System.out.println("Mark and John AFTER: ");
        mark.toStr();
        john.toStr();
    }
}