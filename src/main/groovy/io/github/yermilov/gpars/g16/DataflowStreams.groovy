package io.github.yermilov.gpars.g16

import static groovyx.gpars.dataflow.Dataflow.*
import groovyx.gpars.dataflow.DataflowQueue

def retweets = new DataflowQueue()
def facebookLikes = new DataflowQueue()
def numberOfInteractions = new DataflowQueue()

operator(
    inputs: [ retweets, facebookLikes ],
    outputs: [ numberOfInteractions ],
    { retweetsCount, facebookLikesCount ->
        numberOfInteractions << retweetsCount + facebookLikesCount
    }
)

task {
    ALL_ARTICLES.each { article -> retweets << twitter.numberOfRetweets(article) }
}

task {
    ALL_ARTICLES.each { article -> facebookLikes << facebook.numberofLikes(article) }
}

