package io.github.yermilov.gpars.e01

import groovy.transform.Synchronized
import groovy.transform.WithReadLock
import groovy.transform.WithWriteLock

class SynchronizedCounter {

    int atomicCounter
    int counter

    @Synchronized
    int incrementAndGet() {
        atomicCounter = atomicCounter + 1
        return atomicCounter
    }

    @WithReadLock
    int getCounter() {
        counter
    }

    @WithWriteLock
    void increment() {
        counter = counter + 1
    }
}
