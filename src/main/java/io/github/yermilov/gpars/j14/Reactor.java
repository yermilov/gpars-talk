package io.github.yermilov.gpars.j14;

import groovy.lang.Closure;
import groovyx.gpars.ReactorMessagingRunnable;
import groovyx.gpars.actor.Actor;
import groovyx.gpars.actor.DefaultActor;
import groovyx.gpars.actor.ReactiveActor;

public class Reactor extends DefaultActor {

    public static void main(String[] args) {
        final Closure handler = new ReactorMessagingRunnable() {

            @Override
            protected Object doRun(Object argument) {
               // return news.fetchLatest();
                return null;
            }
        };

        final Actor actor = new ReactiveActor(handler);
        actor.start();
    }
}
