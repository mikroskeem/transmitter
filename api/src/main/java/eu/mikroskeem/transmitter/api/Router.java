/*
 * This file is part of project Transmitter, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2019 Mark Vainomaa <mikroskeem@mikroskeem.eu>
 * Copyright (c) Contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package eu.mikroskeem.transmitter.api;

import java.util.function.Consumer;

/**
 * @author Mark Vainomaa
 */
public interface Router {
    void handle(String method, String path, RequestHandler handler);

    default void get(String path, RequestHandler handler) {
        handle("GET", path, handler);
    }

    default void post(String path, RequestHandler handler) {
        handle("POST", path, handler);
    }

    default void put(String path, RequestHandler handler) {
        handle("PUT", path, handler);
    }

    default void delete(String path, RequestHandler handler) {
        handle("DELETE", path, handler);
    }

    default void patch(String path, RequestHandler handler) {
        handle("PATCH", path, handler);
    }

    void handle(String path, Router otherRouter);

    Router childRouter(Consumer<Router> initializer);

    Router ROOT = null;
}
