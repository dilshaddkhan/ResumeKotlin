import com.google.gson.annotations.SerializedName


/**
 * this data class is the model class for the each Experience information of a company  of resume
 */
data class Experience (

	@SerializedName("company") val company : String,
	@SerializedName("position") val position : String,
	@SerializedName("website") val website : String,
	@SerializedName("startDate") val startDate : String,
	@SerializedName("endDate") val endDate : String,
	@SerializedName("companylogo") val companylogo : String,
	@SerializedName("responsibilities") val responsibilities : String
)