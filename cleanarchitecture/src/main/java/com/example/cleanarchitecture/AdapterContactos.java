package com.example.cleanarchitecture;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterContactos extends RecyclerView.Adapter<AdapterContactos.ContactosViewHolder> {

    private ArrayList<Contacto> contactosList;
    private int resource;
    private Activity activity;

    public AdapterContactos(ArrayList<Contacto> contactosList) {
        this.contactosList = contactosList;
    }

    public AdapterContactos(ArrayList<Contacto> contactosList, int resource, Activity activity) {
        this.contactosList = contactosList;
        this.resource = resource;
        this.activity = activity;
    }

    @Override
    public ContactosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);

        return new ContactosViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactosViewHolder holder, int position) {
        Contacto contacto = contactosList.get(position);
        holder.bindContactos(contacto, activity);
    }

    @Override
    public int getItemCount() {
        return contactosList.size();
    }

    public class ContactosViewHolder extends RecyclerView.ViewHolder {

        private TextView tNombre, tTelefono, tCorreo;

        public ContactosViewHolder(View itemView) {
            super(itemView);
            tNombre = itemView.findViewById(R.id.tNombre);
            tTelefono = itemView.findViewById(R.id.tTelefono);
            tCorreo = itemView.findViewById(R.id.tCorreo);
        }

        public void bindContactos(Contacto contacto, Activity activity) {
            tNombre.setText(contacto.getNombre());
            tTelefono.setText(contacto.getTelefono());
            tCorreo.setText(contacto.getCorreo());

        }
    }
}