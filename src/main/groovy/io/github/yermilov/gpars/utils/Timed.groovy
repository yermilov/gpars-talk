package io.github.yermilov.gpars.utils

class Timed {

    static def timed(Closure timedClosure) {
        long startTime = System.currentTimeMillis()
        def result = timedClosure.call()
        long endTime = System.currentTimeMillis()

        println "action took ${(endTime - startTime) / 1000} sec"

        return result
    }
}
