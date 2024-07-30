package org.framework;
import java.io.*;

public class TestUtils {

    public static void explicitWait() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

     public enum Credentials {
        USERNAME(0),
        PASSWORD(1);
        private final int value;
         Credentials(int value) {this.value = value;}
    }

    //quick and dirty read credentials from file
    public String getCredentials(Credentials credentials) throws IOException {

            BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\cosmeticaBrasovGit\\Creds.txt"));
            if (credentials.ordinal() == 0) {
                //read first line and return it
                return bufferedReader.readLine();
            }
            else {
                //read first line and ignore
                bufferedReader.readLine();
                //return second line
                return bufferedReader.readLine();
            }
    }
}





