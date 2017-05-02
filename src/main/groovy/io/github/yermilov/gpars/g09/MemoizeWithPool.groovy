package io.github.yermilov.gpars.g09

import io.github.yermilov.gpars.utils.Twitter

import java.util.concurrent.atomic.AtomicInteger

import static groovyx.gpars.GParsPool.withPool

withPool {
    AtomicInteger requestsCount = new AtomicInteger(0)
    AtomicInteger actionsCount = new AtomicInteger(0)

    Closure requestTweets = { String request ->
        actionsCount.incrementAndGet()
        new Twitter().search(request, 100)
    }

    Closure memoizedRequestTweets = requestTweets.gmemoize()
    Closure cachedRequestTweets = { String request ->
        requestsCount.incrementAndGet()
        memoizedRequestTweets(request)
    }

    cachedRequestTweets('junit')
    (1..9).collect({
        Thread.start {
            cachedRequestTweets('junit')
        }
    })*.join()

    assert requestsCount.get() == 10
    assert actionsCount.get() == 1
}
