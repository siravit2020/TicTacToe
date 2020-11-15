package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.xml.datatype.Duration;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9;
    private final int[] dra = {R.drawable.ic_group_1, R.drawable.ic_ellipse_1, R.drawable.empty};
    private TicTacToeClient ticTacToeClient;
    private LinearLayout linearLayout;
    private ImageView[] v;
    private String type;
    private String typeC="";
    private boolean status = true;
    private boolean status2 = true;
    String ip;
    tttConnect tc;
    tttHost th;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = findViewById(R.id.linear);
        type = getIntent().getStringExtra("type");
        typeC = getIntent().getStringExtra("c_h");




        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
        img5 = findViewById(R.id.img5);
        img6 = findViewById(R.id.img6);
        img7 = findViewById(R.id.img7);
        img8 = findViewById(R.id.img8);
        img9 = findViewById(R.id.img9);
        v = new ImageView[]{img1, img2, img3, img4, img5, img6, img7, img8, img9};
        for (ImageView imageView : v) {
            imageView.setTag("");
            imageView.setOnClickListener(this);
        }
        if(getIntent().hasExtra("c_h")){
            typeC = getIntent().getStringExtra("c_h");
            if(typeC.equals("client"))
                ip = getIntent().getStringExtra("ip");
            if(typeC.equals("host")){
                Log.d("host","start");
                th = new tttHost();
                th.execute();
            }
        }


    }

    @Override
    public void onClick(View v) {
        ImageView view = (ImageView) v;
        //TicTacToeClient ticTacToeClient = new TicTacToeClient();
        tc = new tttConnect();
        if(view.getTag() == "") {
            view.setImageResource(R.drawable.ic_o);
            if (type.equals("online") && typeC.equals("client")) {
                if (view == img1) {
                    //ticTacToeClient.execute("TTP_P1_X0_Y0");
                    tc.execute();
                }
                if (view == img2) {
                    //ticTacToeClient.execute("TTP_P1_X0_Y1");
                    tc.execute();
                }
                if (view == img3) {
                    //ticTacToeClient.execute("TTP_P1_X0_Y2");
                    tc.execute();
                }
                if (view == img4) {
                    //ticTacToeClient.execute("TTP_P1_X1_Y0");
                    tc.execute();
                }
                if (view == img5) {
                    //ticTacToeClient.execute("TTP_P1_X1_Y1");
                    tc.execute();
                }
                if (view == img6) {
                    //ticTacToeClient.execute("TTP_P1_X1_Y2");
                    tc.execute();
                }
                if (view == img7) {
                    //ticTacToeClient.execute("TTP_P1_X2_Y0");
                    tc.execute();
                }
                if (view == img8) {
                    //ticTacToeClient.execute("TTP_P1_X2_Y1");
                    tc.execute();
                }
                if (view == img9) {
                    //ticTacToeClient.execute("TTP_P1_X2_Y2");
                    tc.execute();
                }
                view.setTag("o");
                preCheck();
            }
            else if(type.equals("online")&& typeC.equals("host")){

            }
            else {
                view.setTag("o");
                preCheck();
                random();
            }
        }



    }

    private void random() {
        final int min = 1;
        final int max = 9;
        final int random = new Random().nextInt((max - min) + 1) + min;
        Log.d("ran",String.valueOf(random));
        if(status)
        switch (random) {
            case 1:
                if (img1.getTag() == "") {
                    img1.setImageResource(R.drawable.ic_x);
                    img1.setTag("x");
                    break;
                }
            case 2:
                if (img2.getTag() == "") {
                    img2.setImageResource(R.drawable.ic_x);
                    img2.setTag("x");
                    break;
                }
            case 3:
                if (img3.getTag() == "") {
                    img3.setImageResource(R.drawable.ic_x);
                    img3.setTag("x");
                    break;
                }
            case 4:
                if (img4.getTag() == "") {
                    img4.setImageResource(R.drawable.ic_x);
                    img4.setTag("x");
                    break;
                }
            case 5:
                if (img5.getTag() == "") {
                    img5.setImageResource(R.drawable.ic_x);
                    img5.setTag("x");
                    break;
                }
            case 6:
                if (img6.getTag() == "") {
                    img6.setImageResource(R.drawable.ic_x);
                    img6.setTag("x");
                    break;
                }
            case 7:
                if (img7.getTag() == "") {
                    img7.setImageResource(R.drawable.ic_x);
                    img7.setTag("x");
                    break;
                }
            case 8:
                if (img8.getTag() == "") {
                    img8.setImageResource(R.drawable.ic_x);
                    img8.setTag("x");
                    break;
                }
            case 9:
                if (img9.getTag() == "") {
                    img9.setImageResource(R.drawable.ic_x);
                    img9.setTag("x");
                    break;
                }
                else{end();}

        }
        preCheck();
    }
    private void end(){
        if(img1.getTag() == ""){
            img1.setImageResource(R.drawable.ic_x);
            img1.setTag("x");
            Log.d("end1","1");
        }
        else if(img2.getTag() == ""){
            img2.setImageResource(R.drawable.ic_x);
            img2.setTag("x");
            Log.d("end2","1");
        }
        else if(img3.getTag() == ""){
            img3.setImageResource(R.drawable.ic_x);
            img3.setTag("x");
            Log.d("end3","1");
        }
        else if(img4.getTag() == ""){
            img4.setImageResource(R.drawable.ic_x);
            img4.setTag("x");
            Log.d("end4","1");
        }
        else if(img5.getTag() == ""){
            img5.setImageResource(R.drawable.ic_x);
            img5.setTag("x");
            Log.d("end5","1");
        }
        else if(img6.getTag() == ""){
            img6.setImageResource(R.drawable.ic_x);
            img6.setTag("x");
            Log.d("end6","1");
        }
        else if(img7.getTag() == ""){
            img7.setImageResource(R.drawable.ic_x);
            img7.setTag("x");
            Log.d("end7","1");
        }
        else if(img8.getTag() == ""){
            img8.setImageResource(R.drawable.ic_x);
            img8.setTag("x");
            Log.d("end8","1");
        }
        else if(img9.getTag() == ""){
            img9.setImageResource(R.drawable.ic_x);
            img9.setTag("x");
            Log.d("end9","1");
        }
    }

    private void preCheck() {
        if (img1.getTag().equals(img2.getTag()) && img2.getTag().equals(img3.getTag())) {
            checkCR(img1, img2, img3);
        }
        if (img4.getTag().equals(img5.getTag()) && img5.getTag().equals(img6.getTag())) {
            checkCR(img4, img5, img6);
        }
        if (img7.getTag().equals(img8.getTag()) && img8.getTag().equals(img9.getTag())) {
            checkCR(img7, img8, img9);
        }
        if (img1.getTag().equals(img4.getTag()) && img4.getTag().equals(img7.getTag())) {
            checkCR(img1, img4, img7);
        }
        if (img2.getTag().equals(img5.getTag()) && img5.getTag().equals(img8.getTag())) {
            checkCR(img2, img5, img8);
        }
        if (img3.getTag().equals(img6.getTag()) && img6.getTag().equals(img9.getTag())) {
            checkCR(img3, img6, img9);
        }
        if (img1.getTag().equals(img5.getTag()) && img5.getTag().equals(img9.getTag())) {
            checkCR(img1, img5, img9);
        }
        if (img3.getTag().equals(img5.getTag()) && img5.getTag().equals(img7.getTag())) {
            checkCR(img3, img5, img7);
        }
        if(!img1.getTag().equals("") && !img2.getTag().equals("") && !img3.getTag().equals("")
                && !img4.getTag().equals("") && !img5.getTag().equals("") && !img6.getTag().equals("")
                && !img7.getTag().equals("") && !img8.getTag().equals("") && !img9.getTag().equals("")){
            show();
            Snackbar.make(img1, "Equal", Snackbar.LENGTH_SHORT).show();
        }

    }

    private void checkCR(ImageView im1, ImageView im2, ImageView im3) {
        if (im1.getTag().equals("x")) {
            im1.setImageResource(R.drawable.ic_x_com);
            im2.setImageResource(R.drawable.ic_x_com);
            im3.setImageResource(R.drawable.ic_x_com);
            Snackbar.make(im1, "X Win", Snackbar.LENGTH_SHORT).show();
            status=false;
            show();

        } else if (im1.getTag().equals("o")) {
            im1.setImageResource(R.drawable.ic_o_com);
            im2.setImageResource(R.drawable.ic_o_com);
            im3.setImageResource(R.drawable.ic_o_com);
            Snackbar.make(im1, "O Win", Snackbar.LENGTH_SHORT).show();
            status=false;
            show();
        }

//192.168.1.4
    }
    private void show(){
        linearLayout.setVisibility(View.VISIBLE);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                clear();
                linearLayout.setVisibility(View.GONE);
                status=true;
            }
        }, 1500);
    }

    private void clear() {
        for (ImageView imageView : v) {
            imageView.setTag("");
            imageView.setImageResource(R.drawable.empty);
        }

    }

    private class TicTacToeServer extends AsyncTask<String, Integer, String> {

        @SuppressLint("WrongThread")
        @Override
        protected String doInBackground(String... strings) {
            try {

                Log.d("start", "start");
                ServerSocket serverSocket = new ServerSocket(2222);
                serverSocket.setSoTimeout(10000);
                Socket socket = serverSocket.accept();
                Log.d("start", "start2");
                DataInputStream in = new DataInputStream(socket.getInputStream());
                String data = in.readUTF();
                in.close();
                Log.d("tt", data);
                if (data == "P11") {
                    img1.setImageResource(dra[0]);
                }

                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                out.writeUTF("Ack 220 OK!!");
                out.writeUTF("Ack 220 OK!!");
                out.flush();
                out.close();
                serverSocket.close();
                socket.close();


            } catch (IOException e) {
                Log.d("error", e.getLocalizedMessage());
            }
            return "null";
        }
    }

    public class TicTacToeClient extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            Log.d("ggg", "fff");
            String Host = ip;
            int Port = 2222;
            try {
                Socket socketClient = new Socket(Host, Port);
                DataOutputStream out = new DataOutputStream(socketClient.getOutputStream());
                DataInputStream in = new DataInputStream(socketClient.getInputStream());
                out.writeUTF(strings[0]);
                out.flush();
                String data = in.readUTF();
                in.close();
                out.close();
                Log.d("tt", data);
                socketClient.close();

            } catch (IOException e) {
                Log.d("error", e.getLocalizedMessage());
            }
            return "null";
        }
    }

    class tttConnect extends AsyncTask<String, String, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            Log.d("tc","start");
            try{
                Socket socketClient = new Socket(ip, 2222);
                Log.d("tc","start1");

                Log.d("tc","start2");

                Log.d("tc","start3");

                Log.d("tc","start4");
                DataOutputStream out = new DataOutputStream(socketClient.getOutputStream());
                Log.d("tc","start5");
                out.writeUTF("W2C");
                Log.d("tc","start6");
                out.flush();
                Log.d("tc","start7");
                DataInputStream in = new DataInputStream(socketClient.getInputStream());
                String msg = in.readUTF();
                Log.d("tc","start");
                System.out.println(msg);
                if(msg.equals("ACK 220 OK!!")){
                    tc.cancel(true);
                    System.out.println("สวยสุดๆ");

                }

            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
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
                        while(true){

                            System.out.println("Execute5");
                            String msg = in.readUTF();
                            //Received data
                            System.out.println("Received: "+ msg);
                            //send data

                            if(msg.equals("W2C")){
                                out.writeUTF("ACK 220 OK!!");
                                System.out.println("W2C ACC");
                                break;
                            }
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



}