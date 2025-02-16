package libmngsys.util;

public class IdGenerator {
    private  IdGenerator() {}
    public static int getUniqueId() {
        return (int) (Math.random() * 1000);
    }
}
