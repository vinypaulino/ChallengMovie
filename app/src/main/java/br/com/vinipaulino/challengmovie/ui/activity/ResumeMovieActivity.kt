package br.com.vinipaulino.challengmovie.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.anestech.axcalc.AppConstants
import br.com.vinipaulino.challengmovie.R
import br.com.vinipaulino.challengmovie.model.Movies
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_resume_movie.*
import kotlinx.android.synthetic.main.layout_item_movies.*

class ResumeMovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resume_movie)

        //Capturar o filme aqui
        val intent = intent
        val movie: Movies = intent.getSerializableExtra("movie") as Movies

        txt_title_movie.text = movie.original_title

        Picasso.with(this)
                .load(AppConstants.IMG_BASE_URL + movie.backdrop_path)
                .resize(1100, 500)
                .centerCrop()
                .into(img_resume_movie)
        // Fazer o bind dos campos e setar na tela

    }
}
