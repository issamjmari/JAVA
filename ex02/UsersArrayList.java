package ex02;

public class UsersArrayList implements UsersList {
    private User[] users = new User[10];
    private int usersLength = 0;

    public void addUser(User newUser) {
        if (usersLength == users.length) {
            User[] newArray = new User[users.length + users.length / 2];
            for(int i = 0; i < users.length; i++) {
                newArray[i] = users[i];
            }
            users = newArray;
            System.out.println("I reached max with a new capacity of: "+ users.length);
        }
        users[usersLength] = newUser;
        usersLength++;
    }

    public User getUserById(int id) {
        for(int i = 0; i < users.length; i++) {
            if(users[i].getId() == id) {
                return users[i];
            }
        }
        throw new UserNotFoundException("user not found");
    }

    public User getUserByIndex(int index) {
        return users[index];
    }

    public int getUsersNumber() {
        return usersLength;
    }
}
