package com.drbrosdev.flix_bags.data

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.drbrosdev.flix_bags.Ticket
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream


object TicketSerializer: Serializer<Ticket> {
    override val defaultValue: Ticket
        get() = Ticket.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): Ticket {
        try {
            return Ticket.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto store", exception)
        }
    }

    override suspend fun writeTo(t: Ticket, output: OutputStream) {
        t.writeTo(output)
    }

}