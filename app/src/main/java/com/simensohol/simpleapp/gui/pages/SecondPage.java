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
import com.simensohol.simpleapp.R;
import com.simensohol.simpleapp.gui.MainActivity;

/**
 * @author Simen Søhol
 */
public class SecondPage extends Fragment implements View.OnClickListener {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private Button btnEmpty;
    private ListView list;

    public static SecondPage newInstance(int sectionNumber, Context context) {
        SecondPage fragment = new SecondPage();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.second_page, container, false);

        btnEmpty = (Button) rootView.findViewById(R.id.buttonEmpty);
        list = (ListView) rootView.findViewById(R.id.list);

        populateList();

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
    }

    private void populateList() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonEmpty:
                populateList();
                break;
        }
    }
}
