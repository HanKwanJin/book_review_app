package com.han.book_review_app.api


import com.han.book_review_app.model.BestSellerDto
import com.han.book_review_app.model.SearchBookDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface BookService {
    @GET("/api/search.api?output=json")
    fun getBooksByName(
        @Query("key") apiKey: String,
        @Query("query") keyword: String
    ): Call<SearchBookDto>

    @GET("/api/bestSeller.api?output=json&categoryId=100")
    fun getBestSellersBooks(
        @Query("key") apiKey: String
    ): Call<BestSellerDto>
}