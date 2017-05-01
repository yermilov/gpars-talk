package io.github.yermilov.gpars.j01;

public class Holder {

    private int n;

    public Holder(int n) {
        this.n = n;
    }

    public void assertSanity() {
        if (n != n) {
            throw new AssertionError("Even it can go wrong!");
        }
    }
}
