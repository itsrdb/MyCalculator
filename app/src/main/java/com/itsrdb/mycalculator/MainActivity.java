package com.itsrdb.mycalculator;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void toastMsg(String msg) {

        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();

    }

    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor();
                    else if (eat('/')) x /= parseFactor();
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();

                double x;
                int startPos = this.pos;
                if (eat('(')) {
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor());

                return x;
            }
        }.parse();
    }

        TextView txt1;
        TextView txt2;
        String process, tempString, answer;
        Button btnDel, btnAns, btnAdd, btnDiv, btnPdt, btnSub, btnDot, btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            txt1 = findViewById(R.id.txt1);
            txt2 = findViewById(R.id.txt2);

            btn0 = findViewById(R.id.btn0);
            btn1 = findViewById(R.id.btn1);
            btn2 = findViewById(R.id.btn2);
            btn3 = findViewById(R.id.btn3);
            btn4 = findViewById(R.id.btn4);
            btn5 = findViewById(R.id.btn5);
            btn6 = findViewById(R.id.btn6);
            btn7 = findViewById(R.id.btn7);
            btn8 = findViewById(R.id.btn8);
            btn9 = findViewById(R.id.btn9);

            btnAns = findViewById(R.id.btnAns);
            btnAdd = findViewById(R.id.btnAdd);
            btnDel = findViewById(R.id.btnDel);

            btnDiv = findViewById(R.id.btnDiv);
            btnPdt = findViewById(R.id.btnPdt);
            btnSub = findViewById(R.id.btnSub);
            btnDot = findViewById(R.id.btnDot);

            btnDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (txt1.getText().length() == 0) return;
                    process = txt1.getText().toString();
                    String processNew = process.substring(0, process.length() - 1);
                    txt1.setText(processNew);
                    if(txt1.getText().length() == 0) txt2.setText("");
                }
            });

            btnDel.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ((Vibrator) getSystemService(VIBRATOR_SERVICE)).vibrate(VibrationEffect.createOneShot(50,2));
                    txt1.setText("");
                    txt2.setText("");
                    return false;
                }
            });

            btn0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    process = txt1.getText().toString();
                    if (txt1.getText().length() == 1){
                        if (process.substring(process.length()-1).equals("0")) return;
                    }
                    txt1.setText(process + "0");
                }
            });

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    process = txt1.getText().toString();
                    txt1.setText(process + "1");
                }
            });

            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    process = txt1.getText().toString();
                    txt1.setText(process + "2");
                }
            });

            btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    process = txt1.getText().toString();
                    txt1.setText(process + "3");
                }
            });

            btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    process = txt1.getText().toString();
                    txt1.setText(process + "4");
                }
            });

            btn5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    process = txt1.getText().toString();
                    txt1.setText(process + "5");
                }
            });

            btn6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    process = txt1.getText().toString();
                    txt1.setText(process + "6");
                }
            });

            btn7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    process = txt1.getText().toString();
                    txt1.setText(process + "7");
                }
            });

            btn8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    process = txt1.getText().toString();
                    txt1.setText(process + "8");
                }
            });

            btn9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    process = txt1.getText().toString();
                    txt1.setText(process + "9");
                }
            });

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    process = txt1.getText().toString();
                    if (txt1.getText().length() == 0) return;
                    if (process.substring(process.length()-1).equals("+")) return;
                    if (process.substring(process.length()-1).equals("−")){
                        tempString = process.substring(0, process.length()-1) + "+";
                        txt1.setText(tempString);
                        return;
                    }
                    if (process.substring(process.length()-1).equals("÷")){
                        tempString = process.substring(0, process.length()-1) + "+";
                        txt1.setText(tempString);
                        return;
                    }
                    if (process.substring(process.length()-1).equals("×")){
                        tempString = process.substring(0, process.length()-1) + "+";
                        txt1.setText(tempString);
                        return;
                    }
                    txt1.setText(process + "+");
                }
            });

            btnDiv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    process = txt1.getText().toString();
                    if (txt1.getText().length() == 0) return;
                    if (process.substring(process.length()-1).equals("÷")) return;
                    if (process.substring(process.length()-1).equals("−")){
                        tempString = process.substring(0, process.length()-1) + "÷";
                        txt1.setText(tempString);
                        return;
                    }
                    if (process.substring(process.length()-1).equals("+")){
                        tempString = process.substring(0, process.length()-1) + "÷";
                        txt1.setText(tempString);
                        return;
                    }
                    if (process.substring(process.length()-1).equals("×")){
                        tempString = process.substring(0, process.length()-1) + "÷";
                        txt1.setText(tempString);
                        return;
                    }
                    txt1.setText(process + "÷");
                }
            });

            btnPdt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    process = txt1.getText().toString();
                    if (txt1.getText().length() == 0) return;
                    if (process.substring(process.length()-1).equals("×")) return;
                    if (process.substring(process.length()-1).equals("−")){
                        tempString = process.substring(0, process.length()-1) + "×";
                        txt1.setText(tempString);
                        return;
                    }
                    if (process.substring(process.length()-1).equals("+")){
                        tempString = process.substring(0, process.length()-1) + "×";
                        txt1.setText(tempString);
                        return;
                    }
                    if (process.substring(process.length()-1).equals("÷")){
                        tempString = process.substring(0, process.length()-1) + "×";
                        txt1.setText(tempString);
                        return;
                    }
                    txt1.setText(process + "×");
                }
            });

            btnDot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    process = txt1.getText().toString();
                    if (process.indexOf(".")!=-1) return;
                    if (txt1.getText().length() == 0) return;
                    if (process.substring(process.length()-1).equals(".")) return;
                    txt1.setText(process + ".");
                }
            });

            btnSub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    process = txt1.getText().toString();
                    if (txt1.getText().length() == 0) return;
                    if (process.substring(process.length()-1).equals("−")) return;
                    if (process.substring(process.length()-1).equals("+")){
                        tempString = process.substring(0, process.length()-1) + "−";
                        txt1.setText(tempString);
                        return;
                    }
                    txt1.setText(process + "−");
                }
            });

            btnAns.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    process = txt1.getText().toString();
                    if (txt1.getText().length() == 0) return;
                    if (process.indexOf("E")!=-1) return;
                    if (process.substring(process.length()-1).equals("÷")) return;
                    if (process.substring(process.length()-1).equals("+")) return;
                    if (process.substring(process.length()-1).equals("−")) return;
                    if (process.substring(process.length()-1).equals("×")) return;

                    process = process.replace("×", "*");
                    process = process.replace("÷", "/");
                    process = process.replace("−", "-");

                    answer = Double.toString(MainActivity.eval(process));

                    if(answer.contains(".")){
                        String afterDecimal = answer.substring(answer.indexOf(".")+1, answer.length());
                        if(afterDecimal.equals("0")){
                            answer = answer.substring(0, answer.indexOf("."));
                        }
                    }
                    txt2.setText(txt1.getText());
                    txt1.setText(answer);

                }
            });

        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_favorite) {
                toastMsg("made with love");
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
}
