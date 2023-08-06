package com.example.clubreservationcalendar

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform