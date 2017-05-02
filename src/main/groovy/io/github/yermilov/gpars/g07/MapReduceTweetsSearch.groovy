package io.github.yermilov.gpars.g07

import io.github.yermilov.gpars.utils.Twitter
import twitter4j.Status

import static groovyx.gpars.GParsPool.withPool

Collection jeeconfTweets = new Twitter().search('jeeconf', 100)

def findMostPopularTweet = { Map params ->
    String topic = params.about
    Collection tweets = params.from

    withPool {
        tweets.parallel.filter({ Status tweet ->
            tweet.text.toLowerCase().contains(topic.toLowerCase())
        }).filter({ Status tweet ->
            !tweet.retweet
        }).map({ Status tweet ->
            [
                user: tweet.user.screenName,
                text: tweet.text,
                favourited: tweet.favoriteCount,
                retweeted: tweet.retweetCount
            ]
        }).max({
            it.favourited + it.retweeted
        })
    }
}

def result = findMostPopularTweet about: 'gpars', from: jeeconfTweets
println result

