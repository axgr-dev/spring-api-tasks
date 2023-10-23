package dev.axgr

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/tasks")
class TaskController(private val tasks: TaskService) {

  @GetMapping("/{id}")
  fun get(@PathVariable id: UUID) = tasks.get(id)

  @DeleteMapping("/{id}")
  fun cancel(@PathVariable id: UUID) = tasks.cancel(id)

}
