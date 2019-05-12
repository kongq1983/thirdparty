package com.kq.javassist.entity;

/**
 * Point
 *
 * @author kq
 * @date 2019/5/12
 */
public class Point {
    int x, y;
    void move(int dx, int dy) { x += dx; y += dy; }
}
