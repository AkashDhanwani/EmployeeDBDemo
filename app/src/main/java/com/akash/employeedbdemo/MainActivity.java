package com.akash.employeedbdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etId,etName;
    Button btnGet,btnAdd,btnDelete,btnUpdate;
    TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etId = (EditText) findViewById(R.id.etId);
        etName = (EditText) findViewById(R.id.etName);
        btnGet = (Button) findViewById(R.id.btnGet);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        tvData = (TextView) findViewById(R.id.tvData);

        final DataBaseHandler db = new DataBaseHandler(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = etId.getText().toString();
                String name = etName.getText().toString();

                if(id.length() == 0 || name.length() == 0 )
                {
                    Toast.makeText(MainActivity.this, "Please enter name and id", Toast.LENGTH_SHORT).show();
                    return;
                }
                db.addRecord(Integer.parseInt(id),name);
                etId.setText("");etName.setText("");
                etId.requestFocus();
            }
        });

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String data = db.getRecord();
                tvData.setText(data);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = etId.getText().toString();

                if(id.length() == 0)
                {
                    etId.setError("id Empty");
                    etId.requestFocus();
                    return;
                }
                db.removeRecord(Integer.parseInt(id));
                etId.setText("");
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = etId.getText().toString();
                String name = etName.getText().toString();

                if(id.length() == 0)
                {
                    Toast.makeText(MainActivity.this, "Please enter id", Toast.LENGTH_SHORT).show();
                    return;
                }
                db.modifyRecord(Integer.parseInt(id),name);
                etId.setText("");etName.setText("");
                etId.requestFocus();
            }
        });
    }
}
