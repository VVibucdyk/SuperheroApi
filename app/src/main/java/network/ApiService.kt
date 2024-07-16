package network

import model.Superhero
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/{api_key}/{id}")
    suspend fun getSuperhero(
        @Path("api_key") apiKey: String,
        @Path("id") id: String
    ): Superhero
}