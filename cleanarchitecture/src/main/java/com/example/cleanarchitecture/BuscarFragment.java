package com.example.cleanarchitecture;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class BuscarFragment extends Fragment {

    EditText eNombre;
    TextView tNombre, tTelefono, tCorreo;
    Button bBuscar;
    ContactosSQLiteHelper contactosSQLiteHelper;
    SQLiteDatabase dbContactos;

    public BuscarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_buscar, container, false);

        eNombre = view.findViewById(R.id.eNombre);
        tNombre = view.findViewById(R.id.tNombre);
        tTelefono = view.findViewById(R.id.tTelefono);
        tCorreo = view.findViewById(R.id.tCorreo);
        bBuscar = view.findViewById(R.id.btnBuscar);
        bBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = dbContactos.rawQuery("SELECT * FROM contactos" + " WHERE nombre = '"+
                        eNombre.getText().toString()+ "'",
                        null);
                if (cursor.moveToFirst()){
                    tNombre.setText(cursor.getString(1));
                    tTelefono.setText(cursor.getString(2));
                    tCorreo.setText(cursor.getString(3));
                }
                else {
                    Toast.makeText(getActivity(), "!!!No existe base de datos!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        contactosSQLiteHelper = new ContactosSQLiteHelper(getActivity(),
                "Agenda",
                null,
                1);
        dbContactos = contactosSQLiteHelper.getWritableDatabase();


        return view;
    }

    public void buscarClicked(View view){

    }

}
