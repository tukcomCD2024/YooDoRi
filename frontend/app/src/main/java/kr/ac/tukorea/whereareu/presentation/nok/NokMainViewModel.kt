package kr.ac.tukorea.whereareu.presentation.nok

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.presentation.nok.home.NokHomeViewModel

class NokMainViewModel: ViewModel() {
    private val _currentNavigationDestination = MutableStateFlow(R.id.nokHomeFragment)
    val currentNavigationDestination = _currentNavigationDestination.asStateFlow()

    private val _navigateEvent = MutableSharedFlow<NavigateEvent>()
    val navigateEvent = _navigateEvent.asSharedFlow()

    val navigateEventToString = MutableStateFlow(NavigateEvent.Home.toString())

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
    }

    fun eventNavigate(event: NavigateEvent) {
        viewModelScope.launch {
            navigateEventToString.value = event.toString()
            _navigateEvent.emit(event)
        }
    }
}