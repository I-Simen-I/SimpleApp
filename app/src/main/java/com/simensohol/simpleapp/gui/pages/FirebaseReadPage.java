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
import com.simensohol.simpleapp.core.database.FirebaseDatabaseHelper;
import com.simensohol.simpleapp.gui.MainActivity;
import com.simensohol.simpleapp.gui.utils.ListAdapter;

/**
 * @author Simen Søhol
 */
public class FirebaseReadPage extends Fragment implements View.OnClickListener {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private FirebaseDatabaseHelper firebase;
    private Context context;
    private ListAdapter adapter;
    private ListView list;

    public static FirebaseReadPage newInstance(int sectionNumber) {
        FirebaseReadPage fragment = new FirebaseReadPage();
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
        firebase = new FirebaseDatabaseHelper(getContext());
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
        //List<ObjectToSave> objectList = firebase.getAll();
        //adapter = new ListAdapter(getContext(), R.layout.custom_list_row, objectList);
        //list.setAdapter(adapter);
    }

    private void empty() {
        //.deleteAll();
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
