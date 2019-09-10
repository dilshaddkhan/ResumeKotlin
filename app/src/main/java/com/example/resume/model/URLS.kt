import com.google.gson.annotations.SerializedName

/**
 * this data class is the model class for the URLS of projects images
 */
data class URLS (

	@SerializedName("url1") val url1 : String,
	@SerializedName("url2") val url2 : String,
	@SerializedName("url3") val url3 : String
)