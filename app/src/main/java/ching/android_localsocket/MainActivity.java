package ching.android_localsocket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ching.android_localsocket.layout.AddView;

public class MainActivity extends AppCompatActivity {
    final static String TAG = "MainActivity";
    private Button bt_connect;
    private AddView mAddView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_connect = (Button)findViewById(R.id.bt_connect);
        bt_connect.setOnClickListener(onClick);
        setAddView();



    }
    private void setAddView(){
        mAddView = new AddView(this);
        mAddView.setAddView();
    }



    View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.bt_connect:
                    mAddView.showPictures("Up");
                    break;
                default:
                    break;


            }
        }
    };
}
