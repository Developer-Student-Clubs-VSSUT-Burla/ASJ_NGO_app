package com.pkpanda.ngofinder.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileDhairyaModel: ViewModel() {
    private var _edit= MutableLiveData<Int>(0)

    fun getedit(): LiveData<Int> {
        return _edit
    }

    fun toggle()
    {
        _edit.value=1- _edit.value!!
    }

}