package io.github.yermilov.gpars.j11;

import groovyx.gpars.MessagingRunnable;
import groovyx.gpars.agent.Agent;

import java.util.ArrayList;
import java.util.List;

public class Agents {

    public static void main(String[] args) throws InterruptedException {
        final Agent<String> stringGuard = new Agent<>();
        stringGuard.send(new MessagingRunnable<String>() {

            @Override
            protected void doRun(String value) {
                stringGuard.updateValue(value + '|' + Thread.currentThread().getName());
            }
        });
        System.out.println(stringGuard.getVal());


        final Agent<List<String>> listGuard = new Agent<>();

        listGuard.send(new ArrayList<>());

        listGuard.send(new MessagingRunnable<List<String>>() {

            @Override
            protected void doRun(List<String> value) {
                value.add(Thread.currentThread().getName());
            }
        });

        listGuard.valAsync(new MessagingRunnable<List<String>>() {

            @Override
            protected void doRun(List<String> value) {
                System.out.println(value);
            }
        });
    }
}
