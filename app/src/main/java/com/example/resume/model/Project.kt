import com.google.gson.annotations.SerializedName


/**
 * this data class is the model class for the each Project information of a company  of resume
 */
data class Project (

	@SerializedName("projectName") val projectName : String,
	@SerializedName("playStoreLink") val playStoreLink : String,
	@SerializedName("technologies") val technologies : String,
	@SerializedName("companylogo") val companylogo : String,
	@SerializedName("ScreenShot") val screenShot : ScreenShot
)