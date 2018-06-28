package br.com.vinipaulino.challengmovie.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.anestech.axcalc.AppConstants
import br.com.vinipaulino.challengmovie.R
import br.com.vinipaulino.challengmovie.model.Movies
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_resume_movie.*
import kotlinx.android.synthetic.main.layout_item_movies.*
import java.text.SimpleDateFormat

class ResumeMovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resume_movie)

        //Capturar o filme aqui
        val intent = intent
        val movie_id: String = intent.getStringExtra("movie_id")

        //Recuperar meu objeto
        TODO("Capturar o objeto atravez do Id se ")


        Picasso.with(this)
                .load(AppConstants.IMG_BASE_URL + movie.backdrop_path)
                .resize(1100, 700)
                .centerCrop()
                .into(img_resume_movie)
        txt_title_movie.text = movie.title
        txt_sipnose.text = movie.overview


        val dataEmUmFormato = movie.release_date
        val formato = SimpleDateFormat("yyyy-MM-dd")
        val data = formato.parse(dataEmUmFormato)
        formato.applyPattern("dd/MM/yyyy")
        val dataFormatada = formato.format(data)
        txt_datalancamento.text =  dataFormatada
        txt_genero.text = movie.id.toString()
        // Fazer o bind dos campos e setar na tela

    }
}
