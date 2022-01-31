package com.cellar.cellar.mongoRepos

import com.cellar.cellar.mapper.wineFromDocument
import com.cellar.cellar.mapper.wineToDocument
import com.cellar.cellar.models.Wine
import com.cellar.cellar.models.WineCreateModel
import com.cellar.cellar.repoModel.IWineRepo
import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClients
import com.mongodb.client.model.Filters
import org.bson.types.ObjectId


//cr6ravwjz1j92xAs
class CellarMongoRepo: IWineRepo {

    var connectionString =
        ConnectionString("mongodb+srv://admin:cr6ravwjz1j92xAs@cluster0.xd49x.mongodb.net/myFirstDatabase?retryWrites=true&w=majority")
    var settings = MongoClientSettings.builder()
        .applyConnectionString(connectionString)
        .build()
    var mongoClient = MongoClients.create(settings)
    var database = mongoClient.getDatabase("cellar")
    var collection = database.getCollection("wine")

    override fun createWine(newWine: WineCreateModel): String {
        val wine = Wine(
            generateId(),
            newWine.name,
            newWine.type,
            newWine.subType,
            newWine.vintage,
            newWine.comments
        )
        val document = wineToDocument(wine)
        collection.insertOne(document)
        return wine.id
    }

    override fun getWineById(id: String): Wine? {
        val foundWine = collection.find(Filters.eq("_id", id)).first()
        if (foundWine == null) {
            return null
        } else {
            return wineFromDocument(foundWine)
        }
    }

    override fun getAllWine(): List<Wine>? {
        val wineAsDocument = collection.find().toList()
        return wineAsDocument.map { w -> wineFromDocument(w) }
    }

    override fun getWineByType(type: String): List<Wine>? {
        val wineAsDocument = collection.find(Filters.eq("type", type)).toList()
        return wineAsDocument.map { w -> wineFromDocument(w) }
    }

    override fun getWineBySubType(subType: String): List<Wine>? {
        val wineAsDocument = collection.find(Filters.eq("subType", subType)).toList()
        return wineAsDocument.map { w -> wineFromDocument(w) }
    }

    override fun updateWine(updatedWine: Wine): Wine? {
        val result = collection.replaceOne((Filters.eq("_id", updatedWine.id)), wineToDocument(updatedWine))
        if (result.modifiedCount.toInt() == 1) {
            return updatedWine
        } else {
            return null
        }
    }

    override fun deleteWine(id: String): Boolean {
        val result = collection.deleteOne(Filters.eq("_id", id))
        return result.deletedCount.toInt() == 1
    }


    fun generateId(): String {
        return ObjectId().toHexString()
    }

}