package dev.axgr

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@Service
class TaskService(private val publisher: ApplicationEventPublisher) {

  private val tasks = ConcurrentHashMap<UUID, Task>()

  fun get(id: UUID) = tasks[id]

  fun submit(op: Operation) {
    tasks.computeIfAbsent(op.task.id) { op.task }
    publisher.publishEvent(op)
  }

  fun start(id: UUID) {
    tasks.computeIfPresent(id) { _, task -> task.copy(state = TaskState.RUNNING) }
  }

  fun progress(id: UUID, progress: Int) {
    tasks.computeIfPresent(id) { _, task -> task.copy(progress = progress) }
  }

  fun cancel(id: UUID) {
    tasks.computeIfPresent(id) { _, task -> task.copy(state = TaskState.CANCELED) }
  }

  fun active(id: UUID): Boolean {
    tasks[id]?.let { return it.state == TaskState.RUNNING }
    throw IllegalArgumentException("Task $id not found")
  }

  fun complete(id: UUID) {
    tasks.computeIfPresent(id) { _, task -> task.copy(state = TaskState.COMPLETED) }
  }

}
