package kr.ac.tukorea.whereareu.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kr.ac.tukorea.whereareu.data.model.home.GetLocationInfoResponse
import kr.ac.tukorea.whereareu.data.repository.home.NokHomeRepository
import kr.ac.tukorea.whereareu.data.repository.home.NokHomeRepositoryImpl
import kr.ac.tukorea.whereareu.util.onError
import kr.ac.tukorea.whereareu.util.onException
import kr.ac.tukorea.whereareu.util.onFail
import kr.ac.tukorea.whereareu.util.onSuccess
import javax.inject.Inject

@HiltViewModel
class NokHomeViewModel @Inject constructor(
    val repository: NokHomeRepositoryImpl
): ViewModel() {

    private val _dementiaLocation = MutableSharedFlow<GetLocationInfoResponse>(replay = 1)
    val dementiaLocation = _dementiaLocation.asSharedFlow()
    fun getDementiaLocation(dementiaKey: String){
        viewModelScope.launch {
            repository.getDementiaLocationInfo(dementiaKey).onSuccess {
                _dementiaLocation.emit(it)
            }.onError {
                Log.d("error", it.toString())
            }.onException {
                Log.d("exception", it.toString())
            }.onFail {
                Log.d("fail", it.toString())
            }
        }
    }
}