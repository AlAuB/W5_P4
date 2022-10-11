package com.example.w5_p4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameFrame#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameFrame extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View view;
    Button clear, submit;
    MaterialButton one, two, three, four;
    MaterialButton five, six, seven, eight;
    MaterialButton nine, ten, eleven, twelve;
    MaterialButton thirteen, fourteen, fifteen, sixteen;

    public GameFrame() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment gameFrame.
     */
    // TODO: Rename and change types and number of parameters
    public static GameFrame newInstance(String param1, String param2) {
        GameFrame fragment = new GameFrame();
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
        view = inflater.inflate(R.layout.fragment_game_frame, container, false);
        clear = view.findViewById(R.id.clear);
        submit = view.findViewById(R.id.submit);
        initiateMaterialButtons();
        setRandomLetter();
        return view;
    }

    private void initiateMaterialButtons() {
        one = setMaterialButton(R.id.one);
        two = setMaterialButton(R.id.two);
        three = setMaterialButton(R.id.three);
        four = setMaterialButton(R.id.four);
        five = setMaterialButton(R.id.five);
        six = setMaterialButton(R.id.six);
        seven = setMaterialButton(R.id.seven);
        eight = setMaterialButton(R.id.eight);
        nine = setMaterialButton(R.id.nine);
        ten = setMaterialButton(R.id.ten);
        eleven = setMaterialButton(R.id.eleven);
        twelve = setMaterialButton(R.id.twelve);
        thirteen = setMaterialButton(R.id.thirteen);
        fourteen = setMaterialButton(R.id.fourteen);
        fifteen = setMaterialButton(R.id.fifteen);
        sixteen = setMaterialButton(R.id.sixteen);
    }

    private void setRandomLetter() {
        one.setText(getRandomLetter());
        two.setText(getRandomLetter());
        three.setText(getRandomLetter());
        four.setText(getRandomLetter());
        five.setText(getRandomLetter());
        six.setText(getRandomLetter());
        seven.setText(getRandomLetter());
        eight.setText(getRandomLetter());
        nine.setText(getRandomLetter());
        ten.setText(getRandomLetter());
        eleven.setText(getRandomLetter());
        twelve.setText(getRandomLetter());
        thirteen.setText(getRandomLetter());
        fourteen.setText(getRandomLetter());
        fifteen.setText(getRandomLetter());
        sixteen.setText(getRandomLetter());
    }

    private MaterialButton setMaterialButton(int id) {
        MaterialButton button = view.findViewById(id);
        button.setOnClickListener(this);
        return button;
    }

    private String getRandomLetter() {
        int randNum = (int) ((Math.random() * (90 - 65)) + 65);
        char letter = (char) randNum;
        return String.valueOf(letter);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        System.out.println("Letter: " + buttonText);
    }
}