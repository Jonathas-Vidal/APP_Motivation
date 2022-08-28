package com.jonathasdev.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.jonathasdev.motivation.infra.MotivationConstants
import com.jonathasdev.motivation.R
import com.jonathasdev.motivation.data.Mock
import com.jonathasdev.motivation.infra.SecurityPreferences
import com.jonathasdev.motivation.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    private var filter: Int = MotivationConstants.FILTER.ALL
    private val mock: Mock = Mock()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Esconde a Barra de Nav.
        supportActionBar?.hide()

        handleFilter(R.id.img_all)
        handleNextPhrase()

        //Evento de Gerar Frases e filtrar por tipos
        binding.btnFrase.setOnClickListener(this)
        binding.imgAll.setOnClickListener(this)
        binding.imgHappy.setOnClickListener(this)
        binding.imgSunny.setOnClickListener(this)
        binding.textUsername.setOnClickListener(this)
    }
    override fun onResume() {
        super.onResume()
        handleUsername()
    }
    override fun onClick(view: View) {
        val id : Int = view.id
        val listId = listOf(
            R.id.img_all,
            R.id.img_happy,
            R.id.img_sunny
        )
        if(id in listId) {
            handleFilter(id)
        }else if(id == R.id.btn_frase) {
            handleNextPhrase()
        }else if(id == R.id.text_username) {
            startActivity(Intent(this,UserActivity::class.java))
        }
    }
    private fun handleNextPhrase() {
        binding.textPhrase.text = mock.getPhrase(filter,Locale.getDefault().language)
    }
    private fun handleFilter(id: Int) {

        binding.imgAll.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imgHappy.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imgSunny.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))

        when (id) {
            R.id.img_all -> {
                binding.imgAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
                filter = MotivationConstants.FILTER.ALL
            }
            R.id.img_happy -> {
                binding.imgHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
                filter = MotivationConstants.FILTER.HAPPY
            }
            R.id.img_sunny -> {
                binding.imgSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                filter = MotivationConstants.FILTER.SUNNY
            }
        }
    }
    private fun handleUsername() {
        val name = SecurityPreferences(this).getUsername(MotivationConstants.KEY.USER_NAME)
        val string = getString(R.string.txt_hello)
        binding.textUsername.text = "$string, $name!"
    }
}
