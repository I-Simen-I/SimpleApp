package com.simensohol.simpleapp.gui.pages;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.simensohol.simpleapp.R;
import com.simensohol.simpleapp.core.database.SimpleAppDAO;
import com.simensohol.simpleapp.core.entity.ObjectToSave;
import com.simensohol.simpleapp.gui.MainActivity;
import com.simensohol.simpleapp.gui.utils.ListAdapter;

import java.util.List;

/**
 * @author Simen Søhol
 */
public class SecondPage extends Fragment implements View.OnClickListener {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private SimpleAppDAO simpleAppDAO;
    private Context context;
    private ListAdapter adapter;
    private ListView list;

    public static SecondPage newInstance(int sectionNumber) {
        SecondPage fragment = new SecondPage();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.second_page, container, false);

        Button btnEmpty = (Button) rootView.findViewById(R.id.buttonEmpty);
        btnEmpty.setOnClickListener(this);
        list = (ListView) rootView.findViewById(R.id.list);

        populateList();
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));

        setContext(activity);
        simpleAppDAO = new SimpleAppDAO(getContext());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonEmpty:
                empty();
                break;
        }
    }

    private void populateList() {
        List<ObjectToSave> objectList = simpleAppDAO.getAll();
        adapter = new ListAdapter(getContext(), R.layout.custom_list_row, objectList);
        list.setAdapter(adapter);
    }

    private void empty() {
        simpleAppDAO.deleteAll();
        populateList();

        showMessage(getString(R.string.txtEmpty));
    }

    private void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    private Context getContext() {
        return context;
    }

    private void setContext(Context context) {
        this.context = context;
    }
}
