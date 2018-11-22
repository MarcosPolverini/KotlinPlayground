package com.polverini.bootkt.service

import com.polverini.bootkt.model.Book
import com.polverini.bootkt.repository.BookRepository
import com.polverini.bootkt.repository.model.BookModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BookService {

    @Autowired
    lateinit var bookRepository: BookRepository

    fun save(book: Book) = bookRepository.save(toModel(book))

    fun findAll(): List<Book> = bookRepository.findAll().map { model -> Book(model.id, model.isbn, model.version, model.title, model.author) }

    fun findById(idBook: Long): Book {
        val model = bookRepository.findById(idBook).orElseThrow { NotFoundException("Book not Found") }
        return Book(model.id, model.isbn, model.version, model.title, model.author)
    }

    fun remove(idBook: Long) {
        val book = bookRepository.findById(idBook)
        bookRepository.delete(book.orElseThrow { NotFoundException("Book not found") })
    }

    fun update(book: Book) {
        bookRepository.findById(book.id).orElseThrow { NotFoundException("Book not found") }
        bookRepository.save(toModel(book))
    }

    private fun toModel(book: Book): BookModel = BookModel(book.id, book.isbn, book.version, book.title, book.author)
}