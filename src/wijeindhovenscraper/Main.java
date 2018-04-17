package wijeindhovenscraper;

import StringFunctions.StringFuncs;
import java.util.Arrays;

/**
 *
 * @author Martijn
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        System.out.println("Making HTTP request...");
        System.out.println(getMailAddressFromFullName("Asya van Alst"));
    }

    static String getMailAddressFromFullName(String fullname) {
        String[] parts = fullname.split(" ");
        String firstname = parts[0]; //
        parts = Arrays.copyOfRange(parts, 1, parts.length);
        String lastname = String.join(" ", parts);
        return getMailAddressFromName(firstname, lastname);
    }

    static String getMailAddressFromName(String firstname, String lastname) {

        firstname = FixName(firstname);
        lastname = FixName(lastname);
        return firstname + lastname + "@wijeindhoven.nl";
    }

    static String FixName(String name) {
        name = StringFuncs.uncaps(name);
        name = name.replace(" ", "");
        name = name.replace("-", "");
        return name;
    }
}
