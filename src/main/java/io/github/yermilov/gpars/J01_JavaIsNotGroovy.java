package io.github.yermilov.gpars;

public final class J01_JavaIsNotGroovy {

    private final boolean isJavaGroovy = false;

    private J01_JavaIsNotGroovy() {}

    public static final J01_JavaIsNotGroovy isJavaGroovyDeterminer() {
        return new J01_JavaIsNotGroovy();
    }

    public boolean isJavaGroovy() {
        return false;
    }

    public static void main(String[] args) {
        J01_JavaIsNotGroovy j01_javaIsNotGroovy = isJavaGroovyDeterminer();
        boolean isJavaGroovy = j01_javaIsNotGroovy.isJavaGroovy();
        if (isJavaGroovy) {
            System.out.println("Java is groovy, result is " + isJavaGroovy);
        } else {
            System.out.println("Java is not groovy, result is " + isJavaGroovy);
        }
    }
}
