package com.ebooka.events;

public interface Event<T>
{
    void dispatchOn(Object listener);
}
