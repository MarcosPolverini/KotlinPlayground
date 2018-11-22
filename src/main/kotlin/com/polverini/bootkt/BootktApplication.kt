package com.polverini.bootkt

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories

@SpringBootApplication
@EnableJdbcRepositories("com.polverini.bootkt.repository")
class BootktApplication

fun main(args: Array<String>) {
    runApplication<BootktApplication>(*args)
}
