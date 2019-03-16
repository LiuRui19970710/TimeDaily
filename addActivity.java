package com.example.androidproject3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class addActivity extends AppCompatActivity {

    EditText editTextGoodsName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String date=sdf.format(new java.util.Date());

        TextView date_text=findViewById(R.id.date2);
        date_text.setText(date);
        Button btn=(Button)findViewById(R.id.button_done);
        btn.setOnClickListener(new mClick());
        Button btn2=(Button)findViewById(R.id.button_add);
        btn2.setOnClickListener(new ButtonAddClick());
        Bundle bundle=this.getIntent().getExtras();
        String name= bundle.getString("name");
        editTextGoodsName= (EditText) this.findViewById(R.id.edittext_goods_name);
        editTextGoodsName.setText(name);

}

    private class mClick implements View.OnClickListener
    {
        public void onClick(View v)
        {
            InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editTextGoodsName.getWindowToken(), 0);
            editTextGoodsName.setFocusable(false);
            editTextGoodsName.setFocusable(true);
            editTextGoodsName.setFocusableInTouchMode(true);
            editTextGoodsName.requestFocus();
            editTextGoodsName.findFocus();
        }
    }
    private class ButtonAddClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.putExtra("name",String.valueOf(editTextGoodsName.getText()));
            Log.i("text", String.valueOf(editTextGoodsName.getText()));
            setResult(RESULT_OK,intent);
            addActivity.this.finish();
        }
    }
}

