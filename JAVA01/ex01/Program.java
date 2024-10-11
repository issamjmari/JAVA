package ex01;

class Program {
    public static void main(String[] args) {
        UserIdsGenerator generator = UserIdsGenerator.getInstance();

        int firstId = generator.generateId();
        int secondId = generator.generateId();

        System.out.println("First generated ID: " + firstId);
        System.out.println("Second generated ID: " + secondId);
        generator.toStr();
    
        User mark = new User("mark", 300);
        System.out.println("mark id is: " + mark.getId());
        User sara = new User("sara", 200);
        System.out.println("sara id is: " + sara.getId());
        User karim = new User("karim", 800);
        System.out.println("karim id is: " + karim.getId());
    }
}