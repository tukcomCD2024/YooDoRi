package kr.ac.tukorea.whereareu.presentation.nok

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kr.ac.tukorea.whereareu.R

class NokMainViewModel: ViewModel() {
    private val _currentNavigationDestination = MutableStateFlow(R.id.nokHomeFragment)
    val currentNavigationDestination = _currentNavigationDestination.asStateFlow()

    private val _navigateMenuEvent = MutableSharedFlow<NavigateMenuEvent>()
    val navigateEvent = _navigateMenuEvent.asSharedFlow()

    var currentNavigateMenuEvent = MutableStateFlow<NavigateMenuEvent>(NavigateMenuEvent.Home(R.id.nokHomeFragment))
    var isNavigationEventDuplicate = MutableStateFlow(false)

    fun setCurrentNavigationDestination(destination: Int){
        _currentNavigationDestination.value = destination
    }

    fun setIsNavigationEventDuplicate(isNavigationEventDuplicate: Boolean){
        this.isNavigationEventDuplicate.value = isNavigationEventDuplicate
    }

    sealed class NavigateMenuEvent {
        data class Home(val destination: Int) : NavigateMenuEvent()
        data object Setting : NavigateMenuEvent()
        data class MeaningfulPlace(val destination: Int) : NavigateMenuEvent()
        data class LocationHistory(val destination: Int) : NavigateMenuEvent()
        data class SafeArea(val destination: Int) : NavigateMenuEvent()
    }

    fun eventNavigate(event: NavigateMenuEvent) {
        viewModelScope.launch {
            isNavigationEventDuplicate.value = event::class == currentNavigateMenuEvent::class

            Log.d("isNavigationEventDuplicate", isNavigationEventDuplicate.value.toString())
            //navigateMenuEventToString.value = event.toString()

            currentNavigateMenuEvent.value = event
            _navigateMenuEvent.emit(event)
        }
    }
}