package com.sayyes.easyfood.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sayyes.easyfood.pojo.Meal
import com.sayyes.easyfood.pojo.MealsByCategory
import com.sayyes.easyfood.pojo.MealsByCategoryList
import com.sayyes.easyfood.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryMealsViewModel:ViewModel() {
    val mealsLiveData = MutableLiveData<List<MealsByCategory>>()

    fun getMealsByCategory(categoryName:String){
        RetrofitInstance.api.getMealsByCategory(categoryName).enqueue(object : Callback<MealsByCategoryList>{
            override fun onResponse(
                call: Call<MealsByCategoryList>,
                response: Response<MealsByCategoryList>
            ) {
                response.body()?.let { mealsList ->
                    mealsLiveData.postValue(mealsList.meals!!)
                }
            }

            override fun onFailure(call: Call<MealsByCategoryList>, t: Throwable) {
                Log.e("CategoryMealsViewModel", t.message.toString())
            }
        })
    }

    fun observeMealsLivedata(): LiveData<List<MealsByCategory>> {
        return mealsLiveData
    }
}