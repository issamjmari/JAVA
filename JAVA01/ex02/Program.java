package ex02;

class Program {
    public static void main(String[] args) {
        UsersArrayList usersArrayList = new UsersArrayList();

        System.out.println("*******************************");
        for(int i = 0; i < 20; i++) {
            User user = new User("user" + i, i * 20);
            System.out.println("user " + user.getName());
            usersArrayList.addUser(user);
        }
        System.out.println("*******************************");
        System.out.println("Users with id 1 and 2:");
        User foundUser1 = usersArrayList.getUserById(1);
        foundUser1.toStr();
        User foundUser2 = usersArrayList.getUserById(2);
        foundUser2.toStr();
        System.out.println("*******************************");
        System.out.println("Users with index 7 and 15:");
        User foundUser7 = usersArrayList.getUserById(7);
        foundUser7.toStr();
        User foundUser15 = usersArrayList.getUserById(15);
        foundUser15.toStr();
        System.out.println("*******************************");

        System.out.println("Total number of users:");
        System.out.println(usersArrayList.getUsersNumber());

    }
}