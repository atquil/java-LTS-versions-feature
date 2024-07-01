package java_21._4_StringImprovements;

/**
 * @author atquil
 */
public class _3_StringBuilderStringBuffer {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.repeat("Hello Everyone ", 2);
        sb.repeat(0x1f600, 5);
        sb.repeat('!', 3);
        System.out.println(sb);
    }
}
