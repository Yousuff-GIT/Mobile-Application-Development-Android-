// Multithreading.java
// This Android application demonstrates multi-threading by computing
// Fibonacci, Cube, and Square number series using separate threads.

package lab.cahcet.edu.numberseries;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class Multithreading extends AppCompatActivity {
    private Button fibo, cube, square;
    private ProgressBar fibobar, cubebar, squarebar;
    private EditText number;
    private TextView fiboresult, cuberesult, squareresult;
    boolean flag = true;
    int limit = 0;
    String fiboseries = "0", cubeseries = "0", squareseries = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multithreading);

        // Initialize UI components
        fibo = findViewById(R.id.fibo_btn);
        fibobar = findViewById(R.id.fiboprogress);
        fiboresult = findViewById(R.id.fibo_result);
        cube = findViewById(R.id.cube_btn);
        cubebar = findViewById(R.id.cubeprogress);
        cuberesult = findViewById(R.id.cube_result);
        square = findViewById(R.id.square_btn);
        squarebar = findViewById(R.id.squareprogress);
        squareresult = findViewById(R.id.square_result);
        number = findViewById(R.id.number_limit);
    }

    Handler fibohandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            fiboresult.setText("Fibonacci Series: " + fiboseries);
            fiboseries = "";
            number.setEnabled(true);
        }
    };

    Handler cubehandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            cuberesult.setText("Cube Series: " + cubeseries);
            cubeseries = "";
        }
    };

    Handler squarehandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            squareresult.setText("Square Series: " + squareseries);
            squareseries = "";
        }
    };

    public void fibonacciSeries(View view) {
        if (number.getText().toString().equals("")) {
            Toast.makeText(getBaseContext(), "Invalid Inputs", Toast.LENGTH_SHORT).show();
        } else {
            fiboresult.setText("");
            number.setEnabled(false);
            limit = Integer.parseInt(number.getText().toString());
            fibobar.setMax(limit);
            fibobar.setProgress(0);

            Runnable fibotask = () -> {
                int f1, f2 = 0, f3 = 1;
                for (int i = 1; i <= limit; i++) {
                    fiboseries += " " + f3 + " ";
                    f1 = f2;
                    f2 = f3;
                    f3 = f1 + f2;
                    synchronized (this) {
                        try {
                            wait(1000);
                            fibobar.setProgress(i);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                fibohandler.sendEmptyMessage(0);
            };
            new Thread(fibotask).start();
        }
    }

    public void cubeSeries(View view) {
        if (number.getText().toString().equals("")) {
            Toast.makeText(getBaseContext(), "Invalid Inputs", Toast.LENGTH_SHORT).show();
        } else {
            cuberesult.setText("");
            number.setEnabled(false);
            limit = Integer.parseInt(number.getText().toString());
            cubebar.setMax(limit);
            cubebar.setProgress(0);

            Runnable cubetask = () -> {
                for (int i = 1; i <= limit; i++) {
                    int tempcube = i * i * i;
                    cubeseries += " " + tempcube + " ";
                    synchronized (this) {
                        try {
                            wait(500);
                            cubebar.setProgress(i);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                cubehandler.sendEmptyMessage(0);
            };
            new Thread(cubetask).start();
        }
    }

    public void squareSeries(View view) {
        if (number.getText().toString().equals("")) {
            Toast.makeText(getBaseContext(), "Invalid Inputs", Toast.LENGTH_SHORT).show();
        } else {
            squareresult.setText("");
            number.setEnabled(false);
            limit = Integer.parseInt(number.getText().toString());
            squarebar.setMax(limit);
            squarebar.setProgress(0);

            Runnable squaretask = () -> {
                for (int i = 1; i <= limit; i++) {
                    int tempsquare = i * i;
                    squareseries += " " + tempsquare + " ";
                    synchronized (this) {
                        try {
                            wait(250);
                            squarebar.setProgress(i);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                squarehandler.sendEmptyMessage(0);
            };
            new Thread(squaretask).start();
        }
    }
}