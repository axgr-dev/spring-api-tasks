package dev.axgr

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
class App

fun main(args: Array<String>) {
  runApplication<App>(*args)
}
