package com.example.cleanarchitecture;


import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActualizarFragment extends Fragment {

    private EditText eNombre, eTelefono, eCorreo;
    public Button bActualizar;
    ContactosSQLiteHelper contactosSQLiteHelper;
    SQLiteDatabase dbContactos;


    public ActualizarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_actualizar, container, false);

        eNombre = view.findViewById(R.id.eNombre);
        eTelefono = view.findViewById(R.id.eTelefono);
        eCorreo = view.findViewById(R.id.eCorreo);
        bActualizar = view.findViewById(R.id.btnActualizar);
        contactosSQLiteHelper = new ContactosSQLiteHelper(getActivity(),
                "Agenda",
                null,
                1);
        dbContactos = contactosSQLiteHelper.getWritableDatabase();
        bActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                ContentValues dataBD = new ContentValues();
                dataBD.put("telefono", eTelefono.getText().toString());
                dataBD.put("correo", eTelefono.getText().toString());

                dbContactos.update("contactos", dataBD,
                        "nombre='"+eNombre.getText().toString()+"'",null );
                 */
                class ActualizarContacto extends AsyncTask<Void, Void, String> {
                    ProgressDialog progressDialog;

                    @Override
                    protected String doInBackground(Void... voids) {
                        HashMap<String, String> params = new HashMap<>();
                        params.put("nombre", eNombre.getText().toString());
                        params.put("telefono", eTelefono.getText().toString());
                        params.put("correo", eCorreo.getText().toString());

                        RequestHandler rh = new RequestHandler();
                        String res = rh.sendPostRequest(Config.URL_ACTUALIZAR, params);
                        return res;
                    }

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        progressDialog = ProgressDialog.show(getActivity(), "Crear Contacto",
                                "Creando...", false, false);
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
                    }
                }
                ActualizarContacto actualizarContacto = new ActualizarContacto();
                actualizarContacto.execute();
            }
        });

        return view;
    }

    public void actualizarClicked (View view){

    }

}
