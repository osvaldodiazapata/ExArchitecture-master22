package com.example.cleanarchitecture;


import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class CrearFragment extends Fragment {

    private EditText eNombre, eTelefono, eCorreo;
    private Button btnCrear;

    ContactosSQLiteHelper contactosSQLiteHelper;
    SQLiteDatabase dbContactos;

    public CrearFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_crear, container, false);

        eNombre = view.findViewById(R.id.eNombre);
        eTelefono = view.findViewById(R.id.eTelefono);
        eCorreo = view.findViewById(R.id.eCorreo);

        btnCrear = view.findViewById(R.id.btnCrear);

        contactosSQLiteHelper = new ContactosSQLiteHelper(getActivity(),
                "Agenda",
                null,
                1);
        dbContactos = contactosSQLiteHelper.getWritableDatabase();

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            /*    ContentValues dataBD = new ContentValues();
                dataBD.put("nombre", eNombre.getText().toString());
                dataBD.put("telefono", eTelefono.getText().toString());
                dataBD.put("correo", eCorreo.getText().toString());

                dbContactos.insert("contactos", null, dataBD);

            }*/
                /*
                class CrearContacto extends AsyncTask<Void, Void, String> {
                    ProgressDialog progressDialog;

                    @Override
                    protected String doInBackground(Void... voids) {
                        HashMap<String, String> params = new HashMap<>();
                        params.put("nombre", eNombre.getText().toString());
                        params.put("telefono", eTelefono.getText().toString());
                        params.put("correo", eCorreo.getText().toString());

                        RequestHandler rh = new RequestHandler();
                        String res = rh.sendPostRequest(Config.URL_CREAR, params);
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
                CrearContacto crear = new CrearContacto();
                crear.execute();*/

                RequestQueue queue = Volley.newRequestQueue(getActivity());

                StringRequest postRequest = new StringRequest(Request.Method.POST, Config.URL_CREAR,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                // response
                                Log.d("Response", response);
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Log.d("Error.Response", error.getMessage());
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String>  params = new HashMap<String, String>();
                        params.put("nombre", eNombre.getText().toString());
                        params.put("telefono", eTelefono.getText().toString());
                        params.put("correo", eCorreo.getText().toString());

                        return params;
                    }
                };
                queue.add(postRequest);
            }
        });

        return view;
    }

}
