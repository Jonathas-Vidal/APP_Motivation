package com.jonathasdev.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.jonathasdev.motivation.infra.MotivationConstants
import com.jonathasdev.motivation.R
import com.jonathasdev.motivation.infra.SecurityPreferences
import com.jonathasdev.motivation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSalvar.setOnClickListener(this)
        supportActionBar?.hide()

    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_salvar) {
            handleSave()
        }
    }

    private fun handleSave() {
        val name = binding.textInsertName.text.toString()
        if (name != "") {
            SecurityPreferences(this).saveUsername(MotivationConstants.KEY.USER_NAME, name)
            finish()
        } else {Toast.makeText(this, R.string.validation_mandatory_name, Toast.LENGTH_LONG).show()
        }
    }
}