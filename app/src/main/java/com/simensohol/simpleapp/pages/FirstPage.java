package com.simensohol.simpleapp.pages;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.simensohol.simpleapp.MainActivity;
import com.simensohol.simpleapp.R;

/**
 * @author Simen Søhol
 */
public class FirstPage extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static FirstPage newInstance(int sectionNumber) {
        FirstPage fragment = new FirstPage();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.first_page, container, false);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
    }
}
