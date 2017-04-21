package io.github.yermilov.gpars;

public class N01_JavaIsNotGroovy {

    public boolean isJavaGroovy() {
        return false;
    }

    public static void main(String[] args) {
        N01_JavaIsNotGroovy n01_javaIsNotGroovy = new N01_JavaIsNotGroovy();
        boolean isJavaGroovy = n01_javaIsNotGroovy.isJavaGroovy();
        if (isJavaGroovy) {
            System.out.println("Java is groovy, result is " + isJavaGroovy);
        } else {
            System.out.println("Java is not groovy, result is " + isJavaGroovy);
        }
    }
}
