package besmak1.vocap;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Audio_description extends AppCompatActivity {
    private TextView nom_image;
    private int currentimage = 0;
    private ImageView image;
    private Button ecoute;
    private String retour;
    private TextToSpeech mTTs;
    private String nameCurrentImage;
    private List<String> level;
    private int nbdessai;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() ==0){
            Intent acceuil = new Intent(Audio_description.this, Acceuil.class);
            startActivity(acceuil);
        }else{
            Intent main = new Intent(Audio_description.this, MainActivity.class);
            startActivity(main);
        }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_description);

        image = findViewById(R.id.image);
        nom_image = findViewById(R.id.nom_image);

        ecoute = findViewById(R.id.bouton_ecoute);
        Button parler = findViewById(R.id.bouton_parler);

        level = new ArrayList<>();
        level.add("D1");
        level.add("D2");
        level.add("D3");

        retour = "";
        nbdessai = 0;
        nameCurrentImage = DisplayreadLevel(level.get(1), currentimage);
        mTTs = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTs.setLanguage(Locale.FRANCE);

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Langugae not supported");

                    } else {
                        ecoute.setEnabled(true);
                    }
                } else {
                    Log.e("TTs", "Initialization failed");
                }
            }
        });

        ecoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });

        parler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSpeechInput(v);
                nbdessai ++;
            }
        });
    }

    public void speak() {
        String txt = nom_image.getText().toString();
        mTTs.speak(txt, TextToSpeech.QUEUE_FLUSH, null);
    }


    public void getSpeechInput(View view) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Vous pouvez parler");

        //commencement de l'intent
        try {
            //in there was no error
            startActivityForResult(intent, 1000);
        } catch (Exception e) {
            //if there some error
            Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    // récupération de ce qui a été dit  dans le microphone du téléphone
    //verification de la bonne prononcition ou pas de ce qui a ete enregistré
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                retour = result.get(0);
                if (retour.toLowerCase().equals(nameCurrentImage.toLowerCase())) {
                    Toast.makeText(getApplicationContext(), "Bravo ! ", Toast.LENGTH_SHORT).show();
                    currentimage++;
                    nbdessai = 0;
                    nameCurrentImage = DisplayreadLevel(level.get(1), currentimage);
                } else {
                    if (nbdessai > 2) {
                        Toast.makeText(getApplicationContext(), "Ce n'est pas grave, tu y arrivera une prochaine fois", Toast.LENGTH_SHORT).show();
                        currentimage++;
                        nbdessai = 0;
                        nameCurrentImage = DisplayreadLevel(level.get(1), currentimage);
                    } else {
                        Toast.makeText(getApplicationContext(), "Non, c'était " + nameCurrentImage + ". \n Essaye encore une fois", Toast.LENGTH_SHORT).show();
                        speak();
                        nbdessai++;
                    }
                }
            }
        }
    }

    //Convertit la lecture du json depuis le dossier assets
    //les images étant stocké dans drawable
    private String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("images.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    // récupération des images json
    private String DisplayreadLevel(String level, int i) {
        try {
            JSONObject jObject = new JSONObject(loadJSONFromAsset());
            JSONArray pluginfo = jObject.getJSONArray(level);// niveau D1
            JSONObject e = pluginfo.getJSONObject(i);

            String imagefile = e.getString("img");
            String resName = imagefile.split("\\.")[2];
            nom_image.setText(resName);
            int id = getResources().getIdentifier(resName, "drawable", getPackageName());
            Drawable drawable = getResources().getDrawable(id);
            image.setImageDrawable(drawable);


            return resName;
        } catch (JSONException e) {
            e.printStackTrace();
            nom_image.setText("erreur JSONEXCaption");
            return "";
        }

    }
}

