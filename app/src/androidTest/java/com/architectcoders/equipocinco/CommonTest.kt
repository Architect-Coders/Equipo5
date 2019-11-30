package com.architectcoders.equipocinco

import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertFalse
import org.junit.Test

class CommonTest {

    @Test
    fun `is_Movie_DB_API_Key_retrieved_correctly`() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val apiKey: String? = context.getString(R.string.movie_db_api_key)
        assertFalse(apiKey.isNullOrBlank())
    }
}