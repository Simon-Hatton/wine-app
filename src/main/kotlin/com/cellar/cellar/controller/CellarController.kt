package com.cellar.cellar.controller

import com.cellar.cellar.models.Wine
import com.cellar.cellar.models.WineCreateModel
import com.cellar.cellar.serviceModel.IWineService
import com.cellar.cellar.services.WineService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api")
class CellarController {
    @PostMapping("wine")
    fun createWine(@RequestBody newWine: WineCreateModel): ResponseEntity<String> {
        return ResponseEntity.ok(wineServices.createWine(newWine))
    }
    @GetMapping("wine/{id}")
    fun getWineById(@PathVariable id: String): ResponseEntity<Wine> {
        val foundWine = wineServices.getWineById(id)
        if (foundWine == null) {
            return ResponseEntity.notFound().build()
        } else {
            return ResponseEntity.ok(foundWine)
        }
    }
    @GetMapping("wine")
    fun getAllWine(): ResponseEntity<List<Wine>> {
        return ResponseEntity.ok(wineServices.getAllWine())
    }
    @GetMapping("type/{type}")
    fun getWineByType(@PathVariable type: String): ResponseEntity<List<Wine>> {
        val foundWine = wineServices.getWineByType(type)
        if (foundWine == null) {
            return ResponseEntity.notFound().build()
        } else {
            return ResponseEntity.ok(foundWine)
        }
    }
    @GetMapping("subType/{subType}")
    fun getWineBySubType(@PathVariable subType: String): ResponseEntity<List<Wine>> {
        val foundWine = wineServices.getWineBySubType(subType)
        if (foundWine == null) {
            return ResponseEntity.notFound().build()
        } else {
            return ResponseEntity.ok(foundWine)
        }
    }
    @PutMapping("wine")
    fun updateWine(@RequestBody updatedWine: Wine): ResponseEntity<Wine> {
        val wine = wineServices.updateWine(updatedWine)
        if (wine == null) {
            return ResponseEntity.notFound().build()
        } else {
            return ResponseEntity.ok(wine)
        }
    }

    @DeleteMapping("wine/{id}")
    fun deleteWine(@PathVariable id: String): ResponseEntity<Void> {
        val wasDeleted = wineServices.deleteWine(id)
        if (wasDeleted) {
            return ResponseEntity.ok().build()
        } else {
            return ResponseEntity.notFound().build()
        }
    }


    companion object {
        var wineServices: IWineService = WineService()
    }
}