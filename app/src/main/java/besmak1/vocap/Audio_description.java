package besmak1.vocap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.lang.Object.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Audio_description extends AppCompatActivity {
    private TextView nom_image;
    private int currentimage=0;
    private ImageView image ;
    private Button next;
    private Button ecoute;
    private Button parler;
    private String retour;
    private String resName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_description);

        image = findViewById(R.id.image);
        nom_image = findViewById(R.id.nom_image);

        ecoute = findViewById(R.id.bouton_ecoute);
        parler = findViewById(R.id.bouton_parler);

        final List<String> level = new ArrayList<String>();
        level.add("D1");
        level.add("D2");
        level.add("D3");

        retour = "";
        DisplayreadLevel(level.get(1), currentimage);
        currentimage++;
        parler.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v == parler) {
                    getSpeechInput(v);

                    //DisplayreadLevel(level.get(1), currentimage);
                    //Toast.makeText(getApplicationContext(), "Next Image", Toast.LENGTH_SHORT).show();
                    //currentimage++;
                }
            }
        });
        if (retour == "") {
            Toast.makeText(getApplicationContext(), "Je n'ai pas compris votre réponse", Toast.LENGTH_SHORT).show();
        }else{
            if(retour==nom_image.toString()){
                Toast.makeText(getApplicationContext(), "Bravo ! ", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "non cest"+ nom_image.toString(), Toast.LENGTH_SHORT).show();
            }
    }

        }




    public void getSpeechInput(View view){
        Intent intent= new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, Locale.getDefault());

        if(intent.resolveActivity(getPackageManager()) !=null){
            startActivityForResult(intent, 10);
            Toast.makeText(this, "Vous pouvez parler ! ", Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(this, "Your Device Don't support speech Input", Toast.LENGTH_SHORT).show();
        }
        startActivityForResult(intent, 10);
    }

    @Override
    // récupération de ce qui a été dit  dans el microphone du téléphone
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode){
            case 10:
                if(resultCode == RESULT_OK && data != null){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    retour = result.get(0);
                }
                break;
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
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


    // récupération des images json
    private int DisplayreadLevel(String level, int i){
        try {
            JSONObject jObject = new JSONObject(loadJSONFromAsset());
            JSONArray pluginfo = jObject.getJSONArray(level);// niveau D1
            JSONObject e = pluginfo.getJSONObject(i);

            String imagefile = e.getString("img");
            resName = imagefile.split("\\.")[2];
            nom_image.setText(resName);
            int id = getResources().getIdentifier(resName, "drawable", getPackageName());
            Drawable drawable = getResources().getDrawable(id);
            image.setImageDrawable(drawable);


            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
            nom_image.setText("erreur JSONEXCeption");
            return 0;
        }

    }
}

