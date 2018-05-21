package com.example.cleanarchitecture;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

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

        class ListarContacto extends AsyncTask<Void, Void, String> {
            ProgressDialog progressDialog;

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler rh = new RequestHandler();
                String res = rh.sendGetRequest(Config.URL_LISTAR);
                return res;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(getActivity(), "listar Contacto",
                        "Cargando...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressDialog.dismiss();
                Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
                showdata(s);

            }
        }
        ListarContacto listarContacto = new ListarContacto();
        listarContacto.execute();

        /*
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
        }*/

        return view;
    }

    private void showdata(String s){


        try {

            JSONObject jsonObject = new JSONObject();
            //ArrayList<HashMap<String, String>> list = new ArrayList<>();
            JSONArray result = jsonObject.getJSONArray("result");
            //Toast.makeText(getActivity(), "osvald0", Toast.LENGTH_SHORT).show();
            for (int i = 0; i<result.length(); i++){

                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString("id");
                String nombre = jo.getString("nombre");
                String telefono = jo.getString("telefono");
                String correo = jo.getString("correo");

                Contacto contacto = new Contacto(nombre, telefono, correo);
                contactosList.add(contacto);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapterContactos.notifyDataSetChanged();

    }

}
