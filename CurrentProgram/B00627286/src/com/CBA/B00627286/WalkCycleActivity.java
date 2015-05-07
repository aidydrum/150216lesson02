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
 * UPDATE COMMENT ABOUT PROGRAM HERE
 */

//public class AndroidAdvanceSqliteActivity extends Activity implements View.OnClickListener

public class WalkCycleActivity extends Activity implements View.OnClickListener {
    private Button btn_walk, btn_cycle;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitywalkcycle);

        btn_walk = (Button) findViewById(R.id.btn_walk);
        btn_cycle = (Button) findViewById(R.id.btn_cycle);
        btn_walk.setOnClickListener(this);
        btn_cycle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btn_walk:
                SimpleDateFormat sDatewalk = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                Date d = new Date();
                String date = sDatewalk.format(d);

                Intent walkintent = new Intent(WalkCycleActivity.this, WalkDistanceActivity.class);
                walkintent.putExtra("currentTime", date);
                startActivity(walkintent);
                break;

            case R.id.btn_cycle:
                SimpleDateFormat sDateCycle = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                Date dt = new Date();
                String dateR = sDateCycle.format(dt);

                Intent cycleintent = new Intent(WalkCycleActivity.this, CycleDistanceActivity.class);
                cycleintent.putExtra("currentTime", dateR);
                startActivity(cycleintent);
                break;
            default:
                break;
        }
    }
}
