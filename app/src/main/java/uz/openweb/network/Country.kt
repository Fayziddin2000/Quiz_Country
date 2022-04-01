package uz.openweb.network

import java.util.*

data class Country(
    val name: String,
    val flags: Flag,
    val capital: String,
    val callingCodes: List<String>,
    val borders: List<String>,
    val population: Int,
    val area: Double
)

data class Flag(val png: String)

