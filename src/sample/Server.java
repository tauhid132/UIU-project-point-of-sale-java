package sample;

import util.NetworkUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
    private ServerSocket serverSocket;
    public HashMap<String, NetworkUtil> clientMap;


    Server(){
        clientMap  = new HashMap<>();
        try {
            serverSocket = new ServerSocket(44448);

            while(true){
                System.out.println("Server Waiting for clients ... ");
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
                System.out.println("hhh");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void serve(Socket clientSocket) throws IOException, ClassNotFoundException {
        NetworkUtil netUtil = new NetworkUtil(clientSocket);
        String clientName = (String) netUtil.read();
        clientMap.put(clientName, netUtil);
        System.out.println(clientName + " connected with the server");
        new ReadThreadServer(clientMap, netUtil);
    }

    public static void main(String[] args){
        Server server = new Server();
    }
}