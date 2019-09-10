
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// Service builder class to initialize the URL and retrofit2 class
object ServiceBuilder {
    private const val URL = "http://my-json-server.typicode.com/dilshaddkhan/"

    private val okHttpClient = OkHttpClient.Builder()

    private val builder = Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient.build())

    private val retrofit = builder.build()


    // this function will pass the interface and return the result
    fun <T> buildService(serviceType: Class<T>): T{
        return  retrofit.create(serviceType )
    }
}