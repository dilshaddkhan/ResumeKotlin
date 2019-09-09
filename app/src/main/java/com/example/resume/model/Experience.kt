import com.google.gson.annotations.SerializedName

data class Experience (

	@SerializedName("company") val company : String,
	@SerializedName("position") val position : String,
	@SerializedName("website") val website : String,
	@SerializedName("startDate") val startDate : String,
	@SerializedName("endDate") val endDate : String,
	@SerializedName("responsibilities") val responsibilities : String
)