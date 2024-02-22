package kr.ac.tukorea.whereareu.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kr.ac.tukorea.whereareu.WhereAreUApplication
import kr.ac.tukorea.whereareu.data.model.LocationInfo
import kr.ac.tukorea.whereareu.data.model.sensor.Accelerometer
import kr.ac.tukorea.whereareu.data.repository.HomeRepository
import kr.ac.tukorea.whereareu.data.repository.HomeRepositoryImpl
import kr.ac.tukorea.whereareu.util.onError
import kr.ac.tukorea.whereareu.util.onSuccess
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepositoryImpl
): ViewModel() {


    fun postLocationInfo(request: LocationInfo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.postLocationInfo(request).onSuccess {
                Log.d("success", it.toString())
            }.onError {
                Log.d("error", it.toString())
            }
        }
    }
}