package com.example.iran.sysdvp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iran.dao.DatabaseHelper;
import com.example.iran.dao.UserDao;
import com.example.iran.models.User;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.stmt.query.IsNull;

import java.sql.SQLException;

public class LoginActivity extends AppCompatActivity {

    //Conexao sql
    private DatabaseHelper dh;
    private UserDao userDao;
    private User usr;

    SharedPreferences preferencias;
    private EditText edEmail;
    private EditText edSenha;
    private Button btnEntrar;
    private Intent intent;
    private Intent intentNovoRegistro;
    private CheckBox Lembrar;
    private TextView novoRegistro;

    private static final String MyPreference = "MyPreference";
    private static final String UserName = "UserName";
    private static final String PassWord = "PassWord";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        intent = new Intent(LoginActivity.this, MainActivity.class);
        intentNovoRegistro = new Intent(LoginActivity.this, RegistroActivity.class);

        edEmail = (EditText)findViewById(R.id.edEmail);
        edSenha = (EditText)findViewById(R.id.edSenha);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        Lembrar = (CheckBox)findViewById(R.id.chLembrarCredenciais);
        novoRegistro = (TextView)findViewById(R.id.opNovoRegistro);


        if (CarregarPreferencias())
          showPreferences();
        else
          clearPreferences();

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((edEmail.getText().toString().equals("")) || (edSenha.getText().toString().equals(""))){
                    Toast.makeText(LoginActivity.this, "Por favor informe login e senha", Toast.LENGTH_LONG).show();
                }else{

                    if (Lembrar.isChecked())
                      savePreferences();
                    else
                      clearPreferences();

                    try {
                        dh = new DatabaseHelper(LoginActivity.this);
                        userDao = new UserDao(dh.getConnectionSource());

                        QueryBuilder<User, Integer> qb = userDao.queryBuilder();
                        Where where = qb.where();

                        where.eq("email", edEmail.getText().toString());
                        where.and();
                        where.eq("senha", edSenha.getText().toString());

                        //PreparedQuery<User> preparedQuery = qb.prepare();
                        usr = new User();
                        usr = qb.queryForFirst();

                        if (usr == null){
                            Toast.makeText(LoginActivity.this, "Usuario ou Senha Incorretos", Toast.LENGTH_LONG).show();
                        }else{
                            intent.putExtra("idUsr", usr.get_id());
                            intent.putExtra("Nome", usr.getNome().toString());
                            intent.putExtra("Email", usr.getEmail().toString());
                            intent.putExtra("Nivel", usr.getNivel());
                            startActivity(intent);
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        novoRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentNovoRegistro.putExtra("modo","N");
                startActivity(intentNovoRegistro);
            }
        });
    }

    public void savePreferences() {
        String name = edEmail.getText().toString();
        String pws = edSenha.getText().toString();
        Boolean lem = Lembrar.isChecked();
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putBoolean(MyPreference, lem);
        editor.putString(UserName, name);
        editor.putString(PassWord, pws);
        editor.commit();
    }

    public void clearPreferences(){
        SharedPreferences.Editor editor = preferencias.edit();
        editor.remove(UserName);
        editor.remove(PassWord);
        editor.remove(MyPreference);
        editor.commit();
    }

    public void showPreferences(){
        preferencias = getSharedPreferences(MyPreference, Context.MODE_PRIVATE);
        if (preferencias.contains(UserName))
            edEmail.setText(preferencias.getString(UserName,""));

        if (preferencias.contains(PassWord))
            edSenha.setText(preferencias.getString(PassWord, ""));

        if (preferencias.contains(MyPreference))
            Lembrar.setChecked(preferencias.getBoolean(MyPreference, false));
    }

    public boolean CarregarPreferencias(){
        preferencias = getSharedPreferences(MyPreference, Context.MODE_PRIVATE);
        if (preferencias.contains(MyPreference))
            Lembrar.setChecked(preferencias.getBoolean(MyPreference, false));

        if (Lembrar.isChecked())
            return true;
        else
           return false;
    }
}
