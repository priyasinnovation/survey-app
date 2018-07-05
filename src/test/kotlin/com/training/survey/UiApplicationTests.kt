package com.training.survey

import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest
@TestPropertySource(properties =
["amazon.dynamodb.endpoint=http://localhost:8000/",
"amazon.aws.accesskey=test1",
"amazon.aws.secretkey=test231"])
class UiApplicationTests {

    @Test
    fun contextLoads() {
    }

}
