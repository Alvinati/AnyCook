package com.porto.core.domain.user.models

sealed class InitialPage {
    object Authentication : InitialPage()

    object Home : InitialPage()
}