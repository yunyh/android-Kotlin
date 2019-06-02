package com.base.yun.mytestapp

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.junit.Before
import org.junit.Test
import com.fasterxml.jackson.core.JsonParser
import java.io.ByteArrayInputStream
import java.nio.charset.StandardCharsets


class ObjectMapperUnitTest() {

    val mapper = ObjectMapper()

    @Before
    fun setUp() {
        mapper.registerKotlinModule()
    }

    @Test
    fun test() {
       /* val json = "{sha : 123, author : \"asdfadfasdf\"}"
        mapper.des*/
    }
}