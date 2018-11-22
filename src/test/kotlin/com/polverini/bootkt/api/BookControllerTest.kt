package com.polverini.bootkt.api

import com.polverini.bootkt.model.Book
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.web.client.RestClientException
import java.util.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner::class)
class BookControllerTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Test
    fun shouldListAllBook() {
        val response = restTemplate.getForEntity("/book", Array<Book>::class.java)
        Assert.assertTrue(response.statusCode == HttpStatus.OK)
        Assert.assertNotNull(response.body)
    }

    @Test
    fun shouldCreateABook() {
        val book = Book(0, IntRange(0, 1000).random().toLong(), IntRange(0, 1000).random(), "Star", "Json")
        val response = restTemplate.postForEntity("/book", book, Void::class.java)
        Assert.assertTrue(response.statusCode == HttpStatus.CREATED)
        Assert.assertNull(response.body)
    }

    @Test
    fun shouldDeleteABookUsingId() {
        val id = getBook()[0].id
        restTemplate.delete("/book", id)
        Assert.assertFalse(getBook().find { i -> i.id == id } == null)
    }

    @Test
    fun shouldUpdateExistingBook() {
        val version = 12331
        var book = getBook()[0].copy(version = version)
        restTemplate.put("/book", book)
        book = getBook().first { b -> b.id == book.id }
        Assert.assertNotNull(book)
        Assert.assertTrue(book.version == version)
    }

    @Test
    fun shouldFindBookById() {
        val id = getBook()[0].id
        val book = restTemplate.getForEntity("/book/$id", Book::class.java).body
        Assert.assertNotNull(book)
        Assert.assertTrue(book!!.id == id)
    }

    @Test
    fun shouldFailToFindNonExistentId() {
        try {
            restTemplate.getForEntity("/book/1223", Book::class.java)
        } catch (e: RestClientException) {
            println(e.message)
        }
    }

    private fun getBook(): Array<Book> {
        shouldCreateABook()
        var response = restTemplate.getForEntity("/book", Array<Book>::class.java)
        Assert.assertNotNull(response.body)
        return response.body!!
    }

    fun IntRange.random(): Int = Random().nextInt((endInclusive + 1) - start) + start
}