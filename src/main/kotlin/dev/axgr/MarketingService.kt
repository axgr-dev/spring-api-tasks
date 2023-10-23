package dev.axgr

import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class MarketingService(private val tasks: TaskService) {

  companion object {
    private val log = LoggerFactory.getLogger(MarketingService::class.java)
  }

  @Async
  @EventListener
  fun broadcast(op: Operation) {
    log.info("Starting task ${op.task.id}..")
    tasks.start(op.task.id)
    (1..10).forEach { actual ->
      if (tasks.active(op.task.id)) {
        Thread.sleep(10_000)
        tasks.progress(op.task.id, actual * 10)
        log.info("Iteration $actual finished.")
      } else {
        log.info("Broadcast canceled.")
        return
      }
    }
    tasks.complete(op.task.id)
  }

}
