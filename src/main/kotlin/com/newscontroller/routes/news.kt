package com.newscontroller.routes

import com.newscontroller.data.*
import com.newscontroller.data.model.APIErrorHandler
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val format = Json { prettyPrint = true }

fun Route.responseManager() {
    //Получение списка категорий
    get("/news-controller/categories"){
        //просто отправляем все хранимые категории
        call.respond(HttpStatusCode.OK, format.encodeToString(categories))
    }

    //Получение списка новостей
    get("/news-controller/{id}/news"){
        //проверяем введен ли айди
        if (call.parameters["id"]?.toIntOrNull() != null) {
            //проверяем введен ли параметр страницы
            if (call.request.queryParameters["page"]?.toIntOrNull() != null) {
                call.respond(
                    HttpStatusCode.OK,
                    format.encodeToString(newsPagination(call.parameters["id"]?.toInt()!!, call.request.queryParameters["page"]?.toInt()!!))
                )
            }
            //параметр страницы не введен
            else if (call.request.queryParameters["page"] == null) {
                call.respond(
                    HttpStatusCode.OK,
                    format.encodeToString(newsPagination(call.parameters["id"]?.toInt()!!, 0))
                )
            }
            //параметр страницы введен, но не является null или int
            else {
                call.respond(
                    HttpStatusCode.OK, format.encodeToString(APIErrorHandler(4,
                        "Type mismatch exception: '${call.request.queryParameters["page"]}' does not " +
                                "look like something of Integer type.")
                ))
            }
        }
        //айди не введен
        else {
            call.respond(
                HttpStatusCode.NotFound
            )
        }
    }

    //Получение новости со всеми подробностями
    get("/news-controller/details") {
        val id = call.request.queryParameters["id"]?.toIntOrNull() //сократил потому что это нечитаемый ад
        //проверяем введен ли параметр айди
        if (id != null) {
            //проверяем есть ли новость с таким айди
            if (findArticle(id) != null) {
                call.respond(
                    HttpStatusCode.OK, format.encodeToString(findArticle(id))
                )
            }
            //такой новости нет
            else {
                call.respond(
                    HttpStatusCode.OK, format.encodeToString(APIErrorHandler(14, "Article with an id of ${id} was not found."))
                )
            }
        }
        //параметр айди не введен
        else if (call.request.queryParameters["id"] == null) {
            call.respond(
                HttpStatusCode.OK, format.encodeToString(APIErrorHandler(5, "you haven't entered the id of article."))
            )
        }
        //параметр введен, но не является null или int
        else {
            call.respond(
                HttpStatusCode.OK, format.encodeToString(APIErrorHandler(4, "Type mismatch exception: " +
                        "'${call.request.queryParameters["id"]}' does not look like something of Integer type."))
            )
        }
    }
}