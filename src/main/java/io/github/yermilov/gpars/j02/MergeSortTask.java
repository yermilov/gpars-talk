package io.github.yermilov.gpars.j02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.RecursiveTask;

import static java.util.Arrays.asList;

public class MergeSortTask<T extends Comparable<T>> extends RecursiveTask<List<T>> {

    @Override
    protected List<T> compute() {
        if (list.size() < 2) {
            return list;
        }

        if (list.size() == 2) {
            if (list.get(0).compareTo(list.get(1)) != 1) {
                return list;
            } else {
                return asList(list.get(1), list.get(0));
            }
        }

        MergeSortTask<T> leftTask = new MergeSortTask<>(list.subList(0, list.size() / 2));
        MergeSortTask<T> rightTask = new MergeSortTask<>(list.subList(list.size() / 2, list.size()));

        leftTask.fork();
        rightTask.fork();

        List<T> left = leftTask.join();
        List<T> right = rightTask.join();

        return merge(left, right);
    }

    private final List<T> list;

    public MergeSortTask(Collection<T> collection) {
        this.list = new ArrayList<>(collection);
    }

    private List<T> merge(List<T> left, List<T> right) {
        int i = 0;
        int j = 0;
        List<T> result = new ArrayList<T>(left.size() + right.size());

        while ((i < left.size()) && (j < right.size())) {
            if (left.get(i).compareTo(right.get(j)) <= 0) {
                result.add(left.get(i++));
            } else {
                result.add(right.get(j++));
            }
        }

        if (i < left.size()) {
            result.addAll(left.subList(i, left.size()));
        } else {
            result.addAll(right.subList(j, right.size()));
        }

        return result;
    }
}
