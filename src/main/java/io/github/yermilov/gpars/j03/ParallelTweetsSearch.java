package io.github.yermilov.gpars.j03;

import groovyx.gpars.ParallelEnhancer;
import io.github.yermilov.gpars.utils.Twitter;
import twitter4j.Status;

import java.util.*;

public class ParallelTweetsSearch {

    public static void main(String[] args) {
        Collection<Status> jeeconfTweets = new Twitter().search("jeeconf", 100);

        Map<String, Object> result = findMostPopularTweet("junit", jeeconfTweets);
        System.out.println(result);
    }

    private static Map<String, Object> findMostPopularTweet(String topic, Collection<Status> tweets) {
        Optional<Status> mostPopularTweet = tweets.stream()

                .parallel()

                .filter(tweet -> tweet.getText().toLowerCase().contains(topic.toLowerCase()))
                .filter(tweet -> !tweet.isRetweet())
                .max(Comparator.comparingInt(tweet -> tweet.getFavoriteCount() + tweet.getRetweetCount()));

        Map<String, Object> result = new HashMap<>();
        result.put("user", mostPopularTweet.get().getUser().getScreenName());
        result.put("text", mostPopularTweet.get().getText());
        result.put("favourited", mostPopularTweet.get().getFavoriteCount());
        result.put("retweeting", mostPopularTweet.get().getRetweetCount());

        return result;
    }
}
