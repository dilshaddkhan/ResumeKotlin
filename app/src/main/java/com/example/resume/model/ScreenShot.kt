import com.google.gson.annotations.SerializedName

/**
 * this data class is the model class for the ScreenShot of projects images
 */
data class ScreenShot (

	@SerializedName("screenShotURL1") val screenShotURL1 : String,
	@SerializedName("screenShotURL2") val screenShotURL2 : String,
	@SerializedName("screenShotURL3") val screenShotURL3 : String
)