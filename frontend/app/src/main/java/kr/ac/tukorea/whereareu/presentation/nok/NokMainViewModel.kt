package kr.ac.tukorea.whereareu.presentation.nok

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kr.ac.tukorea.whereareu.R

class NokMainViewModel: ViewModel() {
    private val _currentNavigationDestination = MutableStateFlow(R.id.nokHomeFragment)
    val currentNavigationDestination = _currentNavigationDestination.asStateFlow()

    fun setCurrentNavigationDestination(destination: Int){
        _currentNavigationDestination.value = destination
    }

    sealed interface NavigateEvent {
        data object Home : NavigateEvent
        data object Setting : NavigateEvent
        data object MeaningfulPlace : NavigateEvent
        data object LocationHistory : NavigateEvent
        data object SafeArea : NavigateEvent

        data object SafeAreaDetail: NavigateEvent

        data object SafeAreaSetting: NavigateEvent
        data class HomeState(val isPredicted: Boolean, val isPredictDone: Boolean) : NavigateEvent
    }
}