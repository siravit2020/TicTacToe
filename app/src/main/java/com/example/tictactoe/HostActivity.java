package com.example.tictactoe;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HostActivity extends AppCompatActivity {

    private String ip;
    private int port;
    private tttHost th;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);
        th = new tttHost();
        th.execute();


    }
    class tttHost extends AsyncTask<String, String, Void> {

        @Override
        protected Void doInBackground(String... strings) {

            try{
                ServerSocket serverSocket = new ServerSocket(2222);
                while(true){
                    Socket socket = serverSocket.accept();
                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                    try{


                            System.out.println("Execute5");
                            String msg = in.readUTF();
                            //Received data
                            System.out.println("Received: "+ msg);
                            //send data
                            out.writeUTF("ACK 220 OK!!");
                            if(msg.equals("W2C")){

                                th.cancel(true);
                                Intent intent = new Intent(HostActivity.this, MainActivity.class);
                                intent.putExtra("type","online");
                                intent.putExtra("c_h", "host");
                                startActivity(intent);
                                if (isCancelled())
                                    break;



                        }
                    }catch(Exception e){
                        //
                    }

                }
            }catch(Exception e){
                //
            }
            return null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}