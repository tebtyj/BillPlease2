package sg.edu.rp.c346.id22011050.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    EditText amount;
    EditText NumOfPax;
    ToggleButton tbSVS;
    ToggleButton tbGST;
    EditText discount;
    RadioGroup rgpayment;
    Button btsplit;
    Button btreset;
    TextView totalBill;
    TextView eachPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.editAmount);
        NumOfPax = findViewById(R.id.editNumber);
        tbSVS = findViewById(R.id.toggleButtonSVS);
        tbGST = findViewById(R.id.toggleButtonGST);
        discount = findViewById(R.id.editDiscount);
        rgpayment =findViewById(R.id.radioGroupPayment);
        btsplit = findViewById(R.id.buttonSplit);
        btreset = findViewById(R.id.buttonReset);
        totalBill = findViewById(R.id.displayBill);
        eachPay = findViewById(R.id.displayEachPay);

        btsplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code for the action
                double totalAmt=0.0;
                int getAmt = amount.getText().toString().trim().length();
                int getNum = NumOfPax.getText().toString().trim().length();
                int getDisc = discount.getText().toString().trim().length();
                if (getAmt != 0 && getNum !=0)
                {
                    if(!tbSVS.isChecked() && (!tbGST.isChecked())) //if both are not checked
                    {
                        totalAmt = Double.parseDouble(amount.getText().toString());
                    } else if ((!tbSVS.isChecked() && (tbGST.isChecked())))
                    {
                        totalAmt = Double.parseDouble(amount.getText().toString()) *1.07;
                    }else if (tbSVS.isChecked() && (!tbGST.isChecked()))
                    {
                        totalAmt = Double.parseDouble(amount.getText().toString())* 1.10;
                    }else if (tbSVS.isChecked() && (tbGST.isChecked()))
                    {
                        totalAmt = Double.parseDouble(amount.getText().toString())*1.17;
                    }

                }
                if (getDisc != 0)
                {
                    double disc = Double.parseDouble(discount.getText().toString()) * totalAmt;
                    totalAmt = totalAmt - (disc/100);
                }
                String newAmt = String.format("%.2f",totalAmt);

                int getPax = Integer.parseInt(NumOfPax.getText().toString());
                if (getPax != 1)
                {
                    int checkedRadioButtonId = rgpayment.getCheckedRadioButtonId();
                    if (checkedRadioButtonId == R.id.radioButtonCash)
                    {
                        totalBill.setText("Total Bill: $" + newAmt);
                        eachPay.setText("Each Pays: $"+ String.format("%.2f",totalAmt/getPax));
                    }else
                    {
                        totalBill.setText("Total Bill: $" + newAmt);
                        eachPay.setText("Each Pays: $"+newAmt +" via Paynow to 9123 4567");
                    }
                }

            }
        });
        btreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code for the action
                amount.setText("");
                NumOfPax.setText("");
                tbSVS.setChecked(false);
                tbGST.setChecked(false);
                discount.setText("");
                totalBill.setText("");
                eachPay.setText("");
            }
        });
    }
}