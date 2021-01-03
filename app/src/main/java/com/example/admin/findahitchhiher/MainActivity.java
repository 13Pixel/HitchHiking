package com.example.admin.findahitchhiher;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppDatabase mDb;
    private TextView txt_list;
    private Button button;
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etPassword;
    private Context context = this;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDb = AppActivity.getDatabase();
        txt_list = (TextView) findViewById(R.id.txt_list);

        etFirstName = (EditText) findViewById(R.id.edittext_name);
        etLastName = (EditText) findViewById(R.id.edittext_surname);
        etPassword = (EditText) findViewById(R.id.edittext_password);

        button = (Button) findViewById(R.id.button);
        button.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_save, 0, 0,0);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etFirstName.getText().toString().trim();
                String surname = etLastName.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(surname) || TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(), "Name/Email/Password should not be empty", Toast.LENGTH_SHORT).show();
                }else{
                    Person person = new Person();
                    person.setName(name);
                    person.setSurname(surname);
                    person.setPassword(password);
                    mDb.personDAO().insert(person);
                    Toast.makeText(getApplicationContext(), "Registration successful", Toast.LENGTH_SHORT).show();
                    etFirstName.setText("");
                    etLastName.setText("");
                    etPassword.setText("");
                    etFirstName.requestFocus();
                    getPersonList();
                    Intent intent = new Intent(context, Login_Activity.class);
                    context.startActivity(intent);

                }

            }
        });

    }

    private void getPersonList() {
        txt_list.setText("");
        List<Person> personList = mDb.personDAO().getAllPersons();
        for (Person person : personList){
            txt_list.append(person.getName() + "|||" + person.getSurname() + "|||" + person.getPassword());
            txt_list.append("\n");
        }
    }
}
