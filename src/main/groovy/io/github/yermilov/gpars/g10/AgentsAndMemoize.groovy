package io.github.yermilov.gpars.g10

import groovyx.gpars.agent.Agent
import io.github.yermilov.gpars.utils.Twitter

import java.util.concurrent.atomic.AtomicInteger

import static groovyx.gpars.GParsPool.withPool

withPool {
    def requestsCount = new Agent<Integer>(0)
    def actionsCount = new Agent<Integer>(0)

    Closure requestTweets = { String request ->
        actionsCount { updateValue(it + 1) }
        new Twitter().search(request, 100)
    }

    Closure memoizedRequestTweets = requestTweets.gmemoize()
    Closure cachedRequestTweets = { String request ->
        requestsCount { updateValue(it + 1) }
        memoizedRequestTweets(request)
    }

    cachedRequestTweets('junit')
    (1..9).collect({
        Thread.start {
            cachedRequestTweets('junit')
        }
    })*.join()

    assert requestsCount.val == 10
    assert actionsCount.val == 1
}

