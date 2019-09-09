import com.example.resume.R

// creating the class ProjectsImages to provide and setting up  the data for the  AboutProjectDetails fragment
data class ProjectsImages
    (
     val logo: Int,
     val images:IntArray)

// creating the object and getting the value by mocking up the data
object ProjectsImagesSupplier {

    private val konnectionsImages = intArrayOf(R.drawable.konnections1, R.drawable.konnections2, R.drawable.konnections3)
    private val augmontImages = intArrayOf(R.drawable.augmont1, R.drawable.augmont2, R.drawable.augmont3)
    private val javaImages = intArrayOf(R.drawable.javadeeplearning1, R.drawable.javadeeplearning3, R.drawable.javadeeplearning3)

    val projects = listOf<ProjectsImages>(
        ProjectsImages(
            R.drawable.konnection_app_logo,
            konnectionsImages
        ),

        ProjectsImages(
            R.drawable.augmont_logo,
            augmontImages
        )
        , ProjectsImages(
            R.drawable.awwal_soft,
            javaImages
        )
    )

}
