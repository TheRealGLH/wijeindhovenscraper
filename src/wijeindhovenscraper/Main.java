package wijeindhovenscraper;

import StringFunctions.StringFuncs;
import java.io.*;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author Martijn
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        String fp = "namen\\";
        System.out.println("Reading files...");
        File folder = new File(fp);
        File[] listOfFiles = folder.listFiles();
        //we get all files from the directory
        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.println(file.getName());
                if (file.canRead()) {
                    //and for each file found, we run this method
                    DumpFile(file);
                } else {
                    System.out.println("Can't read file: " + file.getPath());
                }
            }
        }
    }

    static String[] getNameSplit(String fullname) {
        //our source file has the names stored in a specific format, so most of this is just shitty hardcoded regex stuff
        int i = fullname.indexOf(",", -1);
        String lname = fullname.substring(0, i);

        int lastperiod = fullname.lastIndexOf(".");
        //lname = fullname.substring(lastperiod, i);

        i = fullname.indexOf("(", -1);
        try {
            String vv = fullname.substring(lastperiod + 1, i);
            lname = vv + lname;
            String fname = fullname.substring(i + 1);
            fname = fname.substring(0, fname.indexOf(")"));
            String[] splits = new String[2];
            splits[0] = fname;
            splits[1] = lname;
            return splits;
            //TODO: make sure that this try catch block encompasses more and that the catch statement isn't ass
        } catch (java.lang.StringIndexOutOfBoundsException ex) {
            System.out.println("Error formatting name:" + fullname + ". Exception: " + ex);
                        String[] splits = new String[2];
            splits[0] = "f";
            splits[1] = "f";
            return splits;
        }

    }

    static String getMailAddressFromName(String firstname, String lastname) {

        //simple func that formats the name based on the two inputs
        firstname = FixName(firstname);
        lastname = FixName(lastname);
        return firstname + lastname + "@wijeindhoven.nl";
    }

    static String FixName(String name) {
        //we use this to get rid of unwanted spaces and dashes in our names, the assumption here is that the source file has already been scrubbed of non-ASCII characters
        name = StringFuncs.uncaps(name);
        name = name.replace(" ", "");
        name = name.replace("-", "");
        return name;
    }

    static void DumpFile(File file) {
        try {
            //this method is used to read all names in a file and to dump them in CSV
            Scanner s = new Scanner(file);
            
            ArrayList<String> list = new ArrayList<String>();//we dump this in a list, rather than keeping the scanner open, because I don't want to keep having a file open in memory longer than needed
            while (s.hasNextLine()) {
                list.add(s.nextLine());
            }
            s.close();
            FileWriter fw = new FileWriter("emails" + file.getName() + ".csv");
            for (String line : list) {
                int i = line.indexOf("\t", -1);//items on a line are seperated by tabs, the 1st item is always the name
                String n = line.substring(0, i);
                String[] splits = getNameSplit(n);
                String e = getMailAddressFromName(splits[0], splits[1]);
                System.out.println("\t" + e);
                //fw.write("\"" + n + "\"" + "," + "\"" + e + "\"\n");
                fw.write("\""+n+"\",\""+e+"\",\""+splits[0]+"\",\""+splits[1]+ "\"\n");

            }
           fw.close();
        } catch (FileNotFoundException ex) {
            //TODO proper implementation for this
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
