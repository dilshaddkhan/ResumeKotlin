import com.google.gson.annotations.SerializedName


/**
 * this data class is the model class for al the Project information of all company  of resume
 */
data class Projects (

	@SerializedName("project") val project : List<Project>
)