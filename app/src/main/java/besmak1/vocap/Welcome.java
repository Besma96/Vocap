package besmak1.vocap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Welcome extends AppCompatActivity {
    private Button mButtonGameWorld;
    private Button mButtonUserWorld;
    private Button mButtonToolWorld;


    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mButtonGameWorld =  findViewById(R.id.B_game_world);
        mButtonUserWorld =  findViewById(R.id.B_own_world);
        mButtonToolWorld =  findViewById(R.id.B_tool_world);

        mButtonGameWorld.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent audio = new Intent(Welcome.this, GameOne.class);
                startActivity(audio);
            }
        });

        mButtonUserWorld.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // a implemeter pendan tla version 2 de la'application
                // charger l'utilsateiion d'un compte solo sur et crerr avatar de facon automatqiue dna sla verison 3
            }

        });

        mButtonToolWorld.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // à implémenter : aller sur la page parametres ( nom du joueur, avatar, age, humeur)
                // utiliser User pour cette activitée
            }
        });
    }

    // Pas sur que cela serve vraiment...
    // menu peut etre pas necesaire si retour à la wlecome page a chauqe fois qu'on veut changer la navigation
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() ==0){
            Intent acceuil = new Intent(Welcome.this, Welcome.class);
            startActivity(acceuil);
        }else{
            Intent main = new Intent(Welcome.this, Login.class);
            startActivity(main);
        }
        return true;
    }
*/
}
