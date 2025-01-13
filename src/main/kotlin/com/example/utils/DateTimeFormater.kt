package com.example.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

private const val DATE_FORMAT = "dd-MM-yyyy"

fun LocalDate.toDatabaseString(): String {
    val formatter = DateTimeFormatter.ofPattern("DATE_FORMAT")
    return formatter.format(this)
}