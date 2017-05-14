package io.github.yermilov.gpars.j06;

import java.util.Collection;
import java.util.Optional;

import static io.github.yermilov.gpars.j06.Shape.BLUE;
import static io.github.yermilov.gpars.j06.Shape.CIRCLE;
import static java.util.Comparator.comparingInt;

public class StreamsForGeometry {

    public static void main(String[] args) {
        Collection<Shape> shapes = null;

        Optional<Shape> minBlueCircle = shapes.stream()
                .parallel()
                .filter(shape -> shape.getType() == CIRCLE)
                .filter(shape -> shape.getColor() == BLUE)
                .min(comparingInt(shape -> shape.getSize()));
    }
}
