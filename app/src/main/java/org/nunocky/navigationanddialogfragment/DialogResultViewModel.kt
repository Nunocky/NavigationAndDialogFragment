package org.nunocky.navigationanddialogfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DialogResultViewModel : ViewModel() {
    val result = MutableLiveData<YesNoDialogFragment.Result>(YesNoDialogFragment.Result.Init)
}