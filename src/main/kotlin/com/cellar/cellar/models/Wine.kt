package com.cellar.cellar.models

data class Wine(
    var id: String,
    var name: String,
    var type: String,
    var subType: String,
    var vintage: String,
    var comments: String
)
