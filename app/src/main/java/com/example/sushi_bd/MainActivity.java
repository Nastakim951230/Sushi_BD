package com.example.sushi_bd;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String  zagolovok="Name";
private AdapterMask pAdapter;
private List<Mask> listSushi=new ArrayList<>();
    Spinner spinnerFilter,spinnerChoice;
    String [] Filter={"Без фильтрации","По возрастанию","По убыванию"};
    String [] Choice={"Без выбора","Название","Цена"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView sushiView=findViewById(R.id.ListSushi);
        pAdapter=new AdapterMask(MainActivity.this,listSushi);
        sushiView.setAdapter(pAdapter);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Filter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerFilter=findViewById(R.id.filter);
        spinnerFilter.setAdapter(adapter);

        ArrayAdapter<String> adapterChoice = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Choice);
        adapterChoice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerChoice=findViewById(R.id.vvybor);
        spinnerChoice.setAdapter(adapterChoice);

        new GetSushi().execute();
    }

    //Поиск по listview
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);

        MenuItem item=menu.findItem(R.id.search);
        SearchView searchView=(SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                txtSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                txtSearch(newText);

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    //Выбор по какой из столбцов будет поиск (троиточие рядом с поиском)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();

        if(id==R.id.NamePoisk)
        {
            zagolovok="Name";
        }
        else
        if(id==R.id.PreciPoisk)
        {
            zagolovok="Surname";
        }
        else
        if(id==R.id.SostavPoisk)
        {
            zagolovok="Job_title";
        }
        return super.onOptionsItemSelected(item);
    }

    private  void txtSearch(String str)
    {}


    private class GetSushi extends AsyncTask<Void,Void,String>
    {


        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL ( "https://ngknn.ru:5101/NGKNN/ТрифоноваАР/api/Sushis");
                HttpURLConnection connection=(HttpURLConnection) url.openConnection();

                BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result=new StringBuilder();
                String line="";

                while ((line=reader.readLine())!=null){
                    result.append(line);
                }
                return result.toString();
            }
            catch (Exception exception)
            {
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                {
                    JSONArray tempArray= new JSONArray(s);
                    for (int i=0;i<tempArray.length();i++)
                    {
                        JSONObject productJson=tempArray.getJSONObject(i);
                        Mask tempSushi = new Mask(
                                productJson.getInt("Id"),
                                productJson.getString("Image"),
                                productJson.getString("Name"),
                                productJson.getString("Compound"),
                                productJson.getInt("Price")
                        );
                        listSushi.add(tempSushi);
                        pAdapter.notifyDataSetInvalidated();
                    }
                }
            }
            catch (Exception ignored)
            {

            }
        }
    }

    public void Add_bt(View v)
    {
        Intent intent = new Intent(this, Add.class);
        startActivity(intent);
    }
}