package com.drbrosdev.flix_bags.data

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.drbrosdev.flix_bags.Token
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object TokenSerializer: Serializer<Token> {
    override val defaultValue: Token
        get() = Token.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): Token {
        try {
            return Token.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto store", exception)
        }
    }

    override suspend fun writeTo(t: Token, output: OutputStream) {
        t.writeTo(output)
    }
}