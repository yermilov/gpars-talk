package io.github.yermilov.gpars.j15;

import groovyx.gpars.dataflow.DataflowVariable;
import groovyx.gpars.group.DefaultPGroup;

import static groovyx.gpars.dataflow.Dataflow.task;

public class DataflowTasks {

    public static void main(String[] args) throws InterruptedException {
        final DataflowVariable<Integer> numberOfInteractions = new DataflowVariable<>();
        final DataflowVariable<Integer> retweets = new DataflowVariable<>();
        final DataflowVariable<Integer> facebookLikes = new DataflowVariable<>();

        task((Runnable) () -> {
            try {
                numberOfInteractions.bind(retweets.getVal() + facebookLikes.getVal());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        task(() -> retweets.bind(twitter.numberOfRetweets(article)));

        task(() -> facebookLikes.bind(facebook.numberofLikes(article)));

        System.out.println(numberOfInteractions.getVal());
    }
}
