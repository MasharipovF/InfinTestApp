package com.example.infintestapp.presentation.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.infintestapp.Consts
import com.example.infintestapp.Preferences
import com.example.infintestapp.R
import com.example.infintestapp.app.InfinTestApp
import com.example.infintestapp.databinding.ActivityMainBinding
import com.example.infintestapp.presentation.reposearch.RepoSearchActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mViewModelFactory: MainActivityViewModelFactory
    private lateinit var mViewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (applicationContext as InfinTestApp).appComponent.inject(this)

        if (Preferences.token != null) {
            val intent = Intent(this, RepoSearchActivity::class.java)
            startActivity(intent)
            finish()
        }

        initViewModel()

        binding.btnAuthorize.setOnClickListener {
            val intent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse("${Consts.AUTH_URL}?client_id=${Consts.clientId}&redirect_uri=${Consts.REDIRECT_URL}"))
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()

        Log.d("ACTIVITY", "onresume")
        Log.d("ACTIVITY", Preferences.token.toString())


        val uri: Uri? = intent.data

        if (uri != null && uri.toString().startsWith(Consts.REDIRECT_URL)) {
            val code: String? = uri.getQueryParameter("code")
            if (code != null) {
                mViewModel.getAccessToken(code)
            }
        }
    }


    fun initViewModel() {
        mViewModel = ViewModelProvider(this, mViewModelFactory).get(MainActivityViewModel::class.java)

        mViewModel.tokenReceived.observe(this, {
            if (it == true) {
                Toast.makeText(this, resources.getString(R.string.authorize_succes), Toast.LENGTH_SHORT).show()
                val intent = Intent(this, RepoSearchActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, resources.getString(R.string.error), Toast.LENGTH_SHORT).show()
            }
        })
    }

}