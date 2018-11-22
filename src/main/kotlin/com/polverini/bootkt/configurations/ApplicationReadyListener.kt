package com.polverini.bootkt.configurations

import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class ApplicationReadyListener : ApplicationListener<ApplicationReadyEvent>{

    private val sql : String = "CREATE TABLE IF NOT EXISTS book (id bigint auto_increment primary key,isbn numeric(10),version int,title varchar(500),author varchar(500))"

    /**
     * Create book table in database.
     */
    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        event.applicationContext.getBean(JdbcTemplate::class.java).execute(sql)
    }
}