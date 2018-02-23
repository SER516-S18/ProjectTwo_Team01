package ser516.project2.team1.server.sys;
import java.net.*;
import java.util.Scanner;
import java.io.*;

public class client {
	public static void main(String[] args) throws UnknownHostException, IOException{
		
	 String temp;
	 
	 //create a socket
	 Socket s = new Socket("10.152.8.87", 8001);
	 
	 //receive value from server
	 Scanner sc1 = new Scanner(s.getInputStream());
	 temp = sc1.nextLine();
	 
     System.out.println(temp); // print the value which is from server
     //System.out.println("xxx");
     
     PrintWriter p = new PrintWriter(s.getOutputStream());
     p.println("channels=3;");
     
     p.flush();
     
    // Scanner sc2 = new Scanner(s.getInputStream());
	 //temp = sc2.nextLine();
	 //System.out.println("heeloo");
	 
	 for(;;){
		 if(sc1.hasNextLine()){
			 System.out.println(sc1.nextLine());
		 }
	 }
	 
	 
	 
	}
}
