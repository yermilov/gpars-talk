package io.github.yermilov.gpars

import static groovyx.gpars.actor.Actors.*

def manual = reactor { message ->
    switch (message) {
        case Number: reply 'number'; break
        case String: reply 'string'; break
    }
}

def auto = messageHandler {
    when { String message -> reply 'string' }
    when { Number message -> reply 'number' }
}