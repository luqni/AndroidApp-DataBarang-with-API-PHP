package com.example.luqni.databarang;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BarangActivity extends AppCompatActivity {

    private EditText editTextKode, editTextNama, editTextHarga;
    private Barang barang;

    private String action_flag="add";
    private String refreshFlag="0";
    private static final String TAG="MahsiswaActivity";
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barang);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDataVolley();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        barang = new Barang();
        initUI();
        //initEvent();
        Intent intent = getIntent();
        if (intent.hasExtra("barang")) {
            barang = (Barang) intent.getSerializableExtra("barang");
            Log.d(TAG, "Barang : " + barang.toString());
            setData(barang);
            action_flag = "edit";
            editTextKode.setEnabled(false);
        }else{
            barang = new Barang();
        }
    }
    private void setData(Barang barang) {
        editTextKode.setText(barang.getKode());
        editTextNama.setText(barang.getNama());
        editTextHarga.setText(barang.getHarga());
    }
    private void initUI() {
        pDialog = new ProgressDialog(BarangActivity.this);

        editTextKode = (EditText) findViewById(R.id.editTextKode);
        editTextNama = (EditText) findViewById(R.id.editTextNama);
        editTextHarga = (EditText) findViewById(R.id.editTextHarga);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_barang, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
            saveDataVolley();
            return true;
        }else  if (id == R.id.action_delete) {
            hapusData();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void hapusData() {
        new AlertDialog.Builder(this)
                .setTitle("Data Barang")
                .setMessage("Hapus Data " + barang.getNama() + " ?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        hapusDataServer();
                        refreshFlag = "1";
                        //finish();
                    }
                })
                .setNegativeButton(android.R.string.no, null).show();
    }

    @Override
    public void finish() {
        System.gc();
        Intent data = new Intent();
        data.putExtra("refreshflag", refreshFlag);
        //  data.putExtra("barang", barang);
        setResult(RESULT_OK, data);
        super.finish();
    }

    private void saveDataVolley(){
        refreshFlag="1";
        final String nama = editTextNama.getText().toString();
        final String kode = editTextKode.getText().toString();
        final String harga = editTextHarga.getText().toString();

        String url = AppConfig.IP_SERVER+"/barang/savedata.php";
        pDialog.setMessage("Save Data Barang...");
        showDialog();

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hideDialog();
                        Log.d("BarangActivity", "response :" + response);
                        // Toast.makeText(getBaseContext(),"response: "+response, Toast.LENGTH_SHORT).show();
                        processResponse("Save Data",response);
                        finish();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        hideDialog();
                        error.printStackTrace();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<>();
                // the POST parameters:

                params.put("nama",nama);
                params.put("kode",kode);
                params.put("harga",harga);
                if (action_flag.equals("add")){
                    params.put("id","0");
                }else{
                    params.put("id",barang.getId());
                }
                return params;
            }
        };
        Volley.newRequestQueue(this).add(postRequest);

    }
    private void processResponse(String paction, String response){

        try {
            JSONObject jsonObj = new JSONObject(response);
            String errormsg = jsonObj.getString("errormsg");
            Toast.makeText(getBaseContext(),paction+" "+errormsg,Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {
            Log.d("BarangActivity", "errorJSON");
        }

    }

    private void hapusDataServer(){
        refreshFlag="1";
        String url = AppConfig.IP_SERVER+"/barang/deletedata.php";
        pDialog.setMessage("Hapus Data Barang...");
        showDialog();

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hideDialog();
                        Log.d("BarangActivity", "response :" + response);
                        processResponse("Hapus Data", response);
                        finish();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        hideDialog();
                        error.printStackTrace();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<>();
                // the POST parameters:
                params.put("id",barang.getId());

                return params;
            }
        };
        Volley.newRequestQueue(this).add(postRequest);

    }
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}