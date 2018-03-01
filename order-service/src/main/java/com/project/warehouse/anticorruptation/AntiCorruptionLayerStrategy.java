package com.project.warehouse.anticorruptation;

public interface AntiCorruptionLayerStrategy<T> {

    T traslate(String body);

}