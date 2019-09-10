
import retrofit2.Call
import retrofit2.http.GET

// this is a interface to get the result
interface ResumeServiceInterface {

    @GET("MyResumeJSON/about")
    fun getAbout():Call<About>

    @GET("MyResumeJSON/experiences")
    fun getExperience():Call<Experiences>

    @GET("MyResumeJSON/projects")
    fun getProject():Call<Project>
}