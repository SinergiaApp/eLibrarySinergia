package com.sinergia.eLibrary.presentation.AdminZone.Model

interface AdminViewModel {

    interface AdminViewModelCallBack{
        fun onCreateResourceSuccess()
        fun onCreateResourceFailure(errorMsg: String)
    }

    fun addNewResource(titulo: String, autor: String, iban: String, edicion: String, sinopsis: String, listener: AdminViewModel.AdminViewModelCallBack)

}