package com.astro.astro_guruvani_astro.Auth;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.astro.astro_guruvani_astro.Home.HomePage;
import com.astro.astro_guruvani_astro.OtherFiles.Terms;
import com.astro.astro_guruvani_astro.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Email#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Email extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Email() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Email.
     */
    // TODO: Rename and change types and number of parameters
    public static Email newInstance(String param1, String param2) {
        Email fragment = new Email();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_email, container, false);
        TextInputEditText editText=view.findViewById(R.id.Email);
        TextInputLayout editTextLayout=view.findViewById(R.id.Emaillayout);
        TextView t2=view.findViewById(R.id.textView2);
        TextView t=view.findViewById(R.id.textView);
        MaterialButton bb=view.findViewById(R.id.button);
        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!email(editText,editTextLayout)){
                    return;
                }
                Intent in=new Intent(getActivity(),OTP.class);
                in.putExtra("Phone",editText.getText().toString());
                in.putExtra("TYPE","EMAIL");
                startActivity(in);
                getActivity().finish();
            }
        });
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(), Terms.class);
                startActivity(in);
                getActivity().finish();
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(), HomePage.class);
                startActivity(in);
                getActivity().finish();
            }
        });
        return view;
    }

    private boolean email(TextInputEditText tee, TextInputLayout te) {
        String val = tee.getText().toString();

        if (val.isEmpty()) {
            te.setError("Please Enter the Valid Email.");
            tee.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    te.setError(null);
                }
            });
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(val).matches()) {
            te.setError("Please Enter the Valid Email.");
            tee.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    te.setError(null);
                }
            });
            return false;
        } else {
            te.setError(null);
            return true;
        }
    }

}