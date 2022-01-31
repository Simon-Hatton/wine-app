package com.cellar.cellar

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CellarApplication

fun main(args: Array<String>) {
	runApplication<CellarApplication>(*args)
}
