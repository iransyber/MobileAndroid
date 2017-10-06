package com.example.iran.sysdvp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.iran.Apis.CotacaoMoedas;
import com.example.iran.models.Moedas;
import com.google.gson.JsonObject;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import static com.loopj.android.http.AsyncHttpClient.log;

public class MoedasActivity extends Activity {

    private List<Moedas> moedas;
    private ArrayAdapter<Moedas> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moedas);

        moedas = new ArrayList<Moedas>();
        adapter = new ArrayAdapter<Moedas>(this, R.layout.layout_moedas, R.id.edNomeMoedas, moedas);

        ListView lvMoedas = (ListView)findViewById(R.id.lvListaMoedas);
        lvMoedas.setAdapter(adapter);

        CotacaoMoedas.getAllNickels(null, new JsonHttpResponseHandler(){
            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                log.e("erro",throwable.getMessage());
            }

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                JSONObject obj = null;
                JSONObject objMoedas = null;
                try {
                    obj = response.getJSONObject("valores");
                    JsonParser parser = new JsonParser();
                    JsonObject array = parser.parse(obj.toString()).getAsJsonObject();
                    Set<?> s =  array.keySet();
                    Iterator<?> x = s.iterator();
                    do{
                        try {
                            Moedas moedasobj = new Moedas();
                            objMoedas = obj.getJSONObject(x.next().toString());
                            moedasobj.setNome(objMoedas.getString("nome"));
                            moedasobj.setValor((float) objMoedas.getDouble("valor"));
                            moedasobj.setFonte(objMoedas.getString("fonte"));
                            moedas.add(moedasobj);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }while(x.hasNext());

                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
