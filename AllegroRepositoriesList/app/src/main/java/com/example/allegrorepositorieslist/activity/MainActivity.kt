package com.example.allegrorepositorieslist.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.allegrorepositorieslist.R
import com.example.allegrorepositorieslist.ReposAdapter
import com.example.allegrorepositorieslist.api_modules.runReposService

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        runReposService {
            val adapter = ReposAdapter(it) {
                startActivity(Intent(applicationContext, RepoDetailsActivity::class.java).apply {
                    putExtra(getString(R.string.intent_extra_repo_name), it.name)
                })
            }
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(applicationContext)
            recyclerView.addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))
            findViewById<ProgressBar>(R.id.progressBarMainScreen).setVisibility(View.GONE);
        }
    }
}

