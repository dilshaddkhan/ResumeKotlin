import com.google.gson.annotations.SerializedName

data class Projects (

	@SerializedName("projectName") val projectName : String,
	@SerializedName("playStoreLink") val playStoreLink : String,
	@SerializedName("technologies") val technologies : String
)