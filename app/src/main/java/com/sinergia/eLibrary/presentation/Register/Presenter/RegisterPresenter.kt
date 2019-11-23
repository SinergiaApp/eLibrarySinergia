package com.sinergia.eLibrary.presentation.Register.Presenter

import androidx.core.util.PatternsCompat
import com.sinergia.eLibrary.domain.interactors.RegisterInteractor.RegisterInteractor
import com.sinergia.eLibrary.presentation.Register.RegisterContract
import java.util.regex.Pattern

class RegisterPresenter(registerInteractor: RegisterInteractor): RegisterContract.RegisterPresenter {

    var view: RegisterContract.RegisterView? = null
    var registerInteractor: RegisterInteractor? = null


    init {
        this.registerInteractor = registerInteractor
    }

    override fun attachView(view: RegisterContract.RegisterView) {
        this.view = view
    }

    override fun dettachView() {
        view = null
    }

    override fun isViewAttach(): Boolean {
        return view != null
    }

    override fun checkEmptyFields(name: String, lastName: String, email: String, password: String, repearPassword: String): Boolean {
        return name.isNullOrEmpty() || lastName.isNullOrEmpty() || email.isNullOrEmpty() || password.isNullOrEmpty() || repearPassword.isNullOrEmpty()
    }


    override fun checkEmptyRegisterName(name: String): Boolean {
        return name.isNullOrEmpty()
    }

    override fun checkEmptyRegisteraLastName(lastName: String): Boolean {
        return lastName.isNullOrEmpty()
    }

    override fun checkRegisterEmptyEmail(email: String): Boolean {
        return email.isNullOrEmpty()
    }

    override fun checkEmptyRegisterPassword(password: String): Boolean {
        return password.isNullOrEmpty()
    }

    override fun checkEmptyRegisterRepeatPassword(repearPassword: String): Boolean {
        return repearPassword.isNullOrEmpty()
    }

    override fun checkValidRegisterEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun checkRegisterPasswordMatch(password: String, repearPassword: String): Boolean {
        return password == repearPassword
    }

    override fun registerWithEmailAndPassword(name: String, lastName: String, email: String, password: String) {

        view?.showProgressBar()
        view?.disableRegisterButton()

        registerInteractor?.register(name, lastName, email, password, object: RegisterInteractor.RegisterCallBack{

            override fun onRegisterSuccess() {
                view?.navigateToMainPage()
                view?.hideProgressBar()
                view?.enableRegisterButton()
            }

            override fun onRegisterFailure(erroMsg: String) {
                view?.showError(erroMsg)
                view?.hideProgressBar()
                view?.enableRegisterButton()
            }

        })
    }
}