package com.example.iran.sysdvp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iran.dao.DatabaseHelper;
import com.example.iran.dao.UserDao;
import com.example.iran.models.User;

import java.sql.SQLException;

public class RegistroActivity extends AppCompatActivity {

    private DatabaseHelper dh;
    private UserDao userDao;
    private User usr;
    private User usrUp;

    private EditText edNome;
    private EditText edEmail;
    private EditText edNivel;
    private EditText edSenha;
    private EditText edConfirmeSenha;
    private Button btRegistrar;

    private Intent intente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        dh = new DatabaseHelper(RegistroActivity.this);
        usrUp = new User();

        try {
            userDao = new UserDao(dh.getConnectionSource());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        edNome = (EditText)findViewById(R.id.edNome);
        edEmail = (EditText)findViewById(R.id.edEmail);
        edNivel = (EditText)findViewById(R.id.edNivel);
        edSenha = (EditText)findViewById(R.id.edSenha);
        edConfirmeSenha = (EditText)findViewById(R.id.edConfirmeSenha);
        btRegistrar = (Button)findViewById(R.id.btRegistrar);

        if (getIntent().getStringExtra("modo").equals("U")){
            try {
                usrUp = userDao.queryForId(getIntent().getIntExtra("idUsr", 0));
                edNome.setText(usrUp.getNome());
                edEmail.setText(usrUp.getEmail());
                btRegistrar.setText("Alterar");
                edNivel.setText(""+usrUp.getNivel());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    usr = new User();
                    if (getIntent().getIntExtra("idUsr", 0) > 0) usr.set_id(getIntent().getIntExtra("idUsr", 0));
                    usr.setNome(edNome.getText().toString());
                    usr.setEmail(edEmail.getText().toString());
                    usr.setSenha(edSenha.getText().toString());
                    usr.setNivel(Integer.parseInt(edNivel.getText().toString()));

                    if (getIntent().getStringExtra("modo").equals("N")){
                        userDao.create(usr);
                        Toast.makeText(RegistroActivity.this, "Registro efetuado com sucesso!", Toast.LENGTH_LONG).show();
                        intente = new Intent(RegistroActivity.this, LoginActivity.class);
                        startActivity(intente);
                    }
                    else{
                      userDao.update(usr);
                      intente = new Intent(RegistroActivity.this, MainActivity.class);
                      intente.putExtra("idUsr", usr.get_id());
                      intente.putExtra("Nome", usr.getNome().toString());
                      intente.putExtra("Email", usr.getEmail().toString());
                      intente.putExtra("Nivel", usr.getNivel());
                      Toast.makeText(RegistroActivity.this, "Registro alterado com sucesso!", Toast.LENGTH_LONG).show();
                      startActivity(intente);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
