package server.sys;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

class InputThread implements Runnable {
  private Scanner in;
  private ServerThread parent;

  private Socket socket;

  public InputThread(ServerThread parent) {
    this.parent = parent;
    this.socket = parent.socket;
    try {
      in = new Scanner(this.parent.socket.getInputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    if (in.hasNext()) {
      Server.closeClients(this.socket);
    }
  }
}