package io.github.yermilov.gpars.j01;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Collections.unmodifiableCollection;

public final class ImmutableJavaPerson {

    private final String name;
    private final Collection<String> tweets;

    public ImmutableJavaPerson(String name, Collection<String> tweets) {
        this.name = name;
        this.tweets = new ArrayList<>(tweets);
    }

    public String getName() {
        return name;
    }

    public Collection<String> getTweets() {
        return unmodifiableCollection(tweets);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ImmutableJavaPerson that = (ImmutableJavaPerson) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return tweets != null ? tweets.equals(that.tweets) : that.tweets == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (tweets != null ? tweets.hashCode() : 0);
        return result;
    }
}
