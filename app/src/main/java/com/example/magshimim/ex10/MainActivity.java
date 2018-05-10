package com.example.magshimim.ex10;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements FragA.OnFragmentInteractionListener, FragB.OnFragmentInteractionListener {

    private int cnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            FragmentManager fm = getFragmentManager();

            if (savedInstanceState != null && fm.findFragmentByTag("AAA") != null) {
                return;
            }

            fm.beginTransaction().add(R.id.fragContainer, new FragA(), "AAA").commit();
        }
    }

    @Override
    public void OnClickEvent() {
        FragB fragB;
        this.cnt++;

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            fragB = (FragB) getFragmentManager().findFragmentById(R.id.FragB);

        else {
            fragB = new FragB();
            getFragmentManager().beginTransaction().add(R.id.fragContainer, fragB).addToBackStack("BBB").commit();
            getFragmentManager().executePendingTransactions();
        }
        fragB.countChanged(cnt);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("cnt", cnt);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            cnt = savedInstanceState.getInt("cnt");
            FragB fragB;

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                fragB = (FragB) getFragmentManager().findFragmentById(R.id.FragB);

            else {
                fragB = new FragB();
                getFragmentManager().beginTransaction().add(R.id.fragContainer, fragB).addToBackStack("BBB").commit();
                getFragmentManager().executePendingTransactions();
            }
            fragB.countChanged(cnt);
        }
    }
}