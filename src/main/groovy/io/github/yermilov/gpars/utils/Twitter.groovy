package io.github.yermilov.gpars.utils

import twitter4j.Query
import twitter4j.Status
import twitter4j.TwitterFactory

class Twitter {

    def twitter = new TwitterFactory().instance

    Collection<Status> search(String request, int count) {
        println "searching $count tweets about $request"

        Collection<Status> searchResult = []

        Query query = new Query(request)
        query.count = count

        def response = twitter.search(query)

        while (searchResult.size() < count) {
            searchResult += response.tweets
            if (!response.hasNext()) {
                return searchResult
            }
            response = twitter.search(response.nextQuery())
        }
        return searchResult
    }
}
