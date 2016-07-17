package ching.android_localsocket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ching.android_localsocket.layout.AddView;
import ching.android_localsocket.listener.ClientCallBack;
import ching.android_localsocket.localsocket.ClientConnect;
import ching.android_localsocket.localsocket.ServerThread;


public class MainActivity extends AppCompatActivity implements ClientCallBack {
    final static String TAG = "MainActivity";
    private Button bt_connect;
    private Button bt_cencel;
    private AddView mAddView;
    private ClientConnect mClientConnect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_connect = (Button)findViewById(R.id.bt_connect);
        bt_cencel = (Button)findViewById(R.id.bt_cencel);
        bt_connect.setOnClickListener(onClick);
        bt_cencel.setOnClickListener(onClick);
        setAddView();
        Thread run = new ServerThread();
        Thread mythread = new Thread(run);
        mythread.start();
        initClient();

    }
    private void setAddView(){
        mAddView = new AddView(this);
        mAddView.setAddView();
    }
    private void initClient(){
        mClientConnect = new ClientConnect(this,this);
    if(mClientConnect.connect() == false){
        mClientConnect.connect();
     }

    }



    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.bt_connect:
                    mClientConnect.getData();
                    break;
                case R.id.bt_cencel:

                    mClientConnect.cencel();
                    break;
                default:
                    break;


            }
        }
    };


    @Override
    public void receivedMessage(String mesage) {
        mAddView.showPictures(mesage);


    }
}
