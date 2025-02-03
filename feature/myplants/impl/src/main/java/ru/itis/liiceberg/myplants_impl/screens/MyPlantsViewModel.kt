package ru.itis.liiceberg.myplants_impl.screens

import dagger.hilt.android.lifecycle.HiltViewModel
import ru.itis.liiceberg.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class MyPlantsViewModel @Inject constructor(

): BaseViewModel<MyPlantsState, MyPlantsEvent, MyPlantsAction>(
    MyPlantsState()
){

}