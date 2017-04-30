package io.github.yermilov.gpars.g02

import groovyx.gpars.ParallelEnhancer
import io.github.yermilov.gpars.utils.Twitter
import twitter4j.Status

Collection jeeconfTweets = new Twitter().search('jeeconf', 100)

def findMostPopularTweet = { Map params ->
    String topic = params.about
    Collection tweets = params.from

    tweets.findAll({ Status tweet ->
        tweet.text.toLowerCase().contains(topic.toLowerCase())
    }).findAll({ Status tweet ->
        !tweet.retweet
    }).collect ({ Status tweet ->
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

ParallelEnhancer.enhanceInstance(jeeconfTweets)
jeeconfTweets.makeConcurrent()

def result = findMostPopularTweet about: 'junit', from: jeeconfTweets
println result

