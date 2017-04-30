package io.github.yermilov.gpars.g02

import io.github.yermilov.gpars.utils.Twitter

import static groovyx.gpars.GParsPool.withPool

withPool {
    Closure requestTweets = { String request ->
        new Twitter().search(request, 100)
    }

    Closure cachedRequestTweets = requestTweets.gmemoize()

    cachedRequestTweets('junit')
    cachedRequestTweets('junit')
}
