package com.food.oder.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json


@Entity
data class FoodDetails(
    @PrimaryKey
    val id : Int = 0,
    val name: String
)

data class Food(
    @Json(name = "meals")
    var meals: List<Meal>
)

data class Meal(
    @Json(name = "idMeal")
    var idMeal: String,
    @Json(name = "strMeal")
    var strMeal: String,
    @Json(name = "strMealThumb")
    var strMealThumb: String
)

data class RanDomFood(
    @Json(name = "meals")
    var meals: List<MealRanDom>
)

data class MealRanDom(
    @Json(name = "dateModified")
    var dateModified: Any? = null,
    @Json(name = "idMeal")
    var idMeal: String? = "",
    @Json(name = "strArea")
    var strArea: String? = "",
    @Json(name = "strCategory")
    var strCategory: String? = "",
    @Json(name = "strCreativeCommonsConfirmed")
    var strCreativeCommonsConfirmed: Any? = null,
    @Json(name = "strDrinkAlternate")
    var strDrinkAlternate: Any? = null,
    @Json(name = "strImageSource")
    var strImageSource: Any? = null,
    @Json(name = "strIngredient1")
    var strIngredient1: String? = "",
    @Json(name = "strIngredient10")
    var strIngredient10: String? = "",
    @Json(name = "strIngredient11")
    var strIngredient11: String? = "",
    @Json(name = "strIngredient12")
    var strIngredient12: String? = "",
    @Json(name = "strIngredient13")
    var strIngredient13: String? = "",
    @Json(name = "strIngredient14")
    var strIngredient14: String? = "",
    @Json(name = "strIngredient15")
    var strIngredient15: String? = "",
    @Json(name = "strIngredient16")
    var strIngredient16: String? = "",
    @Json(name = "strIngredient17")
    var strIngredient17: String? = "",
    @Json(name = "strIngredient18")
    var strIngredient18: String? = "",
    @Json(name = "strIngredient19")
    var strIngredient19: String? = "",
    @Json(name = "strIngredient2")
    var strIngredient2: String? = "",
    @Json(name = "strIngredient20")
    var strIngredient20: String? = "",
    @Json(name = "strIngredient3")
    var strIngredient3: String? = "",
    @Json(name = "strIngredient4")
    var strIngredient4: String? = "",
    @Json(name = "strIngredient5")
    var strIngredient5: String? = "",
    @Json(name = "strIngredient6")
    var strIngredient6: String? = "",
    @Json(name = "strIngredient7")
    var strIngredient7: String? = "",
    @Json(name = "strIngredient8")
    var strIngredient8: String? = "",
    @Json(name = "strIngredient9")
    var strIngredient9: String? = "",
    @Json(name = "strInstructions")
    var strInstructions: String? = "",
    @Json(name = "strMeal")
    var strMeal: String? = "",
    @Json(name = "strMealThumb")
    var strMealThumb: String? = "",
    @Json(name = "strMeasure1")
    var strMeasure1: String? = "",
    @Json(name = "strMeasure10")
    var strMeasure10: String? = "",
    @Json(name = "strMeasure11")
    var strMeasure11: String? = "",
    @Json(name = "strMeasure12")
    var strMeasure12: String? = "",
    @Json(name = "strMeasure13")
    var strMeasure13: String? = "",
    @Json(name = "strMeasure14")
    var strMeasure14: String? = "",
    @Json(name = "strMeasure15")
    var strMeasure15: String? = "",
    @Json(name = "strMeasure16")
    var strMeasure16: String? = "",
    @Json(name = "strMeasure17")
    var strMeasure17: String? = "",
    @Json(name = "strMeasure18")
    var strMeasure18: String? = "",
    @Json(name = "strMeasure19")
    var strMeasure19: String? = "",
    @Json(name = "strMeasure2")
    var strMeasure2: String? = "",
    @Json(name = "strMeasure20")
    var strMeasure20: String? = "",
    @Json(name = "strMeasure3")
    var strMeasure3: String? = "",
    @Json(name = "strMeasure4")
    var strMeasure4: String? = "",
    @Json(name = "strMeasure5")
    var strMeasure5: String? = "",
    @Json(name = "strMeasure6")
    var strMeasure6: String? = "",
    @Json(name = "strMeasure7")
    var strMeasure7: String? = "",
    @Json(name = "strMeasure8")
    var strMeasure8: String? = "",
    @Json(name = "strMeasure9")
    var strMeasure9: String? = "",
    @Json(name = "strSource")
    var strSource: String? = "",
    @Json(name = "strTags")
    var strTags: Any? = null,
    @Json(name = "strYoutube")
    var strYoutube: String? = ""
)

data class FoodCategory(
    @Json(name = "meals")
    var meals: List<MealCategory>
)

data class MealCategory(
    @Json(name = "idMeal")
    var idMeal: String,
    @Json(name = "strMeal")
    var strMeal: String,
    @Json(name = "strMealThumb")
    var strMealThumb: String
)