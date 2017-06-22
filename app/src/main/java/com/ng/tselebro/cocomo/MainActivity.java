package com.ng.tselebro.cocomo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            int value = item.getItemId();

            switch (value) {
                case R.id.organic:
                    calculateEstimate(value);
                    return true;
                case R.id.semidetached:
                    calculateEstimate(value);
                    return true;
                case R.id.Embedded:
                    calculateEstimate(value);
                    return true;
            }
            return false;
        }

    };


    @BindView(R.id.computeButton)
    Button ComputeEstimateDevelopment;


    @BindView(R.id.size_editText)
    EditText size;

    @BindView(R.id.display_estimate)
    TextView mDisplayEstimate;

    @BindView(R.id.tdev)
    TextView mDisplayDevEstimate;

    public static final double a_ORGANIC = 2.4;
    public static final double a_EMBEDDED = 3.0;
    public static final double a_DETACHED= 3.6;

    public static final double b_ORGANIC= 1.05;
    public static final double b_EMBEDDED = 1.12;
    public static final double b_DETACHED = 1.20;

    public static final double tDev_constant = 2.5;

    public static final double t_ORGANIC= 0.38;
    public static final double t_EMBEDDED = 0.35;
    public static final double t_DETACHED = 0.32;

    private double m_eOrganic;
    private double m_eDetached;
    private double m_eEmbedded;



    private double t_eOrganic;
    private double t_eDetached;
    private double t_eEmbedded;

    private double mKLOC;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        ButterKnife.bind(this);

        ComputeEstimateDevelopment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = navigation.getSelectedItemId();
                calculateEstimate(value);
            }
        });



    }



    public void calculateEstimate(int value) {
        String input = size.getText().toString();


        if (input.isEmpty()) {Toast.makeText(this, "value for size must be entered", Toast.LENGTH_SHORT).show();}
        else{


            switch (value){
                case R.id.organic:
                    mKLOC = Double.valueOf(input);
                    m_eOrganic = a_ORGANIC  * Math.pow(mKLOC, b_ORGANIC);
                    t_eOrganic = tDev_constant * Math.pow(m_eOrganic, t_ORGANIC);


                    String Eoutput = String.valueOf(m_eOrganic);
                    mDisplayEstimate.setText(Eoutput.substring(0, 5));
                    //display dev
                    String toutput = String.valueOf(t_eOrganic);
                    mDisplayDevEstimate.setText(toutput.substring(0, 5));
                    break;
                case R.id.semidetached:

                    mKLOC = Double.valueOf(input);
                    m_eDetached = a_DETACHED  * Math.pow(mKLOC, b_DETACHED);
                    t_eDetached = tDev_constant * Math.pow(m_eDetached, t_DETACHED);


                    String doutput = String.valueOf(m_eDetached);
                    mDisplayEstimate.setText(doutput.substring(0, 5));
                    //display dev
                    String tdoutput = String.valueOf(t_eDetached);
                    mDisplayDevEstimate.setText(tdoutput.substring(0, 5));

                    break;
                case R.id.Embedded:
                    mKLOC = Double.valueOf(input);
                    m_eEmbedded = a_EMBEDDED  * Math.pow(mKLOC, b_EMBEDDED);
                    t_eEmbedded = tDev_constant * Math.pow(m_eEmbedded, t_EMBEDDED);


                    String Emoutput = String.valueOf(m_eEmbedded);
                    mDisplayEstimate.setText(Emoutput.substring(0, 5));
                    //display dev
                    String Emdoutput = String.valueOf(t_eEmbedded);
                    mDisplayDevEstimate.setText(Emdoutput.substring(0, 5));
                    break;


            }




        }



    }

}
