package com.myapplicationdev.android.p09_gettingmylocationsenhanced;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class CheckRecords extends AppCompatActivity {

    Button btnRefresh;
    TextView tvNumOfRecords;
    ListView lvRecords;
    ArrayAdapter aa;
    ArrayList<String> alString;

    String folderLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_records);

        btnRefresh = findViewById(R.id.btnRefresh);
        tvNumOfRecords = findViewById(R.id.tvNumOfRecords);
        lvRecords = findViewById(R.id.lvRecords);
        alString = new ArrayList<String>();

        folderLocation = getFilesDir().getAbsolutePath() + "/MyFolder";
        File targetFile = new File(folderLocation, "data.txt");
        if (targetFile.exists() == true){
            String data ="";
            int numOfRecords = 0;
            try {
                FileReader reader = new FileReader(targetFile);
                BufferedReader br = new BufferedReader(reader);
                String line = br.readLine();
                while (line != null){
//                    data += line + "\n";
                    line = br.readLine();
                    numOfRecords += 1;
                    alString.add(line);
                }
                tvNumOfRecords.setText("Number of Records: "+ numOfRecords);
                aa = new ArrayAdapter<String>(CheckRecords.this, android.R.layout.simple_list_item_1, alString);
                lvRecords.setAdapter(aa);
                br.close();
                reader.close();
            } catch (Exception e) {
                Toast.makeText(CheckRecords.this, "Failed to read!", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
            Log.d("Content", data);
        }




    }
}