package io.github.yermilov.gpars;

import groovy.lang.Closure;

import static groovyx.gpars.GParsPoolUtil.async;
import static groovyx.gpars.GParsPoolUtil.callAsync;

public class J02_GPars101 {

    public static void main(String[] args) {
        Closure whereAmI = new Closure(null) {

            // TODO: is it idiomatic?

            @Override
            public Object call() {
                System.out.println("I'm located at " + Thread.currentThread().getName());
                return null;
            }
        };

        whereAmI.call();

        // TODO: is it possible?

        callAsync(whereAmI);

        Closure whereAsyncAmI = async(whereAmI);
        whereAsyncAmI.call();
    }
}
