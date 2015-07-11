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
import com.simensohol.simpleapp.core.database.FirebaseDatabaseHelper;
import com.simensohol.simpleapp.core.entity.FirebaseObject;
import com.simensohol.simpleapp.gui.MainActivity;

/**
 * @author Simen Søhol
 */
public class FirebaseSavePage extends Fragment implements View.OnClickListener {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private FirebaseDatabaseHelper firebase;
    private Context context;
    private EditText editText;

    public static FirebaseSavePage newInstance(int sectionNumber) {
        FirebaseSavePage fragment = new FirebaseSavePage();

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
        firebase = new FirebaseDatabaseHelper(getContext());

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
        FirebaseObject obj = new FirebaseObject();
        obj.setName(editText.getText().toString());

        if (obj.getName().length() > 0) {
            firebase.pushToFirebase(obj);
            showMessage(getString(R.string.txtSaved));
            editText.setText(R.string.txtEmptyString);
        }
        firebase.getAll();

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
