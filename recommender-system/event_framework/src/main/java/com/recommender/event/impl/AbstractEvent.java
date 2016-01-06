package com.recommender.event.impl;

import com.recommender.event.Event;

/**
 *
 */
public abstract class AbstractEvent implements Event {
    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbstractEvent that = (AbstractEvent) o;

        if (getId() != that.getId()) {
            return false;
        }

        return true;
    }

    @Override
    public final int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }
}
