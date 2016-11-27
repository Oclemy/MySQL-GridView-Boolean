package com.tutorials.hp.mysqlgridviewbool;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.tutorials.hp.mysqlgridviewbool.mAdapter.GridViewAdapter;
import com.tutorials.hp.mysqlgridviewbool.mModel.Spacecraft;
import com.tutorials.hp.mysqlgridviewbool.mMySQL.MySQLClient;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //INSTANCE FIELDS
    private TextInputEditText txtName;
    private CheckBox chkTechnologyExists;
    private Spinner spPropellant;
    private GridView gv;
    private Button btnAdd,btnRetrieve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //REFERENCE VIEWS
        this.initializeViews();

        populatePropellants();

        //HANDLE EVENTS
        this.handleClickEvents();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               gv.setAdapter(new GridViewAdapter(MainActivity.this,new ArrayList<Spacecraft>()));
            }
        });




    }

    /*
    REFERENCE VIEWS
     */
    private void initializeViews()
    {

        txtName= (TextInputEditText) findViewById(R.id.nameTxt);
        chkTechnologyExists= (CheckBox) findViewById(R.id.techExists);
        spPropellant= (Spinner) findViewById(R.id.sp);
        gv= (GridView) findViewById(R.id.gv);

        btnAdd= (Button) findViewById(R.id.addBtn);
        btnRetrieve= (Button) findViewById(R.id.refreshBtn);


    }

    /*
    HANDLE CLICK EVENTS
     */
    private void handleClickEvents()
    {
        //EVENTS : ADD
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //GET VALUES
                String name=txtName.getText().toString();
                String propellant=spPropellant.getSelectedItem().toString();
                Boolean technologyexists=chkTechnologyExists.isChecked();

                //BASIC CLIENT SIDE VALIDATION
                if((name.length()<1 || propellant.length()<1  ))
                {
                    Toast.makeText(MainActivity.this, "Please Enter all Fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //SAVE

                    Spacecraft s=new Spacecraft();
                    s.setName(name);
                    s.setPropellant(propellant);
                    s.setTechnologyExists(technologyexists ? 1 : 0);

                    new MySQLClient(MainActivity.this).add(s,txtName,spPropellant);
                }
            }
        });

        //EVENTS : RETRIEVE
        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MySQLClient(MainActivity.this).retrieve(gv);

            }
        });


    }

    private void populatePropellants()
    {
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item);

        adapter.add("None");
        adapter.add("Chemical Energy");
        adapter.add("Nuclear Energy");
        adapter.add("Laser Beam");
        adapter.add("Anti-Matter");
        adapter.add("Plasma Ions");
        adapter.add("Warp Drive");

        spPropellant.setAdapter(adapter);
        spPropellant.setSelection(0);

    }
}
