package com.CBA.B00627286;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: anon
 * Date: 08/07/13
 * Time: 13:06
 * Fuel input or record viewing paths
 */

public class PrePumpActivity extends Activity implements View.OnClickListener {
    private Button btn_add, btn_view;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityprepump);

        btn_add = (Button) findViewById(R.id.btn_add);
        btn_view = (Button) findViewById(R.id.btn_view);
        btn_add.setOnClickListener(this);
        btn_view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btn_add:                               //2014/01/10 12:59:00
                SimpleDateFormat sdatef = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                Date d = new Date();
                String date = sdatef.format(d);
                // leads to users vehicle choice screen
                Intent addIntent = new Intent(PrePumpActivity.this, PumpVehChoiceActivity.class);
                addIntent.putExtra("currentTime", date);
                startActivity(addIntent);
                break;

            case R.id.btn_view:                                    // leads to viewing records
                Intent viewintent = new Intent(PrePumpActivity.this, ViewPumpRecord.class);
                startActivity(viewintent);
                break;
            default:
                break;
        }
    }
}
