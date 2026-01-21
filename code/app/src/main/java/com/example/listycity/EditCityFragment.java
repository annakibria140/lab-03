package com.example.listycity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class EditCityFragment extends DialogFragment {

    /*
        this class implements a new fragment that pops up when the user
        clicks on a city in the list view. allows them to edit the city/province
     */
    interface EditCityListener {
        //sends data back to the activity
        void OnCityEdited(int position, City updatedCity);
    }


    //keys for storing and retreving data in the bundle
    private static final String ARG_CITY = "arg_city";
    private static final String ARG_POSITION = "arg_position";
    private EditCityListener listener;



    //newInstance is the reccomended way to send data into a Fragment.
    //store the selected city and position into a bundle + attach it to the fragment
    public static EditCityFragment newInstance(City city, int position) {
        Bundle args = new Bundle();

        //store data in the bundle
        args.putSerializable(ARG_CITY, city);
        args.putInt(ARG_POSITION, position);

        //create fragment and attach bundle arguments (right now the existing ones)
        EditCityFragment frag = new EditCityFragment();
        frag.setArguments(args);
        return frag;
    }


    //runs when the fragment is attached to the activity.
    //connect to the listener so we can call the activities method later.
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (EditCityListener) context;
    }

    //builds the popup dialog.
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //inflate the dialog layout
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_city, null);

        //get edit text fields from the layout
        EditText cityEt = view.findViewById(R.id.edit_text_city_text);
        EditText provEt = view.findViewById(R.id.edit_text_province_text);

        //retreive data passed to the fragment from the bundle
        Bundle args = getArguments();
        City city = (City) args.getSerializable(ARG_CITY);
        int position = args.getInt(ARG_POSITION);


        //prefill the text boxes so the user knows the original value they clicked on
        cityEt.setText(city.getName());
        provEt.setText(city.getProvince());

        //build dialog with buttons
        return new AlertDialog.Builder(getContext())
                .setTitle("Edit city")
                .setView(view)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Update", (d, w) -> {

                    // update the same city object with setters.
                    city.setName(cityEt.getText().toString());
                    city.setProvince(provEt.getText().toString());
                    //send updated list back to mainActivity
                    listener.OnCityEdited(position, city);
                })
                .create();
    }
}
