package model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Superhero(
    val response: String?,
    val id: String?,
    val name: String?,
    val powerstats: Powerstats?,
    val biography: Biography?,
    val appearance: Appearance?,
    val work: Work?,
    val connections: Connections?,
    val image: Image?
)

@Serializable
data class Powerstats(
    val intelligence: String?,
    val strength: String?,
    val speed: String?,
    val durability: String?,
    val power: String?,
    val combat: String?
)

@Serializable
data class Biography(
    @SerialName("full-name") val fullName: String?,
    val alterEgos: String?,
    val aliases: List<String>?,
    @SerialName("place-of-birth") val placeOfBirth: String?,
    @SerialName("first-appearance") val firstAppearance: String?,
    val publisher: String?,
    val alignment: String?
)

@Serializable
data class Appearance(
    val gender: String?,
    val race: String?,
    val height: List<String>?,
    val weight: List<String>?,
    @SerialName("eye-color") val eyeColor: String?,
    @SerialName("hair-color") val hairColor: String?
)

@Serializable
data class Work(
    val occupation: String?,
    val base: String?
)

@Serializable
data class Connections(
    @SerialName("group-affiliation") val groupAffiliation: String?,
    val relatives: String?
)

@Serializable
data class Image(
    val url: String?
)