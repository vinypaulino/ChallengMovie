package br.com.anestech.axreg_droid.retrofit.webclient

import android.util.Log
import br.com.anestech.axcalc.AppConstants
import br.com.anestech.axreg_droid.retrofit.RetrofitInicializer
import br.com.anestech.axreg_droid.retrofit.response.CallbackResponse
import br.com.vinipaulino.challengmovie.model.MovieResponse
import br.com.vinipaulino.challengmovie.model.Movies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by vinicius on 26/06/18.
 */
class MovieWebClient {
    fun getMoviesPremier(callbackResponse: CallbackResponse<List<Movies>> ) {

        val call = RetrofitInicializer().movieService().getMoviesPremier(AppConstants.API_KEY)

        call.enqueue(object : Callback<MovieResponse> {

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response?.isSuccessful!!){
                    val listOfMovies: List<Movies> = response.body()?.results!!
                    callbackResponse.success(listOfMovies)
                } else if (response?.code() == 401){
                    callbackResponse.responseFailure()
                }
            }

            override fun onFailure(call: Call<MovieResponse>?, t: Throwable?) {
//                progressBar.visibility = View.GONE
                Log.e("OnFailure", t.toString())
            }
        })
    }

//    fun passwordReset(email: String, callbackResponse: CallbackResponse<String>){
//
//        val call = RetrofitInicializer().loginService().passwordReset(email)
//        call.enqueue(object : retrofit2.Callback<String>{
//
//            override fun onResponse(call: Call<String>?, response: Response<String>?){
//                if (response?.message() == "OK"){
//                    callbackResponse.success(response = response.body()!!)
//                } else {
//                    callbackResponse.responseFailure()
//                }
//            }
//
//            override fun onFailure(call: Call<String>?, t: Throwable?){
//                callbackResponse.failure(t!!)
//                Log.e("onFailureteste", "Falha na comunicação com o servidor ${call.toString()}")
//                t?.printStackTrace()
//            }
//        })
//    }
}



