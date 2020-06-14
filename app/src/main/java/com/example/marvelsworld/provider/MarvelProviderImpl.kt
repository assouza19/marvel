package com.example.marvelsworld.provider

import android.util.Log
import com.example.marvelsworld.Extensions.toMd5
import com.example.marvelsworld.response.CharactersResponse
import com.example.marvelsworld.retrofit.MarvelWorldRetrofit
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*


class MarvelProviderImpl(service: MarvelWorldRetrofit) : MarvelProvider {

    private val retrofit = service.getInstance()
    private var publicKey = "88ee39d74c2dc934562fbf52241c68e2"
    private var privateKey = "dcbf6d23d351ba771d17ca2b52c9b12944047bdb"
    private var timeStamp: String =
        SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    private var hash = (timeStamp.plus(privateKey).plus(publicKey)).toMd5()

    override suspend fun getCharacter(): CharactersResponse {
        return try {
            val call = retrofit.getCharacters(timeStamp, publicKey, hash, 100, "38524, 37405, 55363, 55364")
            if (call.isSuccessful) {
                call.body() as CharactersResponse
            } else {
                val jObjError = JSONObject(call.errorBody()?.string())
                Log.d("Failure: ", jObjError.getJSONObject("error").getString("message"))
                CharactersResponse()
            }
        } catch (e: Exception) {
            Log.d("ExceptionChamada: ", e.message)
            CharactersResponse()
        }
    }
}

