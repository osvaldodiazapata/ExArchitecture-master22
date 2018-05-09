package com.edwinacubillos.exarchitecture.interactor;

import com.edwinacubillos.exarchitecture.presenter.MainActivityPresenter;

public class MainActivityInteractorImpl implements MainActivityInteractor {

    private MainActivityPresenter presenter;

    public MainActivityInteractorImpl(MainActivityPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void sumar(String num1, String num2) {
        Double resultado = Double.valueOf(num1) + Double.valueOf(num2);
        presenter.mostrarResultado(String.valueOf(resultado));
    }
}
