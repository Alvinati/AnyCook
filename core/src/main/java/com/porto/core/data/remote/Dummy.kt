package com.porto.core.data.remote

import com.porto.core.data.recipe.entity.RecipeEntity
import com.porto.core.domain.common.module.ImageItem
import com.porto.core.domain.recipe.models.Ingredients
import com.porto.core.domain.recipe.models.Recipe
import com.porto.core.domain.user.models.User

data class Dummy (
        val userToken : String = "asdasdefasfkahsfyefhaksfasdaefa2328sjda73ad"
)
{
    fun createUser() : User {
        return  User(
                1,
                "John Doe",
                "johndoe@mail.co",
                "+62812345678",
                "https://pinotmasters.sk/wp-content/uploads/2014/10/speaker-3.jpg")
    }

    fun createListRecipe(size : Int, page: Int) : List<RecipeEntity> {
        val recipes = mutableListOf<RecipeEntity>()
        val last = size*page
        val first = (size*page) - size

        for (i in first..last) {
            recipes.add(getRecipe(i))
        }

        return recipes
    }

    fun getRecipe(id: Int) : RecipeEntity {
        return RecipeEntity(
                id = id,
                name = "Mie Ayam $id",
                thumbnailUrl = "https://www.masakapahariini.com/wp-content/uploads/2019/08/mie-ayam-kecap-620x440.jpg",
                serveMinutes = 20+id,
                countLike = 35+id,
                cookingSteps = null,
                ingredients = null,
                imageUrls = null
        )
    }

    fun getRecipeDetails(id: Int) : RecipeEntity {
        val ingredient1 = Ingredients(
                1,
                "Onion",
                "https://lh3.googleusercontent.com/proxy/PT5niJ68qEIYpBjkX2tId9fxKMCEFJuAKGqFZnEddyMR3PI0Eig3XR5Fa2xnkQ6rIhLH_97mrNCYLVfdLAuCX4x0QTQJdNdCo96zzyvXWH08LHWwwuJFoAYa90uOroszBIUcAPeCRLammwp6DaFoBk8CK68Vv1M",
                "1")
        val ingredient2 = Ingredients(
                2,
                "Chili",
                "https://image.flaticon.com/icons/png/512/184/184524.png",
                "3 or according your taste")
        val ingredient3 = Ingredients(
                3,
                "Noodles",
                "https://i.pinimg.com/originals/de/3a/ec/de3aecea64ddb6cfbe51b3160cae30a3.png",
                "one pack of noodles")
        val ingredients = listOf(ingredient1, ingredient2, ingredient3)

        val image1 = ImageItem(1, "https://www.masakapahariini.com/wp-content/uploads/2019/08/mie-ayam-kecap-620x440.jpg")
        val image2 = ImageItem(2, "https://awsimages.detik.net.id/community/media/visual/2019/11/08/31ccb56c-5b9d-406c-935a-07570df3cda6.jpeg?w=700&q=90")
        val image3 = ImageItem(3, "https://img.kurio.network/E0_KAdvD6wo13S7_jNxszraIGzo=/400x400/filters:quality(80):format(jpeg)/https://kurio-img.kurioapps.com/20/05/02/8f6fa69d-c9e3-496e-ba76-795ea0085cf5.jpeg")
        val images = listOf(image1, image2,image3)

        val cookingSteps = "1. Cook the noodle, \n2. Chop all seasoning ingredients, " +
                "\n3. Stir fry the seasoning, add salt and sugar then add cooked noodle to the pan, stir it evenly"

        val recipeEntity = getRecipe(id)

        return recipeEntity.apply {
            this.ingredients = ingredients
            this.cookingSteps = cookingSteps
            this.imageUrls = images
        }
    }
}