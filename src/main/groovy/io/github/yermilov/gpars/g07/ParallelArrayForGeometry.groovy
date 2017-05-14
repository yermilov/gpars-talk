package io.github.yermilov.gpars.g07

import groovyx.gpars.ParallelEnhancer
import io.github.yermilov.gpars.j06.Shape

import static io.github.yermilov.gpars.j06.Shape.BLUE
import static io.github.yermilov.gpars.j06.Shape.CIRCLE

Collection<Shape> shapes = null;

ParallelEnhancer.enhanceInstance shapes
Shape minBlueCircle = shapes
        .findAllParallel({ shape -> shape.type == CIRCLE })
        .findAllParallel({ shape -> shape.color == BLUE })
        .minParallel({ shape -> shape.size })
