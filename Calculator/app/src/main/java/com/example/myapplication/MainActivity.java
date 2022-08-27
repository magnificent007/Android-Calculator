package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.icu.math.BigDecimal;

import android.text.method.ScrollingMovementMethod;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView res_showNum;
    private String showText = "";
    private String first_num = "";
    private String next_num = "";
    private String other_num = "";
    private String result = "";
    private String operate = "";
    private String judge = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_9 = findViewById(R.id.btn_9);
        Button btn_8 = findViewById(R.id.btn_8);
        Button btn_7 = findViewById(R.id.btn_7);
        Button btn_6 = findViewById(R.id.btn_6);
        Button btn_5 = findViewById(R.id.btn_5);
        Button btn_4 = findViewById(R.id.btn_4);
        Button btn_3 = findViewById(R.id.btn_3);
        Button btn_2 = findViewById(R.id.btn_2);
        Button btn_1 = findViewById(R.id.btn_1);
        Button btn_0 = findViewById(R.id.btn_0);
        Button btn_add = findViewById(R.id.btn_add);
        Button btn_reduce = findViewById(R.id.btn_reduce);
        Button btn_squareroot = findViewById(R.id.btn_squareroot);
        Button btn_point = findViewById(R.id.btn_point);
        Button btn_C = findViewById(R.id.btn_C);
        Button btn_CE = findViewById(R.id.btn_CE);
        Button btn_equal = findViewById(R.id.btn_equal);
        Button btn_divide = findViewById(R.id.btn_divide);
        Button btn_multiple = findViewById(R.id.btn_multiple);
        Button btn_sin = findViewById(R.id.btn_sin);
        Button btn_cos = findViewById(R.id.btn_cos);
        Button btn_tan = findViewById(R.id.btn_tan);
        Button btn_pi = findViewById(R.id.btn_pi);
        Button btn_fai = findViewById(R.id.btn_fai);
        res_showNum = findViewById(R.id.res_showNum);

        btn_9.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_0.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_reduce.setOnClickListener(this);
        btn_squareroot.setOnClickListener(this);
        btn_C.setOnClickListener(this);
        btn_CE.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_multiple.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        btn_sin.setOnClickListener(this);
        btn_cos.setOnClickListener(this);
        btn_tan.setOnClickListener(this);
        btn_pi.setOnClickListener(this);
        btn_fai.setOnClickListener(this);
        res_showNum.setMovementMethod(new ScrollingMovementMethod());
        res_showNum.setGravity(Gravity.RIGHT|Gravity.BOTTOM);
    }

    // 计算
    @RequiresApi(api = Build.VERSION_CODES.N)
    private boolean compute(){
        if(operate.equals("＋")){
            result = String.valueOf(new BigDecimal(first_num).add( new BigDecimal(next_num)));
        }else if(operate.equals("－")){
            result = String.valueOf(new BigDecimal(first_num).subtract(new BigDecimal(next_num)));
        }else if(operate.equals("×")){
            result = String.valueOf(new BigDecimal(first_num).multiply(new BigDecimal(next_num)));
        }else if(operate.equals("÷")){
            if(next_num.equals("0")){
                Toast.makeText(this,"被除数不能为0",Toast.LENGTH_SHORT).show();
                first_num = "";
                next_num = "";
                operate = "";
                return false;
            }else{
                result = String.valueOf(new BigDecimal(String.valueOf(Double.parseDouble(first_num))).divide(new BigDecimal(next_num)));
            }
        }
        first_num = "";
        next_num = "";
        operate = "";
        return true;
    }

    // 阶乘
    @RequiresApi(api = Build.VERSION_CODES.N)
    private BigDecimal factBig(int n){
        if(n == 1 || n == 0){
            return BigDecimal.valueOf(1);
        }else{
            return BigDecimal.valueOf(n).multiply(factBig(n - 1));
        }
    }

    // 转化阶乘数为整数
    private int validateNumber(String str) {
        return str.indexOf(".");
    }

    // 清空
    private void clear(){
        showText = "";
        res_showNum.setText(showText);
        operate = "";
        first_num = "";
        next_num = "";
        result = "";
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        int resID = v.getId();
        String inputText;
        String expression = "";
        List<String> datelist2 = new ArrayList<String>();
        inputText = ((TextView)v).getText().toString();
        String sc = "1--1";
        if(resID == R.id.btn_equal){
            expression = res_showNum.getText().toString();
            List<String> datelist1 = new ArrayList<String>();
            for (String str: expression.split("[\\＋\\－\\×\\÷]")){
                datelist1.add(str);
            }
            if (datelist1.size() >= 2){
                next_num = datelist1.get(datelist1.size()-1);
                first_num = datelist1.get(datelist1.size()-2);
            }else{
                Toast.makeText(this, "缺少操作符或数字", Toast.LENGTH_SHORT).show();
                return ;
            }
            Log.d(sc, first_num);
            Log.d(sc+"2", next_num);
            operate = String.valueOf(expression.charAt(expression.indexOf(datelist1.get(datelist1.size()-2))+datelist1.get(datelist1.size()-2).length()));
            compute();
            showText = result;
            res_showNum.setText(showText);
            datelist1.clear();
        }else if(resID == R.id.btn_add || resID == R.id.btn_reduce || resID == R.id.btn_multiple || resID == R.id.btn_divide){
            expression = res_showNum.getText().toString();
            for (String str: expression.split("[\\＋\\－\\×\\÷]")){
                datelist2.add(str);
            }
            Log.d(sc+"3", expression);
            if (expression.equals("")){
                Toast.makeText(this, "请先输入数字", Toast.LENGTH_SHORT).show();
                datelist2.clear();
            }else if (datelist2.size() >= 2){// 连加 乘 除呢 如何进入判断？？？
                next_num = datelist2.get(datelist2.size()-1);
//                if (datelist2.size() > 2){
//                    first_num = "-"+datelist2.get(datelist2.size()-2);
//                }else if(datelist2.size() == 2){
//                    first_num = datelist2.get(datelist2.size()-2);
//                }
                first_num = datelist2.get(datelist2.size()-2);
                operate = String.valueOf(expression.charAt(expression.indexOf(datelist2.get(datelist2.size()-2))+datelist2.get(datelist2.size()-2).length()));
                Log.d(sc+"4", operate);
                compute();
                showText = result;
                showText = showText + inputText;
                res_showNum.setText(showText);
                datelist2.clear();
            }else{
                showText = showText + inputText;
                res_showNum.setText(showText);
                datelist2.clear();
            }
        }else if (resID == R.id.btn_CE){
            clear();
        }else if (resID == R.id.btn_C){
            expression = res_showNum.getText().toString();
            if (expression.equals("")){
                Toast.makeText(this, "无可删除数字", Toast.LENGTH_SHORT).show();
            }else {
                if (expression.length() == 1){
                    expression = "";
                }else{
                    expression = expression.substring(0, expression.length()-1);
                }
                showText = expression;
                res_showNum.setText(showText);
            }
        }else if (resID == R.id.btn_pi){
            expression = res_showNum.getText().toString();
            showText = expression;
            if (expression.substring(expression.length()-1).equals("＋") || expression.substring(expression.length()-1).equals("－") ||
                    expression.substring(expression.length()-1).equals("×") || expression.substring(expression.length()-1).equals("÷")){
                showText = showText + String.valueOf(new BigDecimal(Math.PI).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue());
                res_showNum.setText(showText);
            }else{
                showText = String.valueOf(new BigDecimal(Math.PI).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue());
                res_showNum.setText(showText);
            }
        }else if (resID == R.id.btn_sin || resID == R.id.btn_cos || resID == R.id.btn_tan || resID == R.id.btn_fai || resID == R.id.btn_squareroot){
            expression = res_showNum.getText().toString();
            showText = expression;
            if(expression.equals("")){
                Toast.makeText(this, "请先输入数字", Toast.LENGTH_SHORT).show();
            }else{
                for (String str: expression.split("[\\＋\\－\\×\\÷]")){
                    datelist2.add(str);
                }
                if (datelist2.size() >= 1){
                    Double angle = Math.toRadians(Double.parseDouble(datelist2.get(datelist2.size()-1)));
                    if (resID == R.id.btn_sin){
                        showText = String.valueOf(new BigDecimal(Math.sin(angle)).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue());
                    }else if (resID == R.id.btn_cos){
                        showText = String.valueOf(new BigDecimal(Math.cos(angle)).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue());
                    }else if (resID == R.id.btn_tan){
                        showText = String.valueOf(new BigDecimal(Math.tan(angle)).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue());
                    }else if (resID == R.id.btn_fai){
                        showText = String.valueOf(factBig(Integer.valueOf(datelist2.get(datelist2.size()-1))));
                    }else if (resID == R.id.btn_squareroot){
                        showText = String.valueOf(Math.sqrt(Double.parseDouble(datelist2.get(datelist2.size()-1))));
                    }
                    res_showNum.setText(showText);
                }else{
                    Toast.makeText(this, "请先输入数字", Toast.LENGTH_SHORT).show();
                }
            }
        }else {
            expression = res_showNum.getText().toString();
            showText = expression;
            if (expression.equals("")){
                showText = inputText;
                res_showNum.setText(showText);
            }else if (expression.substring(expression.length()-1).equals("＋") || expression.substring(expression.length()-1).equals("－") ||
                    expression.substring(expression.length()-1).equals("×") || expression.substring(expression.length()-1).equals("÷")){
                showText = showText + inputText;
                res_showNum.setText(showText);
            }else{
                showText = showText + inputText;
                res_showNum.setText(showText);
            }
        }
    }
}