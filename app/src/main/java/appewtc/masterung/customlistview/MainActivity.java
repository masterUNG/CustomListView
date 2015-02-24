package appewtc.masterung.customlistview;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends ActionBarActivity {

    private FoodTABLE objFoodTABLE;
    private String[] strNameFood, strPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create Or Connected Database
        createOrConnectedDatabase();

        //Tester
        //objFoodTABLE.addValueFood("Food", "Price");

        //Delete All Data
        deleteAllData();

        //Synchronize
        synJSONtoSQLite();

        int[] intTarget = {R.drawable.food1, R.drawable.food2, R.drawable.food3, R.drawable.food4, R.drawable.food5, R.drawable.food6, R.drawable.food7, R.drawable.food8, R.drawable.food9, R.drawable.food10,
                R.drawable.food11, R.drawable.food12, R.drawable.food13, R.drawable.food14, R.drawable.food15,
                R.drawable.food16, R.drawable.food17, R.drawable.food18, R.drawable.food19, R.drawable.food20};

        //String[] strNameFood = getResources().getStringArray(R.array.food);

            strNameFood = objFoodTABLE.listFood();

            strPrice = objFoodTABLE.listPrice();

        //Create Adapter
        myAdapter objmyAdapter = new myAdapter(getApplicationContext(), strNameFood, strPrice, intTarget);

        ListView myListView = (ListView) findViewById(R.id.listView);
        myListView.setAdapter(objmyAdapter);

        //Active ClickListView
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                showClick(strNameFood[position], strPrice[position]);

            }   // event
        });


    }   // onCreate

    private void showClick(String strFood, String strPrice) {
        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setTitle("Confirm Order");
        objBuilder.setMessage("Food = " + strFood + "\n" + "Price = " + strPrice);
        objBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        objBuilder.show();
    }   // showClick



    private void deleteAllData() {
        SQLiteDatabase objSQLite = openOrCreateDatabase("my_menu.db", MODE_PRIVATE, null);
        objSQLite.delete("foodTABLE", null, null);
    }   // deleteAllData

    private void synJSONtoSQLite() {

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(myPolicy);
        }   // if


        InputStream objInputStream = null;
        String strJSON = "";

        try {

            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://swiftcodingthai.com/rest/get_data_food_rest.php");
            HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
            HttpEntity objHttpEntity = objHttpResponse.getEntity();
            objInputStream = objHttpEntity.getContent();

        } catch (Exception e) {
            Log.d("memu", "InputStream ==> " + e.toString());
        }


        try {

            BufferedReader objBufferedReader = new BufferedReader(new InputStreamReader(objInputStream, "UTF-8"));
            StringBuilder objStringBuilder = new StringBuilder();
            String strLine = null;
            while ((strLine = objBufferedReader.readLine()) != null) {
                objStringBuilder.append(strLine);
            }
            objInputStream.close();
            strJSON = objStringBuilder.toString();
        } catch (Exception e) {
            Log.d("memu", "strJSON ==> " + e.toString());
        }


        try {

            final JSONArray objJSONArray = new JSONArray(strJSON);
            for (int i = 0; i < objJSONArray.length(); i++) {
                JSONObject objJSONObjcet = objJSONArray.getJSONObject(i);
                String strFood = objJSONObjcet.getString("Food");
                String strPrice = objJSONObjcet.getString("Price");
                objFoodTABLE.addValueFood(strFood, strPrice);
            }   // for

        } catch (Exception e) {
            Log.d("memu", "update SQLite ==> " + e.toString());
        }


    }   // synJSONtoSQLite

    private void createOrConnectedDatabase() {
        objFoodTABLE = new FoodTABLE(this);
    }   // createOrConnectedDatabase


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}   // Main Class
