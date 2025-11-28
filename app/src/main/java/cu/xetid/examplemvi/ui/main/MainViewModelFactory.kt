package cu.xetid.examplemvi.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cu.xetid.examplemvi.data.api.RestApi
import cu.xetid.examplemvi.data.repository.MainRepository

class MainViewModelFactory(private val restApi: RestApi) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java))
            return MainActivityViewModel(MainRepository(restApi)) as T

        throw IllegalArgumentException("Unknown class name")

    }
}