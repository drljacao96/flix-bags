package com.drbrosdev.flix_bags.data

import androidx.datastore.core.DataStore
import com.drbrosdev.flix_bags.Baggage
import com.drbrosdev.flix_bags.Ticket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import java.io.IOException

class TicketDataSource(
    private val dataStore: DataStore<Ticket>
) {
    val ticketFlow = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                //log
                emit(Ticket.getDefaultInstance())
            } else {
                //Make sure to rethrow the exception!!
                throw exception
            }
        }

    suspend fun updateTicketCode(code: String) {
        dataStore.updateData { ticket ->
            ticket.toBuilder()
                .clear()
                .setTicketCode(code)
                .build()
        }
    }

    suspend fun addBaggageCode(code: String) {
        val baggage = Baggage.newBuilder()
            .setBaggageCode(code)
            .build()
        dataStore.updateData { ticket ->
            ticket.toBuilder()
                .addBaggageItems(baggage)
                .build()
        }
    }

    suspend fun addBaggageCodes(codes: List<String>) {
        val baggage = withContext(Dispatchers.IO) {
            codes.map {
                Baggage.newBuilder()
                    .setBaggageCode(it)
                    .build()
            }
        }
        dataStore.updateData { ticket ->
            ticket.toBuilder()
                .clearBaggageItems()
                .addAllBaggageItems(baggage)
                .build()
        }
    }

    suspend fun deleteBaggageCode(code: String) {
        val baggageCodeIndex = withContext(Dispatchers.IO) {
            val dataSnapshot = dataStore.data.first()
            dataSnapshot.baggageItemsList
                .map { it.baggageCode }
                .indexOf(code)
        }

        dataStore.updateData { ticket ->
            ticket.toBuilder()
                .removeBaggageItems(baggageCodeIndex)
                .build()
        }
    }

    suspend fun clearData() {
        dataStore.updateData {
            it.toBuilder().clear().build()
        }
    }
}