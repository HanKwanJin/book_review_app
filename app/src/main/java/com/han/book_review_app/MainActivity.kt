package com.han.book_review_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.han.book_review_app.adapter.BookAdapter
import com.han.book_review_app.api.BookService
import com.han.book_review_app.databinding.ActivityMainBinding
import com.han.book_review_app.model.BestSellerDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: BookAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://book.interpark.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val bookService = retrofit.create(BookService::class.java)
        bookService.getBestSellersBooks("4267DED3D19E06713566B536119A7EC527FEF3B7165D1FD93B48BDE0502EC900")
            .enqueue(object: Callback<BestSellerDto>{
                override fun onResponse(
                    call: Call<BestSellerDto>,
                    response: Response<BestSellerDto>
                ) {
                    //todo 성공처리
                    if(response.isSuccessful.not()){
                        return
                    }
                    response.body()?.let {
                        Log.d(TAG, it.toString())
                        it.books.forEach { book->
                            Log.d(TAG, book.toString())

                        }
                        adapter.submitList(it.books)
                    }
                }

                override fun onFailure(call: Call<BestSellerDto>, t: Throwable) {
                    //todo 실패처리
                }

            })

    }

    fun initRecyclerView(){
        adapter = BookAdapter()
        binding.bookRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.bookRecyclerView.adapter = adapter
    }
    companion object{
        private const val TAG = "MainActivity"
    }
}