package com.example.admin.findahitchhiher;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.List;

public class Login_Activity extends AppCompatActivity implements Serializable{

    private Button login;
    private Button register;
    private EditText email;
    private EditText password;
    private Context context = this;
    private AppDatabase mDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        mDb = AppActivity.getDatabase();

        login = (Button) findViewById(R.id.button_login);
        register = (Button) findViewById(R.id.button_registration);

        email = (EditText) findViewById(R.id.login_email);
        password = (EditText) findViewById(R.id.login_password);

        register.setOnClickListener(startLoginActivity);
        login.setOnClickListener(openMapsActivity);
    }

    View.OnClickListener startLoginActivity = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
        }
    };
    View.OnClickListener openMapsActivity = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String passwordText = password.getText().toString();
            String emailText = email.getText().toString();


            List<Person> personList = mDb.personDAO().getAllPersons();
            for (Person person : personList){
                System.out.println(person.getName() + "|||" + person.getSurname() + "|||" + person.getPassword());
                if(passwordText.equals(person.getPassword()) && emailText.equals(person.getSurname())){
                    runSecondActivity(person);
                }
            }

        }
    };

    public void runSecondActivity(Person person){
        Intent intent = new Intent(context, Main2Activity.class);
        intent.putExtra("CurrentPerson", (Serializable) person);
        context.startActivity(intent);
    }
}
