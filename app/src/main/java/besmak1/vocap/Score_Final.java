package besmak1.vocap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Score_Final extends AppCompatActivity {

    private Button replay;
    private Button home;


    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);

        replay =  findViewById(R.id.bouton_jouer);
        home =  findViewById(R.id.bouton_param);

        replay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent audio_description = new Intent(Score_Final.this, Audio_description.class);
                startActivity(audio_description);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent acceuil = new Intent(Score_Final.this, Acceuil.class);
                startActivity(acceuil);
            }
        });
    }
}
