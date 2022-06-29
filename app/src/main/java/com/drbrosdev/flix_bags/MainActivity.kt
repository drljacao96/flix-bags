package com.drbrosdev.flix_bags

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.drbrosdev.flix_bags.data.TicketSerializer
import com.drbrosdev.flix_bags.data.TokenSerializer

val Context.ticketDataStore: DataStore<Ticket> by dataStore(
    fileName = "ticket_data",
    serializer = TicketSerializer
)

val Context.tokenDataStore: DataStore<Token> by dataStore(
    fileName = "token_data",
    serializer = TokenSerializer
)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}