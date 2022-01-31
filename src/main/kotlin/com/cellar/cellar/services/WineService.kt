package com.cellar.cellar.services

import com.cellar.cellar.models.Wine
import com.cellar.cellar.models.WineCreateModel
import com.cellar.cellar.mongoRepos.CellarMongoRepo
import com.cellar.cellar.repoModel.IWineRepo
import com.cellar.cellar.serviceModel.IWineService

class WineService: IWineService {
    override fun createWine(newWine: WineCreateModel): String {
        return wineRepo.createWine(newWine)
    }

    override fun getWineById(id: String): Wine? {
        return wineRepo.getWineById(id)
    }

    override fun getAllWine(): List<Wine>? {
        return wineRepo.getAllWine()
    }

    override fun getWineByType(type: String): List<Wine>? {
        return wineRepo.getWineByType(type)
    }

    override fun getWineBySubType(subType: String): List<Wine>? {
        return wineRepo.getWineBySubType(subType)
    }

    override fun updateWine(updatedWine: Wine): Wine? {
        return wineRepo.updateWine(updatedWine)
    }

    override fun deleteWine(id: String): Boolean {
        return wineRepo.deleteWine(id)
    }

    companion object {
        val wineRepo: IWineRepo = CellarMongoRepo()
    }
}