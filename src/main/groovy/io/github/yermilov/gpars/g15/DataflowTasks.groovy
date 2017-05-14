package io.github.yermilov.gpars.g15

import groovyx.gpars.dataflow.Dataflows

import static groovyx.gpars.dataflow.Dataflow.task

final dataflow = new Dataflows()

task {
    dataflow.numberOfInteractions = dataflow.retweets + dataflow.facebookLikes
}

task {
    dataflow.retweets = twitter.numberOfRetweets(article)
}

task {
    dataflow.facebookLikes = facebook.numberofLikes(article)
}

println dataflow.numberOfInteractions