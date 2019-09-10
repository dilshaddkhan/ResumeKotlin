import com.google.gson.annotations.SerializedName


/**
 * this data class is the model class for the all Experience information of all company  of resume
 */
data class Experiences (

	@SerializedName("experience") val experience : List<Experience>
)