package com.example.cleanarchitecture;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaFragment extends Fragment {

    private ArrayList<Contacto> contactosList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapterContactos;
    private RecyclerView.LayoutManager layoutManager;

    ContactosSQLiteHelper contactosSQLiteHelper;
    SQLiteDatabase dbContactos;

    public ListaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista, container, false);

        contactosSQLiteHelper = new ContactosSQLiteHelper(getActivity(),
                "Agenda",
                null,
                1);
        dbContactos = contactosSQLiteHelper.getWritableDatabase();

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        contactosList = new ArrayList<>();

        adapterContactos = new AdapterContactos(contactosList, R.layout.list_item,
               getActivity());

        recyclerView.setAdapter(adapterContactos);

        Cursor cursor = dbContactos.rawQuery("SELECT * FROM contactos", null);
        if (cursor.moveToFirst()){
            do{
            Contacto contacto = new Contacto(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3));
            contactosList.add(contacto);
            }while(cursor.moveToNext());

        }else{
            Toast.makeText(getActivity(), "No existe la lista", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

}
