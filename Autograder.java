import java.net.*;
import java.io.*;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Autograder {
    public static void main(String[] args) throws Exception {
        
      Autograder a = new Autograder();
      String toSend = a.readFile(args[0]);
      System.out.println(toSend);
      a.sendPost(toSend);


    }

    private String readFile(String fileName){
      String s = "";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                s += line + "\n";
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        return s.trim() + "\n";

    }

    private void sendPost(String toSend) throws Exception {

      String url = "http://autograde.herokuapp.com";
      String urlParameters = toSend;

      URL obj = new URL(url);
      HttpURLConnection con = (HttpURLConnection) obj.openConnection();

      //add reuqest header
      con.setRequestMethod("POST");
      //con.setRequestProperty("User-Agent", USER_AGENT);
      con.setRequestProperty("Content-Type", "text/json");
      //con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

      
      
      // Send post request
      con.setDoOutput(true);
      DataOutputStream wr = new DataOutputStream(con.getOutputStream());
      wr.writeBytes(urlParameters);
      wr.flush();
      wr.close();

      int responseCode = con.getResponseCode();
      // System.out.println("\nSending 'POST' request to URL : " + url);
      // System.out.println("Post parameters : " + urlParameters);
      // System.out.println("Response Code : " + responseCode);

      BufferedReader in = new BufferedReader(
              new InputStreamReader(con.getInputStream()));
      String inputLine;
      StringBuffer response = new StringBuffer();

      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();
      
      //print result
      //System.out.println(response.toString());
      if(response.toString().equals("correct")){
        System.out.println("All tests passed!");
	System.exit(0);
      } else {
	System.out.println("Test Failed");
        System.exit(1);
      }

    }
}
