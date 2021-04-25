package com.example.allegrorepositorieslist.activity

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.allegrorepositorieslist.R
import com.example.allegrorepositorieslist.api_modules.runRepoService
import com.example.allegrorepositorieslist.model.RepoModel


class RepoDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_details)

        val repoName = intent.getStringExtra(getString(R.string.intent_extra_repo_name))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if(repoName != null){
            runRepoService(repoName, {
                setTextViews(it)
                findViewById<ProgressBar>(R.id.progressBarDetailsScreen).setVisibility(View.GONE);
                findViewById<ImageButton>(R.id.imageButtonCopyUrl).setOnClickListener(View.OnClickListener {
                    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val clip = ClipData.newPlainText("github url", findViewById<TextView>(R.id.textDetailsUrl).text)
                    clipboard.setPrimaryClip(clip)
                    Toast.makeText(applicationContext, getString(R.string.info_url_copied), Toast.LENGTH_SHORT).show()
                })
                findViewById<TableLayout>(R.id.tableLayoutDetails).visibility = View.VISIBLE
            }, {
                findViewById<ProgressBar>(R.id.progressBarDetailsScreen).setVisibility(View.GONE);
                Toast.makeText(applicationContext, getString(R.string.info_no_response), Toast.LENGTH_SHORT).show()
            })
        }
    }

    fun setTextViews(it: RepoModel) {
        findViewById<TextView>(R.id.textDetailsName).setText(it.name)
        findViewById<TextView>(R.id.textDetailsFullName).setText(it.full_name)
        findViewById<TextView>(R.id.textDetailsUrl).setText(it.html_url)
        if(it.description != null) findViewById<TextView>(R.id.textDetailsDescription).setText(it.description)
        else findViewById<TextView>(R.id.textDetailsDescription).setText(getString(R.string.no_answer))
        if(it.language != null) findViewById<TextView>(R.id.textDetailsLanguage).setText(it.language)
        else findViewById<TextView>(R.id.textDetailsLanguage).setText(getString(R.string.no_answer))
        findViewById<TextView>(R.id.textDetailsSize).setText(it.size.toString())
        findViewById<TextView>(R.id.textDetailsWatchers).setText(it.watchers.toString())
        findViewById<TextView>(R.id.textDetailsSubscribers).setText(it.subscribers_count.toString())
        findViewById<TextView>(R.id.textDetailsForks).setText(it.forks.toString())
        findViewById<TextView>(R.id.textDetailsOpenIssuesCount).setText(it.open_issues.toString())

        if(it.is_template) findViewById<TextView>(R.id.textDetailsIsTemplate).setText(getString(R.string.positive_answer))
        else findViewById<TextView>(R.id.textDetailsIsTemplate).setText(getString(R.string.negative_answer))
        if(it.has_issues) findViewById<TextView>(R.id.textDetailsHasIssues).setText(getString(R.string.positive_answer))
        else findViewById<TextView>(R.id.textDetailsHasIssues).setText(getString(R.string.negative_answer))
        if(it.has_projects) findViewById<TextView>(R.id.textDetailsHasProjects).setText(getString(R.string.positive_answer))
        else findViewById<TextView>(R.id.textDetailsHasProjects).setText(getString(R.string.negative_answer))
        if(it.has_wiki) findViewById<TextView>(R.id.textDetailsHasWiki).setText(getString(R.string.positive_answer))
        else findViewById<TextView>(R.id.textDetailsHasWiki).setText(getString(R.string.negative_answer))
        if(it.has_downloads) findViewById<TextView>(R.id.textDetailsHasDownloads).setText(getString(R.string.positive_answer))
        else findViewById<TextView>(R.id.textDetailsHasDownloads).setText(getString(R.string.negative_answer))
        if(it.archived) findViewById<TextView>(R.id.textDetailsArchived).setText(getString(R.string.positive_answer))
        else findViewById<TextView>(R.id.textDetailsArchived).setText(getString(R.string.negative_answer))

        if(it.license != null) findViewById<TextView>(R.id.textDetailsLicenseName).setText(it.license.name)
        else findViewById<TextView>(R.id.textDetailsLicenseName).setText(getString(R.string.no_answer))

    }
}
