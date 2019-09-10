import com.google.gson.annotations.SerializedName

data class Projects (

	@SerializedName("project") val project : List<Project>
)