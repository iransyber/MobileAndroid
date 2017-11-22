package com.example.iran.sysdvp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.iran.dao.DatabaseHelper;
import com.example.iran.dao.MovimentoDao;
import com.example.iran.dao.UserDao;
import com.example.iran.models.Movimento;
import com.example.iran.models.User;

import java.sql.SQLException;

import javax.microedition.khronos.egl.EGLDisplay;

public class MovimentoActivity extends AppCompatActivity {

    private EditText edDescicao;
    private EditText edValor;
    private Button btConfirmaMovimento;
    private Button btCancelaMovimento;
    private TextView lbDescricaoMovimento;

    private Movimento movimento;
    private User usr;
    private MovimentoDao movimentoDao;
    private UserDao userDao;
    private DatabaseHelper dh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimento);

        dh = new DatabaseHelper(MovimentoActivity.this);
        usr = new User();

        edDescicao = (EditText)findViewById(R.id.edDescricaoMovimento);
        edValor = (EditText) findViewById(R.id.edValorMovimento);
        btConfirmaMovimento = (Button) findViewById(R.id.btConfirmaMovimento);
        btCancelaMovimento = (Button) findViewById(R.id.btCancelaLancamento);
        lbDescricaoMovimento = (TextView) findViewById(R.id.lbTipoMovimento);
        try {
            movimentoDao = new MovimentoDao(dh.getConnectionSource());
            userDao = new UserDao(dh.getConnectionSource());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (getIntent().getIntExtra("tipo", 0) > 0){
            lbDescricaoMovimento.setText(lbDescricaoMovimento.getText() + "Saída");
        }else {
            lbDescricaoMovimento.setText(lbDescricaoMovimento.getText() + "Entrada");
        }

        btConfirmaMovimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               movimento = new Movimento();
                if (getIntent().getIntExtra("idUsr", 0) > 0) movimento.setUserId(getIntent().getIntExtra("idUsr", 0));
                if (getIntent().getIntExtra("tipo", 1) > 0) movimento.setTipo(getIntent().getIntExtra("tipo", 1));
                movimento.setDescricao(edDescicao.getText().toString());
                movimento.setValor(Integer.parseInt(edValor.getText().toString()));
                try {
                    movimentoDao.create(movimento);
                    Intent intent = new Intent(MovimentoActivity.this, MainActivity.class);
                    try {
                        usr = userDao.queryForId(getIntent().getIntExtra("idUsr", 0));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    intent.putExtra("idUsr", usr.get_id());
                    intent.putExtra("Nome",  usr.getNome().toString());
                    intent.putExtra("Email", usr.getEmail().toString());
                    intent.putExtra("Nivel", usr.getNivel());
                    startActivity(intent);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        btCancelaMovimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MovimentoActivity.this, MainActivity.class);
                try {
                    usr = userDao.queryForId(getIntent().getIntExtra("idUsr", 0));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                intent.putExtra("idUsr", usr.get_id());
                intent.putExtra("Nome",  usr.getNome().toString());
                intent.putExtra("Email", usr.getEmail().toString());
                intent.putExtra("Nivel", usr.getNivel());
                startActivity(intent);
            }
        });

    }
}
