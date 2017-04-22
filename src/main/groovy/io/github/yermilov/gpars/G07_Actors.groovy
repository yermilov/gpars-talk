package io.github.yermilov.gpars

import static groovyx.gpars.actor.Actors.*

def decrypt = reactor { code -> code.reverse() }
def audit = reactor { println it }

def main = actor {
    decrypt 'terces pot'
    react { plainText ->
        audit plainText
    }
}
main.join()
audit.stop()
audit.join()
