import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PersonalInfo(
    val name: String,
    val nickname: String,
    val hometown: String,
    val age: String,
    val gender: String,
    val color: String,
    val food: String,
    val comfort: String? = null,
    val hobbies: String,
    val sports: String,
    val dateAdded: String
) : Parcelable
