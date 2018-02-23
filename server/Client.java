import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client {
  public static void main(String[] args) throws UnknownHostException, IOException{

    String temp;
    boolean isConnected = true;

    //create a socket
    Socket s = new Socket("localhost", 8001);

    //receive value from server
    Scanner sc1 = new Scanner(s.getInputStream());
    temp = sc1.nextLine();

    System.out.println(temp); // print the value which is from server
    //System.out.println("xxx");

    PrintWriter p = new PrintWriter(s.getOutputStream());
    p.println("channels=3;");

    p.flush();

    while (isConnected) {
      if(sc1.hasNextLine()){
        System.out.println("Getting: " + sc1.nextLine());
      }
    }
    
    sc1.close();
  }
}
