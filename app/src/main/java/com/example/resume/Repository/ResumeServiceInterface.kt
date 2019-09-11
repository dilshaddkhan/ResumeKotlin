
import retrofit2.Call
import retrofit2.http.GET

// this is a interface to get the result
interface ResumeServiceInterface {

    @GET("about")
    fun getAbout():Call<About>

    @GET("experiences")
    fun getExperience():Call<Experiences>

    @GET("projects")
    fun getProject():Call<Projects>
}