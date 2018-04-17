package wijeindhovenscraper;

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
        System.out.println(getMailAddressFromName("Asya van Alst"));
    }
    
    
    static String getMailAddressFromName(String fullname)
    {
        return getMailAddressFromName("placeholderfirst","placeholderlast");
    }
    
    static String getMailAddressFromName(String firstname, String lastname)
    {
       
        return "no";
    }
}
