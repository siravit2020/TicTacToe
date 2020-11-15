package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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

import javax.xml.datatype.Duration;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9;
    private int dra[] = {R.drawable.ic_group_1,R.drawable.ic_ellipse_1,R.drawable.empty};
    private TicTacToeClient ticTacToeClient;
    private LinearLayout linearLayout;
    private ImageView v[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = findViewById(R.id.linear);
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

        TicTacToeClient ticTacToeClient = new TicTacToeClient();
        ticTacToeClient.execute("TTP_P1_X0_Y0");
    }

    @Override
    public void onClick(View v) {//INSERT INTO `tbl_users` (`user_id`, `user_firstname`, `user_lastname`) VALUES (NULL, 'ศิรวิชญ์', 'พิชผล');
        ImageView view = (ImageView) v;
        view.setImageResource(R.drawable.ic_o);
       // view.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_x));
        TicTacToeClient ticTacToeClient = new TicTacToeClient();
        TicTacToeServer ticTacToeServer = new TicTacToeServer();
        //ticTacToeServer.execute();
        if(view == img1){
            ticTacToeClient.execute("TTP_P1_X0_Y0");

        }
        if(view == img2){
            ticTacToeClient.execute("TTP_P1_X0_Y1");

        }
        if(view == img3){
            ticTacToeClient.execute("TTP_P1_X0_Y2");

        }
        if(view == img4){
            ticTacToeClient.execute("TTP_P1_X1_Y0");

        }
        if(view == img5){
            ticTacToeClient.execute("TTP_P1_X1_Y1");

        }
        if(view == img6){
            ticTacToeClient.execute("TTP_P1_X1_Y2");

        }
        if(view == img7){
            ticTacToeClient.execute("TTP_P1_X2_Y0");

        }
        if(view == img8){
            ticTacToeClient.execute("TTP_P1_X2_Y1");

        }
        if(view == img9){
            ticTacToeClient.execute("TTP_P1_X2_Y2");

        }
        view.setTag("o");

        if(img1.getTag().equals(img2.getTag()) && img2.getTag().equals(img3.getTag())){
            checkCR(img1,img2,img3);
            Log.d("law","1");
        }
        if(img4.getTag().equals(img5.getTag()) && img5.getTag().equals(img6.getTag())){
            checkCR(img4,img5,img6);
            Log.d("law","2");
        }
        if(img7.getTag().equals(img8.getTag()) && img8.getTag().equals(img9.getTag())){
            checkCR(img7,img8,img9);
            Log.d("law","3");
        }
        if(img1.getTag().equals(img4.getTag()) && img4.getTag().equals(img7.getTag())){
            checkCR(img1,img4,img7);
            Log.d("law","4");
        }
        if(img2.getTag().equals(img5.getTag()) && img5.getTag().equals(img8.getTag())){
            checkCR(img2,img5,img8);
            Log.d("law","5");
        }
        if(img3.getTag().equals(img6.getTag()) && img6.getTag().equals(img9.getTag())){
            checkCR(img3,img6,img9);
            Log.d("law","6");
        }
        if(img1.getTag().equals(img5.getTag()) && img5.getTag().equals(img9.getTag())){
            checkCR(img1,img5,img9);
            Log.d("law","7");
        }
        if(img3.getTag().equals(img5.getTag()) && img5.getTag().equals(img7.getTag())){
            checkCR(img3,img5,img7);
            Log.d("law","8");
        }



    }
    private void checkCR(ImageView im1,ImageView im2,ImageView im3){
        if(im1.getTag().equals("x"))
        {
            im1.setImageResource(R.drawable.ic_x_com);
            im2.setImageResource(R.drawable.ic_x_com);
            im3.setImageResource(R.drawable.ic_x_com);
            Snackbar.make(im1, "X Win", Snackbar.LENGTH_SHORT).show();
            linearLayout.setVisibility(View.VISIBLE);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    clear();
                    linearLayout.setVisibility(View.GONE);
                }
            }, 1500);
        }
        else if(im1.getTag().equals("o")){
            im1.setImageResource(R.drawable.ic_o_com);
            im2.setImageResource(R.drawable.ic_o_com);
            im3.setImageResource(R.drawable.ic_o_com);
            Snackbar.make(im1, "O Win", Snackbar.LENGTH_SHORT).show();
            linearLayout.setVisibility(View.VISIBLE);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    clear();
                    linearLayout.setVisibility(View.GONE);
                }
            }, 1500);
        }
    }
    private void clear(){
        for (ImageView imageView : v) {
            imageView.setTag("");
            imageView.setImageResource(R.drawable.empty);
        }

    }
    private class TicTacToeServer extends AsyncTask<String,Integer,String> {

        @SuppressLint("WrongThread")
        @Override
        protected String doInBackground(String... strings) {
            try {
                ServerSocket serverSocket = new ServerSocket(2222);
                Socket socket = serverSocket.accept();
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                while (true) {
                    String data = in.readUTF();
                    if (data == "P11") {
                        img1.setImageResource(dra[0]);
                    }
                    out.writeUTF("Ack 220 OK!!");
                }
            } catch (IOException e) {

            }
            return "null";
        }
    }
    public class TicTacToeClient extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {
            Log.d("ggg","fff");
            String Host = "10.0.2.2";
            int Port = 2222;
            try {
                Socket socketClient = new Socket(Host,Port);
                DataOutputStream out = new DataOutputStream(socketClient.getOutputStream());
                out.writeUTF("STU_6003051613168_SIRAVIT_PICHPHOL");

            } catch (IOException e) {
                Log.d("error",e.getLocalizedMessage());
            }
            return "null";
        }

    }


}