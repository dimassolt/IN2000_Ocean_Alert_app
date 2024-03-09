package no.uio.ifi.in2000.team11.havvarselapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import no.uio.ifi.in2000.team11.havvarselapp.data.MetAlertsDataSource
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MetAlertsDataSourceTest {

    private val dataSource: MetAlertsDataSource = MetAlertsDataSource()

    @Test
    fun fetchData() {
        assertEquals(1,1)
        runBlocking {
            dataSource.fetchData()
        }
    }
}