package com.example.guess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.graphics.Color;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.*;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int number;
    boolean found;
    String res = "";
    EditText edit;
    Button button;
    TextView textView;
    ImageView up_grey;
    ImageView down_grey;
    ImageView up_red;
    ImageView down_red;
    ImageView cry;
    ImageView smile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit = (EditText) findViewById(R.id.box);
        button=findViewById(R.id.button);
        getRandom();
        edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {//EditorInfo.IME_ACTION_SEARCH、EditorInfo.IME_ACTION_SEND等分别对应EditText的imeOptions属性
                    //TODO回车键按下时要执行的操作
                    edit.clearFocus();
                    String str=edit.getText().toString();
                    String regex="[1-9][0-9]{0,3}";
//                    Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
                    if(str.matches(regex)){
                        button.setBackgroundResource(R.drawable.btn_shape_red);
                    }
                    else{
                        button.setBackgroundResource(R.drawable.btn_shape_grey);
                    }

                }
                return false;
            }
        });
    }
    public void getRandom(){
        Random rand = new Random();
        number = rand.nextInt(999) + 1;
    }
    public void function(View view){
        try{
            edit = (EditText) findViewById(R.id.box);
            button=findViewById(R.id.button);
            textView=findViewById(R.id.textView);
            up_grey=findViewById(R.id.up_grey);
            down_grey=findViewById(R.id.down_grey);
            up_red=findViewById(R.id.up_red);
            down_red=findViewById(R.id.down_red);
            cry=findViewById(R.id.cry_face);
            smile=findViewById(R.id.smile_face);
            edit.clearFocus();
            Log.i("box",edit.getText().toString());
            res="it is "+number;
//            Toast.makeText(MainActivity.this, res, Toast.LENGTH_SHORT).show();
            int val = Integer.parseInt(edit.getText().toString().trim());
            if(val < number){
                edit.setBackgroundResource(R.drawable.edit_background_new);
                if(button.getVisibility()==View.VISIBLE){
                    button.setVisibility(View.INVISIBLE);
                    cry.setVisibility(View.VISIBLE);
                    up_grey.setVisibility(View.VISIBLE);
                    down_red.setVisibility(View.VISIBLE);
                    textView.setText(" Lorem ipsum");
                }
                else{
                    button.setVisibility(View.VISIBLE);
                    up_grey.setVisibility(View.INVISIBLE);
                    down_red.setVisibility(View.INVISIBLE);
                    textView.setText(" The computer only considers a number from 1 to 9999");
                    edit.setBackgroundResource(R.drawable.edit_background);
                    cry.setVisibility(View.INVISIBLE);
                }

            } else if(val == number){
                found = true;
                textView.setTextColor(this.getResources().getColor(R.color.colorRed));
                edit.setBackgroundResource(R.drawable.edit_background_new);
                if(button.getVisibility()==View.VISIBLE){
                    textView.setText("You got it!");
                    button.setVisibility(View.INVISIBLE);
                    smile.setVisibility(View.VISIBLE);
                }
                else{
                    setContentView(R.layout.activity_main);
                    getRandom();
                }
            } else {
                edit.setBackgroundResource(R.drawable.edit_background_new);
                if(button.getVisibility()==View.VISIBLE){
                    button.setVisibility(View.INVISIBLE);
                    cry.setVisibility(View.VISIBLE);
                    up_red.setVisibility(View.VISIBLE);
                    down_grey.setVisibility(View.VISIBLE);
                    textView.setText(" Lorem ipsum");
                }
                else{
                    button.setVisibility(View.VISIBLE);
                    up_red.setVisibility(View.INVISIBLE);
                    down_grey.setVisibility(View.INVISIBLE);
                    textView.setText(" The computer only considers a number from 1 to 9999");
                    edit.setBackgroundResource(R.drawable.edit_background);
                    cry.setVisibility(View.INVISIBLE);
                }

            }
        } catch(NumberFormatException e){
            res = "Please Enter a number b/w 1-999";
        }
    }
}