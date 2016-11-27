package com.tutorials.hp.mysqlgridviewbool.mAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.tutorials.hp.mysqlgridviewbool.R;
import com.tutorials.hp.mysqlgridviewbool.mModel.Spacecraft;

import java.util.ArrayList;

/**
 * Created by Oclemy on 10/14/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class GridViewAdapter extends BaseAdapter {

    Context c;
    ArrayList<Spacecraft> spacecrafts;

    public GridViewAdapter(Context c, ArrayList<Spacecraft> spacecrafts) {
        this.c = c;
        this.spacecrafts = spacecrafts;
    }

    @Override
    public int getCount() {
        return spacecrafts.size();
    }

    @Override
    public Object getItem(int i) {
        return spacecrafts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view= LayoutInflater.from(c).inflate(R.layout.model,viewGroup,false);
        }

        TextView txtName = (TextView) view.findViewById(R.id.nameTxt);
        TextView txtPropellant = (TextView) view.findViewById(R.id.txtPropellant);

        CheckBox chkTechExists = (CheckBox) view.findViewById(R.id.chkTechExists);

        final Spacecraft s= (Spacecraft) this.getItem(i);

        txtName.setText(s.getName());
        txtPropellant.setText(s.getPropellant());
        chkTechExists.setChecked( s.getTechnologyExists()==1);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(c, s.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
