package com.outsiderh.tfcworldmap.client.collection;

public class Rect {
    public int x;
    public int y;
    public int w;
    public int h;
    public Rect() {
        this(0, 0, 0, 0);
    }
    public Rect(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    public Rect copy() {
        return new Rect(x, y, w, h);
    }
    public FixedRect fixed() {
        return new FixedRect(x, y, w, h);
    }
    public int x2() {
        return x + w - 1;
    }
    public int y2() {
        return y + h - 1;
    }
}
