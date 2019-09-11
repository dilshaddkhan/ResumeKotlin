
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// Service builder class to initialize the URL and retrofit2 class
object ServiceBuilder {

    //Base url for the resume resource data
    private const val URL = "http://my-json-server.typicode.com/dilshaddkhan/MyResumeJSON/"
    //initializing the okhttpclien for the retrofit
    private val okHttpClient = OkHttpClient.Builder()
    // initializing the builder class for the retrofit
    private val builder = Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient.build())
    // creating the retrofit
    private val retrofit = builder.build()

    // this function will pass the interface and return the result
    fun <T> buildService(serviceType: Class<T>): T{
        return  retrofit.create(serviceType )
    }
}