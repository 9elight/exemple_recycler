package com.delight.weatherapp;

public interface Lifecycles<V> {
    void bind(V view);

    void unbind();
}
