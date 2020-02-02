package besmak1.vocap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/*
Activity used to login
Take the name and the password of the user
 */
public class Login extends AppCompatActivity  {

    // à implémenter dans la version V2 --> gestion du comptre utilisatuer
    private EditText mEditTextName;
    private EditText mEditTextPassword;

    private Button mButtonConnection;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEditTextName = findViewById(R.id.EV_login_name);
        mEditTextPassword = findViewById(R.id.EV_login_password);
        mButtonConnection = findViewById(R.id.B_connection_login);

        mButtonConnection.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent audio = new Intent(Login.this, Audio_description.class);
                startActivity(audio);
            }
        });

        mEditTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //mButtonConnection.setEnabled(s.toString().length() != 0);
                //utiliser quand la verison 2 de l'application sera initié
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
