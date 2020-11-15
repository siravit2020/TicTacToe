package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Choose extends AppCompatActivity {
    EditText edt_ip;
    EditText edt_port;
    Button btn_connect;
    Button btn_host;
    private tttConnect tc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        edt_ip = findViewById(R.id.ip);
        edt_port = findViewById(R.id.port);
        btn_connect = findViewById(R.id.connect);
        btn_host = findViewById(R.id.host);
        btn_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tc = new tttConnect();
                tc.execute();
            }
        });
        btn_host.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Choose.this, HostActivity.class);
                startActivity(intent);
            }
        });
    }
    class tttConnect extends AsyncTask<String, String, Void> {
        String ip = edt_ip.getText().toString();

        @Override
        protected Void doInBackground(String... strings) {
            try{
                Socket socketClient = new Socket(ip, 2222);
                DataInputStream in = new DataInputStream(socketClient.getInputStream());
                DataOutputStream out = new DataOutputStream(socketClient.getOutputStream());
                out.writeUTF("W2C");
                String msg = in.readUTF();
                System.out.println(msg);
                if(msg.equals("ACK 220 OK!!")){
                    tc.cancel(true);
                    Intent intent = new Intent(Choose.this, MainActivity.class);
                    intent.putExtra("c_h", "client");
                    intent.putExtra("type","online");
                    intent.putExtra("ip", ip);
                    startActivity(intent);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        tc.cancel(true);
    }
}