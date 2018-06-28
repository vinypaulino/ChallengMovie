package br.com.vinipaulino.challengmovie.ui.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import br.com.anestech.axreg_droid.retrofit.response.CallbackResponse
import br.com.anestech.axreg_droid.retrofit.webclient.MovieWebClient
import br.com.vinipaulino.challengmovie.R
import br.com.vinipaulino.challengmovie.model.Movies
import br.com.vinipaulino.challengmovie.ui.adapter.MovieAdapter
import br.com.vinipaulino.challengmovie.util.AndroidUtils
import br.com.vinipaulino.challengmovie.util.ListItemDecorations
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_premier_movie.*
import org.jetbrains.anko.toast

class PremierMovieActivity : AppCompatActivity() {

    lateinit var myCustomAdapter: MovieAdapter
    private lateinit var movies: MutableList<Movies>

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_premier_movie)
        configRecycleView()

        val internetOk = AndroidUtils.isNetworkAvailable(this)

        if (internetOk) {
            getMovieWebClient()
        } else {
            getMoviesBD()
        }
    }

    private fun getMoviesBD() {
        val realm = Realm.getDefaultInstance()
        movies = realm.copyFromRealm(realm.where(Movies::class.java).findAll())
        realm.close()
        myCustomAdapter = MovieAdapter(applicationContext, movies)
        recyclerView.adapter = myCustomAdapter
        progressBar.visibility = View.GONE
    }

    private fun getMovieWebClient() {
        MovieWebClient().getMoviesPremier(object : CallbackResponse<List<Movies>> {
            override fun success(response: List<Movies>) {

                val realm = Realm.getDefaultInstance()
                realm.executeTransaction {
                    realm.insertOrUpdate(response)
                }
                realm.close()

                myCustomAdapter = MovieAdapter(applicationContext, response)

                //Gravar a lista de Filmes no Banco de Dados aqui !!!!!!
                recyclerView.setAdapter(myCustomAdapter)

                progressBar.visibility = View.GONE
            }

            override fun failure(throwable: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun responseFailure() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    private fun configRecycleView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(ListItemDecorations(20))
        recyclerView.setHasFixedSize(true)
    }
}
