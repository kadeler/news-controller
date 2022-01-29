package com.newscontroller.data.model

import kotlinx.serialization.Serializable

@Serializable
data class APIErrorHandler(
    val code: Int,
    val message: String
)

@Serializable
data class Categories(
    val code: Int,
    val list: Array<CategoryEntry>
)

@Serializable
data class CategoryEntry(
    val id: Int,
    val name: String
)

@Serializable
data class News(
    val code: Int,
    var list: Array<NewsEntry?>
)

@Serializable
data class NewsEntry(
    val id: Int,
    val title: String,
    val date: String,
    val shortDescription: String
)

@Serializable
data class Details(
    val code: Int,
    val news: Array<DetailsEntry>
)

@Serializable
data class DetailsEntry(
    val id: Int,
    val title: String,
    val date: String,
    val shortDescription: String,
    val fullDescription: String
)
