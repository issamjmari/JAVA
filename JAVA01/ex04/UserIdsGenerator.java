package ex04;

public class UserIdsGenerator {
    static int id = 1;
    static UserIdsGenerator instance = new UserIdsGenerator();

    private UserIdsGenerator() {
    }
    public static UserIdsGenerator getInstance() {
        return instance;
    }
    public int generateId() {
        return id++;
    }
    public void toStr() {
        System.out.println("UserIdsGenerator instance, current id: " + id);
    }
}
