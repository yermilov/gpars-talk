package io.github.yermilov.gpars

import groovy.transform.Immutable
import groovy.transform.Synchronized
import groovy.transform.WithReadLock
import groovy.transform.WithWriteLock

Thread.start {
    println "Hello from ${Thread.currentThread().name}"
}

Thread.start 'groovy thread', {
    println "Hello from ${Thread.currentThread().name}"
}

def daemon1 = Thread.startDaemon {
    println "Hello from ${Thread.currentThread().name}"
}

def daemon2 = Thread.startDaemon 'groovy daemon', {
    println "Hello from ${Thread.currentThread().name}"
}

[ daemon1, daemon2 ]*.join()


def process = (['git', 'status']).execute([], new File('.'))
def processOutput = new StringWriter()
process.consumeProcessOutput processOutput, processOutput
process.waitFor()
println processOutput.toString().trim()


@Immutable class Person {
    String name
    long hair
}


class LazyPerson {

    @Lazy
    String greeting = 'hello!'
}


class SynchronizedPerson {

    String personToSayHello

    @Synchronized
    String exclusiveGreeting() {
        "hello, $personToSayHello!"
    }

    @WithReadLock
    String 'whom I say hello?'() {
        personToSayHello
    }

    @WithWriteLock
    String 'change person to say hello'(String newPersonToSayHello) {
        personToSayHello = newPersonToSayHello
    }
}