package com.cellar.cellar.mapper

import com.cellar.cellar.models.Wine
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.bson.Document

data class WineMongoModel(
    var _id: String,
    var name: String,
    var type: String,
    var subType: String,
    var vintage: String,
    var comments: String

    )

val jacksonMapper = jacksonObjectMapper()


fun wineToDocument(wine: Wine): Document {

    val wineMongoModel = WineMongoModel(
        wine.id,
        wine.name,
        wine.type,
        wine.subType,
        wine.vintage,
        wine.comments

        )

    val jsonString = jacksonMapper.writeValueAsString(wineMongoModel)


    val document = Document.parse(jsonString)


    return document
}

fun wineFromDocument(document: Document): Wine {

    val jsonString = document.toJson()

    val mongoModel = jacksonMapper.readValue<WineMongoModel>(jsonString)


    val wine = Wine(
        mongoModel._id,
        mongoModel.name,
        mongoModel.type,
        mongoModel.subType,
        mongoModel.vintage,
        mongoModel.comments

    )

    return wine
}