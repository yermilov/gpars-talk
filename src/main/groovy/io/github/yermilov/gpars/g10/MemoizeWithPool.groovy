package io.github.yermilov.gpars.g10

import groovyx.gpars.dataflow.Promise
import io.github.yermilov.gpars.utils.Twitter

import static groovyx.gpars.GParsPool.withPool

def twitter = new Twitter()

withPool {
    Closure fetchLatestTweets = twitter.&fetchLatestTweets.asyncFun()

    Closure determineTopic = topics.&determineTopic.asyncFun()

    Closure aggregateTopics = topics.&aggregateTopics.gmemoize()

    Closure fetchNewsAbout = news.&fetchNewsAbout.asyncFun()

    Closure filterMostImportant = news.&filterMostImportant.asyncFun()


    Promise topics = determineTopic(fetchLatestTweets)

    Promise aggregatedTopics = topics.get().inject({ t1, t2 -> aggregateTopics(t1, t2) })

    aggregatedTopics.then {
        fetchNewsAbout(it)
    }.then {
        filterMostImportant(it)
    }.then {
        println it
    }.join()
}
