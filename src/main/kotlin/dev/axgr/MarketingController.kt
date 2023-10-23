package dev.axgr

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class MarketingController(private val tasks: TaskService) {

  @PostMapping("/broadcast/start")
  fun broadcast(): ResponseEntity<Void> {
    val op = Broadcast()
    tasks.submit(op)

    return ResponseEntity
      .ok()
      .location(URI.create("/tasks/${op.task.id}"))
      .build()
  }
}
