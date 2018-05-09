package com.example.cleanarchitecture;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


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
                ContentValues dataBD = new ContentValues();
                dataBD.put("nombre", eNombre.getText().toString());
                dataBD.put("telefono", eTelefono.getText().toString());
                dataBD.put("correo", eCorreo.getText().toString());

                dbContactos.insert("contactos", null, dataBD);

            }
        });
        return view;
    }

    public void crearClicked(View view){

    }

}
