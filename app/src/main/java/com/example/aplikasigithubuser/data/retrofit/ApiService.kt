import com.example.aplikasigithubuser.data.response.GithubResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    fun searchUsers(
        @Query("q") username: String
    ): Call<GithubResponse>
}
