package br.pucpr.authserver

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonSerialize

class User (
    var id: Long?,
    var email: String,
    var password: String,
    var name : String
)