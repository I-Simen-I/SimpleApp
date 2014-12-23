package com.simensohol.simpleapp.gui.pages;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.simensohol.simpleapp.R;
import com.simensohol.simpleapp.core.database.SimpleAppDAO;
import com.simensohol.simpleapp.core.entity.ObjectToSave;
import com.simensohol.simpleapp.gui.MainActivity;

/**
 * @author Simen Søhol
 */
public class FirstPage extends Fragment implements View.OnClickListener {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private Context context;
    private SimpleAppDAO simpleAppDAO;
    private EditText editText;


    public static FirstPage newInstance(int sectionNumber) {
        FirstPage fragment = new FirstPage();

        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.first_page, container, false);

        Button btnSave = (Button) rootView.findViewById(R.id.buttonSave);
        btnSave.setOnClickListener(this);
        editText = (EditText) rootView.findViewById(R.id.page1InputText);

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
            case R.id.buttonSave:
                save();
                break;
        }
    }

    private void save() {
        int id = (int) (Math.random() * 100000) + 1;

        ObjectToSave objectToSave = new ObjectToSave();
        objectToSave.setId(id);
        objectToSave.setName(editText.getText().toString());

        simpleAppDAO.save(objectToSave);

        showMessage("Saved");
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
