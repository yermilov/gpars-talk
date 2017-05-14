package io.github.yermilov.gpars.g07

import io.github.yermilov.gpars.j06.Shape

import static groovyx.gpars.GParsPool.withPool
import static io.github.yermilov.gpars.j06.Shape.BLUE
import static io.github.yermilov.gpars.j06.Shape.CIRCLE

Collection<Shape> shapes = null;

withPool(10, exceptionHandler) {
    Shape minBlueCircle = shapes.parallel
            .filter({ shape -> shape.type == CIRCLE })
            .filter({ shape -> shape.color == BLUE })
            .min({ shape -> shape.size })
}
