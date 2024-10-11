package ex05;

class Program {
    public static void main(String[] args) {
        
        if(args[0].equals("--profile=dev")) {
            new Menu(true);
        }
        else if(args[0].equals("--profile=prod")) {
            new Menu(false);
        }
        else {
            System.out.println("option not applicable");
        }
    }
}