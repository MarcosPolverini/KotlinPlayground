package com.polverini.bootkt.service

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.Exception

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException(message:String) : Exception(message){

    constructor() : this("Object not found")
}