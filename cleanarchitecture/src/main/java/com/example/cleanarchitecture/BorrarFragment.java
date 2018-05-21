package com.example.cleanarchitecture;

import android.app.ProgressDialog;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class BorrarFragment extends Fragment {

    private EditText eNombre;
    public Button bBorrar;

    ContactosSQLiteHelper contactosSQLiteHelper;
    SQLiteDatabase dbContactos;

    public BorrarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_borrar, container, false);

        eNombre = view.findViewById(R.id.eNombre);
        bBorrar = view.findViewById(R.id.btnBorrar);

        contactosSQLiteHelper = new ContactosSQLiteHelper(getActivity(),
                "Agenda",
                null,
                1);
        dbContactos = contactosSQLiteHelper.getWritableDatabase();
        bBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*dbContactos.delete("contactos",
                        "nombre='"+eNombre.getText().toString()+"'",
                        null);
            */
                class BorrarContacto extends AsyncTask<Void, Void, String> {
                    ProgressDialog progressDialog;

                    @Override
                    protected String doInBackground(Void... voids) {
                        RequestHandler rh = new RequestHandler();
                        String res = rh.sendGetRequestParam(Config.URL_BORRAR, eNombre.getText().toString());
                        return res;
                    }

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        progressDialog = ProgressDialog.show(getActivity(), "Borrar Contacto",
                                "Borrando...", false, false);
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
                        showData(s);
                    }
                }
                BorrarContacto borrarContacto = new BorrarContacto();
                borrarContacto.execute();
            }
        });
        return view;
    }

    public void borrarClicked(View view){

    }

    private void showData(String s){
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray result = jsonObject.getJSONArray("result");
            JSONObject c = result.getJSONObject(0);
            //tNombre.setText(c.getString("nombre"));
            //tTelefono.setText(c.getString("telefono"));
            //tCorreo.setText(c.getString("correo"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
