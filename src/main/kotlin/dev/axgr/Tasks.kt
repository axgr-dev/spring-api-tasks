package dev.axgr

import java.util.*

enum class TaskState {
  CREATED,
  RUNNING,
  COMPLETED,
  CANCELED
}

data class Task(val id: UUID = UUID.randomUUID(), val progress: Int = 0, val state: TaskState = TaskState.CREATED)

abstract class Operation(val task: Task = Task())

// Specific Task Operations
class Broadcast : Operation()
