package com.example.iran.Apis;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class CotacaoMoedas {

    private static String BASE_URL = "http://api.promasters.net.br/cotacao/v1/";
    private static String ALL_MOEDAS = BASE_URL+"valores";

    private static AsyncHttpClient cliente = new AsyncHttpClient();

    public static void getAllNickels(RequestParams params, AsyncHttpResponseHandler responseHandler){
        cliente.get(ALL_MOEDAS,params,responseHandler);
    }

}
