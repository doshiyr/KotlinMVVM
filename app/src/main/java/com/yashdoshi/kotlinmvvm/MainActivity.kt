package com.yashdoshi.kotlinmvvm

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.yashdoshi.kotlinmvvm.viewmodel.BoredViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: BoredViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(BoredViewModel::class.java)
        viewModel.isLoading.observe(this, Observer {
            if(it){
                activityCard.visibility = View.INVISIBLE
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.INVISIBLE
                activityCard.visibility = View.VISIBLE
            }
        })
        viewModel.isError.observe(this, Observer {
            Snackbar.make(container, it,Snackbar.LENGTH_SHORT).show()
        })
        viewModel.boredActivity.observe(this, Observer {boredActivity ->
            tvActivity.text = boredActivity.activity
            tvType.text = boredActivity.type.capitalize()
            tvPrice.text = "$${boredActivity.price}"
            tvParticipants.text = "${boredActivity.participants} Participants"
            if(boredActivity.link.isBlank()){
                tvLink.visibility = View.GONE
            } else {
                tvLink.visibility = View.VISIBLE
                tvLink.setOnClickListener {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(boredActivity.link))
                    startActivity(browserIntent)
                }
            }
        })
        btnNewActivity.setOnClickListener {
            viewModel.getBoredActivity()
        }
    }
}
