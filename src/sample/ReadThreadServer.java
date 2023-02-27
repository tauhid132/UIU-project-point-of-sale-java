package sample;

import util.NetworkUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReadThreadServer implements Runnable{
    Thread t;
    HashMap<String, NetworkUtil> clientMap;
    NetworkUtil networkUtil;
    ReadThreadServer(HashMap<String,NetworkUtil> clientMap,NetworkUtil networkUtil){
        this.clientMap = clientMap;
        this.networkUtil = networkUtil;
        this.t = new Thread(this);
        this.t.start();
    }

    @Override
    public void run() {

        //Scanner sc = new Scanner(System.in);
        while(true){


            String str = null;
            try {
                str = (String) networkUtil.read();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println(str); // student,uiu,registration done
            String[] fields = str.split("###");
            String src = fields[0];
            String msg = fields[1];

            for(Map.Entry client:clientMap.entrySet()){
                String clientName = (String) client.getKey();
                if(!(clientName.equals(src))) {
                    NetworkUtil networkUtil = clientMap.get(clientName);
                    try {
                        networkUtil.write(str);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

        }
    }
}