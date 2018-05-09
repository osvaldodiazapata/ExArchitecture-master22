package com.edwinacubillos.exarchitecture.presenter;

public interface MainActivityPresenter {

    void sumar(String num1, String num2); //interactor

    void mostrarError(String error); //View

    void mostrarResultado(String resultado); //View
}
