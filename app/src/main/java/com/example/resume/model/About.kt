import com.google.gson.annotations.SerializedName


/**
 * this data class is the model class for the About information of resume
 */
data class About (

	@SerializedName("name") val name : String,
	@SerializedName("designation") val designation : String,
	@SerializedName("email") val email : String,
	@SerializedName("phone") val phone : String,
	@SerializedName("profilepic") val profilepic : String,
	@SerializedName("backgroundimage") val backgroundimage : String,
	@SerializedName("linkedInProfile") val linkedInProfile : String,
	@SerializedName("gitHUbProfile") val gitHUbProfile : String,
	@SerializedName("summary") val summary : String,
	@SerializedName("skills") val skills : String,
	@SerializedName("interests") val interests : String
)