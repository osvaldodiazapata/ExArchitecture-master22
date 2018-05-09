package com.edwinacubillos.exarchitecture.presenter;

import com.edwinacubillos.exarchitecture.interactor.MainActivityInteractor;
import com.edwinacubillos.exarchitecture.interactor.MainActivityInteractorImpl;
import com.edwinacubillos.exarchitecture.view.MainActivityView;

public class MainActivityPresenterImpl implements MainActivityPresenter {

    private MainActivityView activityView;
    private MainActivityInteractor interactor;

    public MainActivityPresenterImpl(MainActivityView activityView) {
        this.activityView = activityView;
        interactor = new MainActivityInteractorImpl(this);
    }

    @Override
    public void sumar(String num1, String num2) {
        interactor.sumar(num1, num2);
    }

    @Override
    public void mostrarError(String error) {
        activityView.mostrarError(error);
    }

    @Override
    public void mostrarResultado(String resultado) {
        activityView.mostrarResultado(resultado);
    }
}
