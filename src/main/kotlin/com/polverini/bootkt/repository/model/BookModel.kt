package com.polverini.bootkt.repository.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("book")
data class BookModel(@Id var id: Long, var isbn: Long, var version: Int, var title: String, var author: String) {
}