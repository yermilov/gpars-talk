package io.github.yermilov.gpars

import groovyx.gpars.dataflow.Dataflows
import static groovyx.gpars.dataflow.Dataflow.task

final flow = new Dataflows()

task {
    Thread.sleep(new Random().nextInt(100))
    println "flow-result-${Thread.currentThread().name}"
    flow.result = flow.x + flow.y
}

task {
    Thread.sleep(new Random().nextInt(100))
    println "flow-x-${Thread.currentThread().name}"
    flow.x = 10
}

task {
    Thread.sleep(new Random().nextInt(100))
    println "flow-y-${Thread.currentThread().name}"
    flow.y = 5
}

assert 15 == flow.result