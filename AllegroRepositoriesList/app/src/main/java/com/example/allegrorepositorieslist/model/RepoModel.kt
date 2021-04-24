package com.example.allegrorepositorieslist.model

data class RepoModel(val name: String,
                     val full_name: String,
                     val html_url: String,
                     val description: String,
                     val language: String,
                     val size: Int,
                     val watchers: Int,
                     val subscribers_count: Int,
                     val forks: Int,
                     val open_issues: Int,
                     val is_template: Boolean,
                     val has_issues: Boolean,
                     val has_projects: Boolean,
                     val has_wiki: Boolean,
                     val has_downloads: Boolean,
                     val archived: Boolean,
                     val license: LicenseModel
){}