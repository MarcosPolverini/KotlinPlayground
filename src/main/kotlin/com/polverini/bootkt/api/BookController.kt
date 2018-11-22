package com.polverini.bootkt.api

import com.polverini.bootkt.model.Book
import com.polverini.bootkt.service.BookService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/book")
@Api(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, value = "Book controller", description = "A Playground Controller to Test =)")
class BookController {

    @Autowired
    lateinit var service: BookService

    @GetMapping(produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    @ApiOperation(value = "List all books", notes = "List all books inside a database")
    @ApiResponse(response = Book::class, code = RESPONSE_OK, message = "default success response code", responseContainer = "List")
    @ResponseBody
    fun listAll(): List<Book> = service.findAll()

    @GetMapping(path = ["/{idx}"],produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    @ApiOperation(value = "Search book by ID", notes = "If not found an error will be throw")
    @ApiResponse(response = Book::class, code = RESPONSE_OK, message = "default success response code")
    fun findById(@PathVariable("idx") idx: Long): Book {
        return service.findById(idx)
    }

    @RequestMapping(method = [RequestMethod.POST], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    @ResponseBody
    @ApiOperation(value = "Create new book in the library")
    @ApiResponse(code = RESPONSE_CREATED, message = "default success response code")
    fun create(@RequestBody book: Book): ResponseEntity<Any> {
        service.save(book)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @DeleteMapping
    @ApiOperation(value = "Remove a book by index", notes = "If the index is invalid an error will be throw")
    @ApiResponse(code = RESPONSE_OK, message = "default success response code")
    fun remove(@RequestParam idx: Long) = service.remove(idx)

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    @ApiOperation(value = "Update book information's ", notes = "If the index is invalid an error will be throw")
    @ApiResponse(code = RESPONSE_OK, message = "default success response code")
    fun update(@RequestBody book: Book) = service.update(book)
}