package java_11._7_EpsilonGarbageCollector;

/**
 * @author atquil
 */
public class EpsilonGarbageCollector {
    public static void main(String[] args) {
        // Let's creat 1MB size for byte
        for (int i = 0; i < 1000; i++) {
            byte[] b = new byte[1024 * 1024];
        }
    }
    // java -XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC EpsilonGarbageCollector
}
