package com.simensohol.simpleapp.gui.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.simensohol.simpleapp.R;
import com.simensohol.simpleapp.core.entity.ObjectToSave;

import java.util.List;

/**
 * @author Simen Søhol
 */
public class ListAdapter extends ArrayAdapter<ObjectToSave> {
    private Context context;

    public ListAdapter(Context context, int layout, List<ObjectToSave> objectToSaveArrayList) {
        super(context, layout, objectToSaveArrayList);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ObjectToSave item = getItem(position);

        if (convertView == null) {
            LayoutInflater inflater;
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.my_list, null);
        } else {
            convertView = (RelativeLayout) convertView;
        }

        TextView name = (TextView) convertView.findViewById(R.id.txtName);

        name.setText(item.getName());

        return convertView;
    }
}