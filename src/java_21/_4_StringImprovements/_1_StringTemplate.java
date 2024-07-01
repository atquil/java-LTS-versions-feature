package java_21._4_StringImprovements;

/**
 * @author atquil
 */
public class _1_StringTemplate {
    public static void main(String[] args) {

        String tutorial = "Atquil";
        //let message = `Greetings ${ tutorial }!`;  			//TypeScript
        String welcomeText = STR."Welcome to \{tutorial}";
        System.out.println(welcomeText);
    }
}
