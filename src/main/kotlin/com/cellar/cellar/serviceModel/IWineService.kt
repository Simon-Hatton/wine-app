package com.cellar.cellar.serviceModel

import com.cellar.cellar.models.Wine
import com.cellar.cellar.models.WineCreateModel

interface IWineService {
    fun createWine(newWine: WineCreateModel): String
    fun getWineById(id: String): Wine?
    fun getAllWine(): List<Wine>?
    fun getWineByType(type: String): List<Wine>?
    fun getWineBySubType(subType: String): List<Wine>?
    fun updateWine(updatedWine: Wine): Wine?
    fun deleteWine(id: String): Boolean
}