package com.example.mobiledev_app

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.mobiledev_app.database.Result
import com.example.mobiledev_app.database.ResultDatabase
import com.example.mobiledev_app.database.ResultDatabaseDao
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ResultDatabaseTest {

    private lateinit var resultDatabaseDao: ResultDatabaseDao
    private lateinit var db: ResultDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        db = Room.inMemoryDatabaseBuilder(context, ResultDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        resultDatabaseDao = db.resultDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetResult() {
        val result = Result(score = 5, userName = "Csaba")
        resultDatabaseDao.insert(result)
        val lastResult = resultDatabaseDao.getLatestResult()
        assertEquals(lastResult?.score, 5)
        assertEquals(lastResult?.userName, "Csaba")
    }
}
