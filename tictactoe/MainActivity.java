package com.example.tictactoe;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import TicTacToe.Observer;
import com.example.tictactoe.src.TicTacToe.TicTacModel;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Observer<TicTacModel, String> {

    private TicTacModel model = new TicTacModel();
    private Button[][] ticButtons = new Button[3][3];
    private int BUTTON_FONT_SIZE = 20;
    private ArrayList<String[][]> finalBoards = new ArrayList<>();
    private int ROWS = 3;
    private int COLS = 3;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        model.addObserver(this);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button resetButton = findViewById(R.id.resetButton);

        button1.setOnClickListener(event -> model.select(1));
        button2.setOnClickListener(event -> model.select(2));
        button3.setOnClickListener(event -> model.select(3));
        button4.setOnClickListener(event -> model.select(4));
        button5.setOnClickListener(event -> model.select(5));
        button6.setOnClickListener(event -> model.select(6));
        button7.setOnClickListener(event -> model.select(7));
        button8.setOnClickListener(event -> model.select(8));
        button9.setOnClickListener(event -> model.select(9));
        resetButton.setOnClickListener(event -> model.reset());

        ticButtons[0][0] = button1;
        ticButtons[0][1] = button2;
        ticButtons[0][2] = button3;
        ticButtons[1][0] = button4;
        ticButtons[1][1] = button5;
        ticButtons[1][2] = button6;
        ticButtons[2][0] = button7;
        ticButtons[2][1] = button8;
        ticButtons[2][2] = button9;
    }

    public void updateView(String[][] board, String message){
        TextView text = findViewById(R.id.message);
        text.setText(message);
        for (int row = 0; row < ROWS; ++row){
            for (int col = 0; col < COLS; ++col){
                String ticID = board[row][col];
                ticButtons[row][col].setTextSize(BUTTON_FONT_SIZE);
                ticButtons[row][col].setText(ticID);
                ticButtons[row][col].setTextColor(Color.WHITE);
            }
        }
    }

    @Override
    public void update(TicTacModel model, String data) {
        updateView(model.getBoard(), data);
    }
}