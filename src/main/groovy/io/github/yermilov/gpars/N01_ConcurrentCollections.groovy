package io.github.yermilov.gpars

import static groovyx.gpars.GParsPool.withPool

withPool {
    assert [1, 4, 9, 16, 25, 36] == [1, 2, 3, 4, 5, 6].collectParallel {
        Thread.sleep(new Random().nextInt(100))
        println "example1-${Thread.currentThread().name}"
        it * it
    }
}

withPool {
    assert [1, 4, 9, 16, 25, 36] == [1, 2, 3, 4, 5, 6].makeConcurrent().collect {
        Thread.sleep(new Random().nextInt(100))
        println "example2-${Thread.currentThread().name}"
        it * it
    }
}

withPool {
    assert [1, 4, 9] == [1, 2, 3, 4, 5, 6].makeConcurrent().collect {
        Thread.sleep(new Random().nextInt(100))
        println "example3-collect-${Thread.currentThread().name}"
        it * it
    }.grep {
        Thread.sleep(new Random().nextInt(100))
        println "example3-grep-${Thread.currentThread().name}"
        it < 10
    }
}
