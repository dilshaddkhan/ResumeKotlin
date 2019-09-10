import com.google.gson.annotations.SerializedName

data class Project (

	@SerializedName("projectName") val projectName : String,
	@SerializedName("playStoreLink") val playStoreLink : String,
	@SerializedName("technologies") val technologies : String,
	@SerializedName("companylogo") val companylogo : String,
	@SerializedName("URLS") val uRLS : URLS
)