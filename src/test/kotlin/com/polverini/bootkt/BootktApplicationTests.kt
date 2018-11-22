package com.polverini.bootkt

import com.polverini.bootkt.api.BookControllerTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(value = [BookControllerTest::class])
class BootktApplicationTests {
}
