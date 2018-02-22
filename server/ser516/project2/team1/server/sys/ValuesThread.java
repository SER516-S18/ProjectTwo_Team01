package ser516.project2.team1.server.sys;

import java.util.Random;

public class ValuesThread implements Runnable {
    Random r = new Random(System.currentTimeMillis());
    //private static int max;
	//private static int min;	
	//private static boolean isRunning;
	//private static int random = 0;
	//private static int frequency = 5;

    /**
     * Counter constructor to spin off a new thread
     * 
     * @param lblCounter JLabel used for displaying counter
     * @param counter Initial counter value either 0 or 9
   
     */
    /*public ValuesThread(boolean isRunning, int max, int min, int frequency) {
      this.max = max;
      this.min = min;
      this.isRunning = isRunning;
      this.frequency = frequency;
    }
  */
	
	public ValuesThread(){
		
	}
    @Override
    public void run() {
      while (Server.isRunning) {
    	  Server.random = r.nextInt(Server.max - Server.min) + Server.min;
        try {
          Thread.sleep((int) (1000 / Server.frequency));
          Server.setConsoleInfo ("Sending: " + Server.random);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
}