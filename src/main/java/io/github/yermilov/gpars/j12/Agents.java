package io.github.yermilov.gpars.j12;

import groovy.lang.Closure;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import static groovyx.gpars.GParsPool.executeAsync;
import static groovyx.gpars.GParsPool.speculate;
import static groovyx.gpars.GParsPool.withPool;

public class Agents {

    private static <V> Closure<V> lambdaToClosure(Callable<V> callable) {
        return new Closure<V>(null) {

            @Override
            public V call(final Object[] args) {
                try {
                    return callable.call();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    public static void main(String[] args) throws InterruptedException {
        withPool(lambdaToClosure(() -> {
            Future result = executeAsync(
                    lambdaToClosure((Callable<Inte>() -> {
                        Thread.sleep(1000);
                        System.out.print("1000");
                        return 15;
                    })).get(0);
            result.get();
            return null;
        }));

        withPool(lambdaToClosure(() -> {
            speculate(
                    lambdaToClosure(() -> {
                        Thread.sleep(1000);
                        System.out.print("1000");
                        return null;
                    }),
                    lambdaToClosure(() -> {
                        Thread.sleep(5000);
                        System.out.print("5000");
                        return null;
                    }));
            return null;
        }));



//        final Agent<String> stringGuard = new Agent<>();
//        stringGuard.send(new MessagingRunnable<String>() {
//
//            @Override
//            protected void doRun(String value) {
//                stringGuard.updateValue(value + '|' + Thread.currentThread().getName());
//            }
//        });
//        System.out.println(stringGuard.getVal());
//
//
//        final Agent<List<String>> listGuard = new Agent<>();
//
//        listGuard.send(new ArrayList<>());
//
//        listGuard.send(new MessagingRunnable<List<String>>() {
//
//            @Override
//            protected void doRun(List<String> value) {
//                value.add(Thread.currentThread().getName());
//            }
//        });
//
//        listGuard.valAsync(new MessagingRunnable<List<String>>() {
//
//            @Override
//            protected void doRun(List<String> value) {
//                System.out.println(value);
//            }
//        });
    }
}
