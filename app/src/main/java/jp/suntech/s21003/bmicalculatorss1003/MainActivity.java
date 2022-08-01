package jp.suntech.s21003.bmicalculatorss1003;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button execCalcBMI;
    private EditText input_H;
    private EditText input_W;
    private EditText input_O;
    private TextView bmi_result;
    private TextView bmi_better;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        execCalcBMI =findViewById(R.id.btCalculate);
        input_H=findViewById(R.id.etHeight);
        input_W=findViewById(R.id.etWeight);
        input_O=findViewById(R.id.etOld);
        bmi_result=findViewById(R.id.tvFat);
        bmi_better =findViewById(R.id.tvBetter);



        execCalcBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    try{
                    double input_H_double = Double.valueOf(input_H.getText().toString());
                    double input_W_double = Double.valueOf(input_W.getText().toString());

                    double BMI = calcBMI(input_H_double, input_W_double);
                    float old_f=   Float.parseFloat(input_O.getText().toString());
                     TextView kgram =findViewById(R.id.tvWeight3);


                    String result = judge(calcBMI(input_H_double, input_W_double));
                    bmi_result.setText(result);
                    String bmiB = String.format("%.1f", BetterF(input_H_double));
                    bmi_better.setText(bmiB);

                   kgram.setText(R.string.tv_weight2);

                    if(old_f <16){
                        DialogFragment dialogFragment=new DialogFragment();
                        dialogFragment.show(getSupportFragmentManager(),"DialogFragment");
                    }
                    }catch (NumberFormatException e){
                        System.out.println("空白です");
                    }

            }
        });
        Button btClear =findViewById(R.id.btClear);
        ButtonClickListener listener =new ButtonClickListener();
        btClear.setOnClickListener(listener);


    }


    private class ButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            EditText old =findViewById(R.id.etOld);
            EditText weight =findViewById(R.id.etWeight);
            EditText height =findViewById(R.id.etHeight);
            TextView fat =findViewById(R.id.tvFat);
            TextView fatG=findViewById(R.id.tvBetter);
            TextView kg=findViewById(R.id.tvWeight3);

            old.setText("");
            weight.setText("");
            height.setText("");
            fat.setText("");
            fatG.setText("");
            kg.setText("");


            
        }
    }
    private double calcBMI(double h,double w){
        double bmi =0;
        if(w>0&&h>0){
            h =h/100;
            bmi =w/Math.pow(h,2);
        }
        return bmi;
    }
    private String judge(double BMI){
        String message="";
        if (BMI<18.5){
            message ="低体重(痩せ型)";
        }else if(BMI>=18.5&&BMI<25){
            message ="普通体重";
        }else if(BMI>=25&&BMI<30){
            message ="肥満(1度)";
        }else if(BMI>=30&&BMI<45){
            message="肥満(2度)";
        }else if(BMI>=35&&BMI<40){
            message="肥満(3度)";
        }else if(BMI>=40){
            message="肥満(4度)";

        }
        return  message;
    }
    private double BetterF(double h){
        double betterW = 0;
        if(h>0) {
            h = h / 100;
            betterW = 22 * Math.pow(h, 2);
        }
        return  betterW;
    }



}