package besmak1.vocap;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.graphics.Color;

public class Acceuil extends AppCompatActivity {
    private Button mButtonJouer;
    private Button mButtonParam;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);

        mButtonJouer = (Button) findViewById(R.id.bouton_jouer);
        mButtonParam = (Button) findViewById(R.id.bouton_param);

        mButtonJouer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent audio = new Intent(Acceuil.this, Audio_description.class);
                startActivity(audio);
            }
        });

        mButtonParam.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // à implémenter : aller sur la page parametres ( nom du joueur, avatar, age, humeur)
                // utiliser User pour cette activitée
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation, menu);
        return true;
    }
}
