package com.example.iran.sysdvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ContextMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.iran.dao.DatabaseHelper;
import com.example.iran.dao.MovimentoDao;
import com.example.iran.dao.UserDao;
import com.example.iran.models.Movimento;
import com.example.iran.models.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView lbNome;
    private TextView lbEmail;
    private MenuItem configuracoes;

    private Movimento movimento;
    private MovimentoDao movimentoDao;
    private DatabaseHelper dh;


    private List<Movimento> movimentos;
    private ArrayAdapter<Movimento> adaptertodosmovimento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        View header = ((NavigationView)findViewById(R.id.nav_view)).getHeaderView(0);

        configuracoes = (MenuItem)findViewById(R.id.action_configuracoes);

        ((TextView) header.findViewById(R.id.lbNome)).setText(getIntent().getStringExtra("Nome"));
        ((TextView) header.findViewById(R.id.lbEmail)).setText(getIntent().getStringExtra("Email"));


        //Listagem basica dos items cadastrados no movimento
        dh = new DatabaseHelper(MainActivity.this);
        try {
            movimentoDao = new MovimentoDao(dh.getConnectionSource());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        movimentos = new ArrayList<Movimento>();
        try {
            movimentos = movimentoDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        adaptertodosmovimento = new ArrayAdapter<Movimento>(this, R.layout.layout_movimento, R.id.edTodosMovimentos, movimentos);

        ListView lvMovimento = (ListView)findViewById(R.id.lvTodosMovimentos);
        lvMovimento.setAdapter(adaptertodosmovimento);


        lvMovimento.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                if (view.getId() == R.id.lvTodosMovimentos) {
                    contextMenu.add("Excluir");
                }
            }
        });



//        try {
//            //ListarTodasMovimentacoes();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
//        tabLayout.addTab(tabLayout.newTab().setText("Todas"));
//        tabLayout.addTab(tabLayout.newTab().setText("Entradas"));
//        tabLayout.addTab(tabLayout.newTab().setText("Saídas"));
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//
//        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
//        final PageAdapterTabs adapter = new PageAdapterTabs
//                (getSupportFragmentManager(), tabLayout.getTabCount());
//        viewPager.setAdapter(adapter);
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//
//        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

        com.github.clans.fab.FloatingActionButton entrada = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.menu_item_entrada);
        entrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intententrada = new Intent(MainActivity.this, MovimentoActivity.class);
                intententrada.putExtra("tipo", 0);
                intententrada.putExtra("idUsr", getIntent().getIntExtra("idUsr",0));
                startActivity(intententrada);
            }
        });

        com.github.clans.fab.FloatingActionButton saida = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.menu_item_saida);
        saida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentsaida = new Intent(MainActivity.this, MovimentoActivity.class);
                intentsaida.putExtra("tipo", 1);
                intentsaida.putExtra("idUsr", getIntent().getIntExtra("idUsr",0));
                startActivity(intentsaida);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void ListarTodasMovimentacoes() throws SQLException {
        //movimentos = movimentoDao.queryForAll();
        //adaptertodosmovimento.notifyDataSetChanged();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case 0:
                movimento = movimentos.get(info.position);
                try {
                    movimentoDao.delete(movimento);
                    movimentos.remove(info.position);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                adaptertodosmovimento.notifyDataSetChanged();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_configuracoes) {
            Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
            intent.putExtra("modo", "U");
            intent.putExtra("idUsr", getIntent().getIntExtra("idUsr",0));
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent intent = new Intent(MainActivity.this, MoedasActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
