package StringFunctions;

public class StringFuncs {

    public static String uncaps(String string) {
        String ns = "";
        if (string.length() == 0) {
            return string;
        }
        char ch;
        for (int i = 0; i < string.length(); i++) {
            ch = string.charAt(i);
            if (Character.isUpperCase(ch)) {
                ch = Character.toLowerCase(ch);
            }
            ns += ch;
        }
        return ns;
    }
}
