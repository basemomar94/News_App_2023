package com.example.newsapp.utils

object Constants {
    const val API_URL = "https://newsapi.org/"
    const val API_KEY = "e5095abc9bd84a259c6f0dba9a733416"
    const val BBC_NEWS_SOURCE = "bbc-news"
    const val CNN_SOURCE = "cnn"
    const val THE_NEW_YORK_TIMES_SOURCE = "the-new-york-times"
    const val THE_WASHINGTON_POST_SOURCE = "the-washington-post"
    const val REUTERS_SOURCE = "reuters"
    const val ASSOCIATED_PRESS_SOURCE = "associated-press"
    const val BLOOMBERG_SOURCE = "bloomberg"
    const val TECH_CRUNCH_SOURCE = "techcrunch"
    const val ESPN_SOURCE = "espn"
    const val NATIONAL_GEOGRAPHIC_SOURCE = "national-geographic"

    val newsSources = listOf(
        BBC_NEWS_SOURCE, CNN_SOURCE, THE_NEW_YORK_TIMES_SOURCE,
        THE_WASHINGTON_POST_SOURCE, REUTERS_SOURCE, ASSOCIATED_PRESS_SOURCE, BLOOMBERG_SOURCE,
        TECH_CRUNCH_SOURCE, ESPN_SOURCE, NATIONAL_GEOGRAPHIC_SOURCE
    )
}