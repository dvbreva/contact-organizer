package com.example.contactsorganizer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import Database.DbHelper;
import Models.Contact;

public class EditContact extends AppCompatActivity {
    private EditText editTextName;
    private EditText editTextPhone;
    private EditText editTextDescription;
    private Button buttonBack;
    private Button buttonSave;
    String category;
    Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        Intent intent = getIntent();
        final Contact contact = (Contact) intent.getSerializableExtra("contact");
        this.editTextName = (EditText) findViewById(R.id.editTextName);
        this.editTextName.setText(contact.getName());

        this.editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        this.editTextPhone.setText(contact.getPhone());

        this.editTextDescription = (EditText) findViewById(R.id.editTextDescription);
        this.editTextDescription.setText(contact.getDescription());

        // spinner
        ArrayList<String> categoryList =  new ArrayList<String>();
        categoryList.add("Work");
        categoryList.add("Family");
        categoryList.add("Friends");
        categoryList.add("Colleagues");


        sp = (Spinner) findViewById(R.id.spinner_category);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_layout,R.id.spinnerText, categoryList);
        sp.setAdapter(adapter);

        // get the value out of the db and select it into the spinner
        String categoryValue = contact.getCategory();
        int spinnerPosition = adapter.getPosition(categoryValue);
        sp.setSelection(spinnerPosition);


        this.buttonBack = (Button) findViewById(R.id.buttonBack);
        this.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(EditContact.this, MainActivity.class);
                startActivity(intent1);
            }
        });

        this.buttonSave = (Button) findViewById(R.id.buttonSave);
        this.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper dbHelper = new DbHelper(getBaseContext());
                contact.setName(editTextName.getText().toString());
                contact.setPhone(editTextPhone.getText().toString());
                contact.setDescription(editTextDescription.getText().toString());

                //set the value chosen from the spinner
                category= sp.getSelectedItem().toString();
                contact.setCategory(category);


                if (dbHelper.update(contact)){
                    Intent intent1 = new Intent(EditContact.this, MainActivity.class);
                    startActivity(intent1);

                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setCancelable(false);
                    builder.setMessage("Fail");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    builder.create().show();
                }
            }
        });

    }
}