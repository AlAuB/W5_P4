package com.example.w5_p4;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ControlFrame#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ControlFrame extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    View view;
    TextView scoreTextView;
    Button newGame;
    private controlListener listener;

    public ControlFrame() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ControlFrame.
     */
    public static ControlFrame newInstance(String param1, String param2) {
        ControlFrame fragment = new ControlFrame();
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
        view = inflater.inflate(R.layout.fragment_control_frame, container, false);
        scoreTextView = view.findViewById(R.id.scoreNum);
        newGame = view.findViewById(R.id.newGame);
        newGame.setOnClickListener(view -> listener.createNewGame());
        return view;
    }

    public void setScore(int score) {
        scoreTextView.setText(String.valueOf(score));
    }

    public interface controlListener {
        void createNewGame();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof controlListener)
            listener = (controlListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}