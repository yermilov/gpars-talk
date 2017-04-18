package io.github.yermilov.gpars

import static groovyx.gpars.GParsPool.withPool

withPool {
    assert 14 == [0, 1, 2, 3, 4].parallel
            .map {
                Thread.sleep(new Random().nextInt(100))
                println "map1-${Thread.currentThread().name}"
                it + 1
            }
            .map {
                Thread.sleep(new Random().nextInt(100))
                println "map2-${Thread.currentThread().name}"
                it ** 2
            }
            .filter {
                Thread.sleep(new Random().nextInt(100))
                println "filter-${Thread.currentThread().name}"
                it < 10
            }
            .reduce { a, b ->
                Thread.sleep(new Random().nextInt(100))
                println "reduce-${Thread.currentThread().name}"
                a + b
            }
}
