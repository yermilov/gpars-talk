package io.github.yermilov.gpars.g10

import groovyx.gpars.agent.Agent

def stringGuard = new Agent<String>()
28.times {
    Thread.start {
        stringGuard { updateValue(it + '|' + Thread.currentThread().name) }
    }
}
println stringGuard.val

def listGuard = new Agent<List<String>>()
43.times {
    Thread.start {
        listGuard { it.add(Thread.currentThread().name) }
    }
}
listGuard.valAsync {
    println it
}

