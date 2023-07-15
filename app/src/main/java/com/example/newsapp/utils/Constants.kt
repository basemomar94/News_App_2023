package com.example.newsapp.utils

object Constants {
    const val API_URL = "https://newsapi.org/"
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
    const val ARTICLE_BUNDLE = "article"
    const val PREF_NAME = "news_app_pref"
    const val NEWS_SOURCE = "news_source"
    const val IS_WEB_VIEW = "is_web_view"

    val newsSources = listOf(
        BBC_NEWS_SOURCE, CNN_SOURCE, THE_NEW_YORK_TIMES_SOURCE,
        THE_WASHINGTON_POST_SOURCE, REUTERS_SOURCE, ASSOCIATED_PRESS_SOURCE, BLOOMBERG_SOURCE,
        TECH_CRUNCH_SOURCE, ESPN_SOURCE, NATIONAL_GEOGRAPHIC_SOURCE
    )
}