# News App

A brief description of your Android Studio project.

## Description

A news app that fetches news from various sources and allows users to select their preferred source. The app presents news sources as a list, featuring images, titles, sources, publication dates, and descriptions for each news item. Users can authenticate using fingerprints for added security. Upon clicking a news item, users can view the content either natively or in a web view from the news website.


## Features

- The app fetches news from various sources and displays them one source at a time. Users can choose the news source from a specific list of sources.
- News sources are presented as a list, with each item displaying an image, news title, source, image, published date, and description.
- Users can authenticate using their fingerprints for added security.
- Clicking on a news item provides users with the content in two forms: a native view or a web view from the news website.

## Technologies Used

- MVVM architecture with the Repository pattern for clean code organization.
- Dependency Injection implemented using Dagger Hilt for efficient management of dependencies.
- Utilizes Kotlin Flows for reactive programming and handling asynchronous data streams.
- Retrofit library for making HTTP requests and handling network communication.
- Glide library for efficient image loading and caching.
