package com.example.cleanarchitecture;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    FragmentManager fm;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        ListaFragment listaFragment = new ListaFragment();
        ft.add(R.id.frame, listaFragment).commit();

        frameLayout = findViewById(R.id.frame);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            ft = fm.beginTransaction();
            switch (item.getItemId()) {
                case R.id.mListar:
                    ListaFragment listaFragment = new ListaFragment();
                    ft.replace(R.id.frame, listaFragment).commit();
                    return true;
                case R.id.mCrear:
                    CrearFragment crearFragment = new CrearFragment();
                    ft.replace(R.id.frame, crearFragment).commit();
                    return true;
                case R.id.mBuscar:
                    BuscarFragment buscarFragment = new BuscarFragment();
                    ft.replace(R.id.frame, buscarFragment).commit();
                    return true;
                case R.id.mActualizar:
                    ActualizarFragment actualizarFragment = new ActualizarFragment();
                    ft.replace(R.id.frame, actualizarFragment).commit();
                    return true;
                case R.id.mBorrar:
                    BorrarFragment borrarFragment = new BorrarFragment();
                    ft.replace(R.id.frame, borrarFragment).commit();
                    return true;
            }
            return false;
        }
    };

}
