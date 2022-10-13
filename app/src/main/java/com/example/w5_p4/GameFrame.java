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
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameFrame#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameFrame extends Fragment implements View.OnClickListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    int score;
    View view;
    TextView input;
    gameListener listener;
    MaterialButton lastClicked;
    StringBuilder preserve;
    Button clear, submit;
    BufferedReader reader;
    MaterialButton one, two, three, four;
    MaterialButton five, six, seven, eight;
    MaterialButton nine, ten, eleven, twelve;
    MaterialButton thirteen, fourteen, fifteen, sixteen;
    ArrayList<MaterialButton> allButtons = new ArrayList<>();
    HashSet<String> answered = new HashSet<>();
    HashSet<String> dictionary = new HashSet<>();
    private final String[] BOGGLE_Board = {
            "LRYTTE", "VTHRWE", "EGHWNE", "SEOTIS",
            "ANAEEG", "IDSYTT", "OATTOW", "MTOICU",
            "AFPKFS", "XLDERI", "HCPOAS", "ENSIEU",
            "YLDEVR", "ZNRNHL", "NMIQHU", "OBBAOJ"
    };

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
        input = view.findViewById(R.id.input);
        preserve = new StringBuilder();
        initiateMaterialButtons();
        setRandomLetter();
        lastClicked = null;
        score = 0;
        clear.setOnClickListener(view -> clear());
        submit.setOnClickListener(view -> submit());
        loadDictionary();
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

    public interface gameListener {
        void updateScore(int score);
    }

    private void setRandomLetter() {
        for (int i = 0; i < allButtons.size(); i++) {
            MaterialButton button = allButtons.get(i);
            button.setText(getRandomLetter(i));
        }
    }

    private MaterialButton setMaterialButton(int id) {
        MaterialButton button = view.findViewById(id);
        button.setOnClickListener(this);
        allButtons.add(button);
        return button;
    }

    private String getRandomLetter(int index) {
        int num = (int) ((Math.random() * 5));
        String temp = BOGGLE_Board[index];
        return String.valueOf(temp.charAt(num));
    }

    private boolean locationCheck(MaterialButton current) {
        int currentIndex = allButtons.indexOf(current);
        int lastIndex = allButtons.indexOf(lastClicked);
        int lastX = lastIndex % 4;
        int lastY = lastIndex / 4;
        int currentX = currentIndex % 4;
        int currentY = currentIndex / 4;
        return currentIndex != lastIndex && Math.abs(lastX - currentX) <= 1
                && Math.abs(lastY - currentY) <= 1;
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        if (lastClicked == null) {
            operations(button);
        } else {
            if (locationCheck(button)) {
                operations(button);
            } else {
                Toast.makeText(this.getContext(), "You may only select connected letters",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void operations(MaterialButton button) {
        lastClicked = button;
        button.setEnabled(false);
        preserve.append(button.getText().toString());
        input.setText(preserve.toString());
    }

    private void clear() {
        preserve.setLength(0);
        input.setText(getResources().getString(R.string.word_shows_here));
        for (int i = 0; i < allButtons.size(); i++) {
            allButtons.get(i).setEnabled(true);
        }
        lastClicked = null;
    }

    private void loadDictionary() {
        try {
            reader = new BufferedReader(new InputStreamReader(
                    requireContext().getAssets().open(
                            "dictionary.txt"), StandardCharsets.UTF_8));
            String word;
            while ((word = reader.readLine()) != null) {
                dictionary.add(word.toLowerCase());
            }
        } catch (IOException e) {
            System.out.println("Cannot open dictionary.txt");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    System.out.println("Cannot close reader");
                }
            }
        }
    }

    private boolean wordCheck(String word) {
        // Length check
        if (word.length() < 4) {
            Toast.makeText(this.getContext(), "Word must has 4 letters.", Toast.LENGTH_SHORT).show();
            return false;
        }
        // Two vowels check
        int vowelCount = 0;
        if (word.contains("a"))
            vowelCount++;
        if (word.contains("e"))
            vowelCount++;
        if (word.contains("i"))
            vowelCount++;
        if (word.contains("o"))
            vowelCount++;
        if (word.contains("u"))
            vowelCount++;
        if (vowelCount >= 2)
            return true;
        else {
            Toast.makeText(this.getContext(), "Word must has 2 vowels.", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private int checkPoints(String word) {
        int tempScore = 0;
        char tempChar;
        boolean doublePoints = false;
        for (int i = 0; i < word.length(); i++) {
            tempChar = word.charAt(i);
            if (tempChar == 'a' || tempChar == 'e' || tempChar == 'i' || tempChar == 'o' || tempChar == 'u') {
                tempScore += 5;
            } else {
                tempScore += 1;
                if (tempChar == 's' || tempChar == 'z' || tempChar == 'p' || tempChar == 'x' || tempChar == 'q') {
                    doublePoints = true;
                }
            }
        }
        if (doublePoints)
            tempScore *= 2;
        Toast.makeText(this.getContext(), "That's correct, +" + tempScore + " points.", Toast.LENGTH_SHORT).show();
        return tempScore;
    }

    private void submit() {
        String word = input.getText().toString().toLowerCase();
        if (wordCheck(word)) {
            if (!answered.contains(word)) {
                if (dictionary.contains(word)) {
                    answered.add(word);
                    score += checkPoints(word);
                } else {
                    Toast.makeText(this.getContext(), "That's incorrect, -10 points.", Toast.LENGTH_SHORT).show();
                    score -= 10;
                }
                listener.updateScore(score);
            } else {
                Toast.makeText(this.getContext(), "Repeated Word.", Toast.LENGTH_SHORT).show();
            }
        }
        clear();
    }

    public void newGame() {
        setRandomLetter();
        clear();
        score = 0;
        listener.updateScore(score);
        answered.clear();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof gameListener)
            listener = (gameListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}