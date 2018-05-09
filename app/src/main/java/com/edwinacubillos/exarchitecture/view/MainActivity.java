package com.edwinacubillos.exarchitecture.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.edwinacubillos.exarchitecture.R;
import com.edwinacubillos.exarchitecture.presenter.MainActivityPresenter;
import com.edwinacubillos.exarchitecture.presenter.MainActivityPresenterImpl;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    private MainActivityPresenter presenter;
    private EditText num1, num2;
    private TextView tTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.edwinacubillos.exarchitecture.R.layout.activity_main);

        num1 = findViewById(R.id.eNum1);
        num2 = findViewById(R.id.eNum2);
        tTotal = findViewById(R.id.tTotal);

        presenter = new MainActivityPresenterImpl(this);
    }

    public void sumarClicked(View view) {
        String numero1 = num1.getText().toString();
        String numero2 = num2.getText().toString();
        presenter.sumar(numero1, numero2);
    }

    public void mostrarResultado(String result) {
        tTotal.setText(result);
    }

    public void mostrarError(String error) {
    }
}
