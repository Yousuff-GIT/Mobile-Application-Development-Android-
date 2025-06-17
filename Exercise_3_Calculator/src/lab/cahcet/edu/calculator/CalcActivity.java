// CalcActivity.java
package lab.cahcet.edu.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalcActivity extends AppCompatActivity {
    EditText number1, number2;
    TextView result;
    double num1, num2, output;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
    }

    public void onclickOperators(View operatorview) {
        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);

        if (number1.getText().toString().equals("") || number2.getText().toString().equals("")) {
            Toast.makeText(getBaseContext(), "Invalid Inputs", Toast.LENGTH_LONG).show();
            flag = true;
        } else {
            flag = false;
            num1 = Double.parseDouble(number1.getText().toString());
            num2 = Double.parseDouble(number2.getText().toString());
        }

        result = findViewById(R.id.output);
        if (flag) return;

        int id = operatorview.getId();
        if (id == R.id.btn_plus) output = num1 + num2;
        else if (id == R.id.btn_minus) output = num1 - num2;
        else if (id == R.id.btn_mul) output = num1 * num2;
        else if (id == R.id.btn_div) output = num1 / num2;
        else if (id == R.id.btn_mod) output = num1 % num2;
        else if (id == R.id.btn_ac) {
            number1.setText("");
            number2.setText("");
            result.setText("");
            return;
        }
        result.setText(String.valueOf(output));
    }
}
