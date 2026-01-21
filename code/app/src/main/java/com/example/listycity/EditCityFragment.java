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

    interface EditCityListener {
        void OnCityEdited(int position, City updatedCity);
    }

    private static final String ARG_CITY = "arg_city";
    private static final String ARG_POSITION = "arg_position";


    public static EditCityFragment newInstance(City city, int position) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CITY, city);
        args.putInt(ARG_POSITION, position);

        EditCityFragment frag = new EditCityFragment();
        frag.setArguments(args);
        return frag;
    }


    private EditCityListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (EditCityListener) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_city, null);

        EditText cityEt = view.findViewById(R.id.edit_text_city_text);
        EditText provEt = view.findViewById(R.id.edit_text_province_text);

        Bundle args = getArguments();
        City city = (City) args.getSerializable(ARG_CITY);
        int position = args.getInt(ARG_POSITION);

        cityEt.setText(city.getName());
        provEt.setText(city.getProvince());

        return new AlertDialog.Builder(getContext())
                .setTitle("Edit city")
                .setView(view)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Update", (d, w) -> {
                    // either mutate existing object with setters:
                    city.setName(cityEt.getText().toString());
                    city.setProvince(provEt.getText().toString());

                    listener.OnCityEdited(position, city);
                })
                .create();
    }
}
