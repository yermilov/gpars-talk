package io.github.yermilov.gpars.g08

import groovyx.gpars.dataflow.Promise
import io.github.yermilov.gpars.utils.Twitter

import static groovyx.gpars.GParsPool.withPool

withPool {
    Closure requestFun = { ->
        'junit'
    }.asyncFun()

    Closure countFun = { ->
        100
    }.asyncFun()

    Closure requestTweets = { String request, int count ->
        new Twitter().search(request, count)
    }.asyncFun()

    Promise tweets = requestTweets(requestFun(), countFun())

    tweets.then { Collection result ->
        result.size()
    }.then { int size ->
        println size
    }.join()
}
