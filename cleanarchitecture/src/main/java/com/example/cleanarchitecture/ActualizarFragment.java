package com.example.cleanarchitecture;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
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

                ContentValues dataBD = new ContentValues();
                dataBD.put("telefono", eTelefono.getText().toString());
                dataBD.put("correo", eTelefono.getText().toString());

                dbContactos.update("contactos", dataBD,
                        "nombre='"+eNombre.getText().toString()+"'",null );
            }
        });

        return view;
    }

    public void actualizarClicked (View view){

    }

}
