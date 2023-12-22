package com.capstone_ch2_ps374.realone.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object VolunteerLayout : Screen("volunteer")
    object VolunteerRegister : Screen("volunteer/register")
    object OrgGenerateQR : Screen("org/generate-qr")
    object VolunteerDetailEvent : Screen("volunteer/detail/{eventId}") {
        fun createRoute(eventId: String) = "volunteer/detail/$eventId"
    }
    object VolunteerParticipateForm : Screen("volunteer/participate/{eventId}") {
        fun createRoute(eventId: String) = "volunteer/participate/$eventId"
    }

    object VolunteerForm : Screen("volunteer/form")

    object AddEventForm : Screen("organization/form")

    object OrganizationHome : Screen("organization/home")

    object VolunteerParticipatePresence : Screen("volunteer/participate/{eventId}/{presenceId}") {
        fun createRoute(eventId: String, presenceId: String) = "volunteer/participate/$eventId/$presenceId"
    }
}

