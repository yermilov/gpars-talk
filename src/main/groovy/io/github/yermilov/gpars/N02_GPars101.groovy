package io.github.yermilov.gpars

import static groovyx.gpars.GParsPool.withPool

Closure whereAmI = {
    println "I'm located at ${Thread.currentThread().name}"
}

whereAmI()

withPool {
    Closure whereAsyncAmI = whereAmI.asyncFun()
    whereAsyncAmI()
}
