package io.github.yermilov.gpars.j18;

import groovyx.gpars.DataflowMessagingRunnable;
import groovyx.gpars.dataflow.DataflowQueue;

import static groovyx.gpars.dataflow.Dataflow.operator;
import static groovyx.gpars.dataflow.Dataflow.task;
import static java.util.Arrays.asList;

public class DataflowStreams {

    public static void main(String[] args) {
        final DataflowQueue retweets = new DataflowQueue();
        final DataflowQueue facebookLikes = new DataflowQueue();
        final DataflowQueue numberOfInteractions = new DataflowQueue();

        operator(asList(retweets, facebookLikes), asList(numberOfInteractions), new DataflowMessagingRunnable(2) {

            @Override
            protected void doRun(Object... arguments) {
                getOwningProcessor().bindOutput((Integer)arguments[0] + (Integer)arguments[1]);
            }
        });

        task(() -> ALL_ARTICLES.forEach(article -> retweets.bind(twitter.numberOfRetweets(article))));

        task(() -> ALL_ARTICLES.forEach(article -> facebookLikes.bind(facebook.numberofLikes(article))));
    }
}
