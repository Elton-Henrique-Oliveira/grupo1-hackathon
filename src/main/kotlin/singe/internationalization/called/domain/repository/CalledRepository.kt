package singe.internationalization.called.domain.repository

import singe.internationalization.called.domain.entities.Called

interface CalledRepository{

    fun createCalled(called: Called): Called

    fun updateCalled(called: Called): Called

    fun getCalled(): List<Called>
}