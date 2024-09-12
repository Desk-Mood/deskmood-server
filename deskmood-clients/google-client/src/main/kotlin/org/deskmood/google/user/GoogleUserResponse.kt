package org.deskmood.google.user

import org.deskmood.datetime.DateTimeUtil

data class GoogleUserResponse(
    val resourceName: String,
    val names: List<GoogleUserName>,
    val photos: List<GoogleUserPhoto>,
    val genders: List<GoogleUserGender>,
    val birthdays: List<GoogleUserBirthday>,
    val emailAddresses: List<GoogleUserEmailAddress>
) {

    fun toGoogleUserProfile(): GoogleUserProfile {
        return GoogleUserProfile(
            id = resourceName.split("/")[1],
            name = names[0].displayName,
            profileImage = photos[0].url,
            email = emailAddresses[0].value,
            gender = genders[0].value,
            birth = DateTimeUtil.toLocalDate(
                String.format(
                    "%04d-%02d-%02d",
                    birthdays[0].date.year,
                    birthdays[0].date.month,
                    birthdays[0].date.day
                )
            )
        )
    }
}

data class GoogleUserName(
    val displayName: String
)

data class GoogleUserPhoto(
    val url: String
)

data class GoogleUserGender(
    val value: String
)

data class GoogleUserBirthday(
    val date: GoogleUserDate
)

data class GoogleUserDate(
    val year: Int,
    val month: Int,
    val day: Int
)

data class GoogleUserEmailAddress(
    val value: String
)
