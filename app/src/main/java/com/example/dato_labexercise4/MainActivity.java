package com.example.dato_labexercise4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    String[] names, version, api, rDate,dbmsg;
    ListView list;

    int[] cLogo = {R.drawable.v1, R.drawable.v1point5, R.drawable.v1point6, R.drawable.v2, R.drawable.v2point2, R.drawable.v2point3, R.drawable.v3, R.drawable.v4, R.drawable.v4point1,
            R.drawable.v4point4, R.drawable.v5, R.drawable.v6, R.drawable.v7, R.drawable.v8, R.drawable.v9, R.drawable.androidten};

    ArrayList<Android> androidList =  new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        names = getResources().getStringArray(R.array.androidNames);
        version = getResources().getStringArray(R.array.androidVersions);
        api = getResources().getStringArray(R.array.androidAPI);
        rDate = getResources().getStringArray(R.array.androidRdate);
        dbmsg = getResources().getStringArray(R.array.dbmsg);
        list = findViewById(R.id.lvAndroids);

        for(int i = 0; i < names.length; i++){
            androidList.add(new Android(cLogo[i], names[i], version[i], api[i], rDate[i], dbmsg[i]));
        }

        list = findViewById(R.id.lvAndroids);
        AndroidAdapter adapter = new AndroidAdapter(this, R.layout.item, androidList);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
        final File folder = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(folder, "android.txt");
        try{
            FileOutputStream fos = new FileOutputStream(file);
            String choiceName = androidList.get(i).getName();
            String choiceVersion = androidList.get(i).getVersion();
            String choiceApi = androidList.get(i).getApi();
            String choiceRdate = androidList.get(i).getRdate();
            String choiceDbmsg = androidList.get(i).getDbmsg();

            fos.write((choiceName + " Version(" + choiceVersion + ")\n").getBytes());
            fos.write((choiceApi + "\n").getBytes());
            fos.write((choiceRdate + "\n").getBytes());
            fos.write((choiceDbmsg + "\n").getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(androidList.get(i).getName());
        dialog.setIcon(androidList.get(i).getLogo());
        dialog.setMessage(androidList.get(i).getDbmsg());
        dialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick (DialogInterface dialog,int which){
               //dialog.dismiss();
                File file = new File(folder, "android.txt");
                StringBuilder sb = new StringBuilder();


                String toastName = null;
                String toastRdate = null;
                try{
                    BufferedReader br = new BufferedReader(new FileReader(file));

                   //Gets the android name and version from SD Card
                    for (int i = 0; i < 1; i++){
                        //br.readLine(); = reads the first line
                        toastName = br.readLine();

                    }

                    //Gets the released date from SD Card
                    for(int i = 0 ; i<2; i++){
                        toastRdate = br.readLine();
                    }
                } catch (FileNotFoundException e) {
                    Log.d("error", "File not found");
                } catch (IOException e) {
                    Log.d("error", "IO error");
                }



                Toast.makeText(MainActivity.this, toastName + "\n" + toastRdate, Toast.LENGTH_LONG).show();
               // Toast.makeText(MainActivity.this, toastRdate, Toast.LENGTH_LONG).show();

            }
        });
        dialog.create().show();

    }
}
