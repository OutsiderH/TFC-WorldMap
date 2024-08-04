package com.outsiderh.tfcworldmap.client.collection;

public record FixedRect(int x, int y, int w, int h) {
    public int x2() {
        return x + w - 1;
    }
    public int y2() {
        return y + h - 1;
    }
}
