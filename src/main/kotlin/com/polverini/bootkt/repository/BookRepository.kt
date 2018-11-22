package com.polverini.bootkt.repository

import com.polverini.bootkt.repository.model.BookModel
import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<BookModel,Long>