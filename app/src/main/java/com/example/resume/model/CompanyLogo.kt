import com.example.resume.R


// creating the data class to provide the related to the Experience2
data class CompanyLogo(
    var image: Int
)


// initialising the mock data to display on the screen.
// with this we just need to call the Supplier object with the value experiences
object CompanyLogoSupplier {


    // adding the mock data to show on the adapter
    val logo = listOf<CompanyLogo>(

        CompanyLogo(
            R.drawable.agranee_logo
        ), CompanyLogo(
            R.drawable.konnection_app_logo
        )
        , CompanyLogo(
            R.drawable.augmont_logo
        )
        , CompanyLogo(
            R.drawable.logo_poleng
        )

    )

}
