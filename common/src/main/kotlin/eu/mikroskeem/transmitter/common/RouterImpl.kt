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

package eu.mikroskeem.transmitter.common

import eu.mikroskeem.transmitter.api.RequestHandler
import eu.mikroskeem.transmitter.api.Router
import java.lang.IllegalStateException
import java.lang.RuntimeException
import java.lang.reflect.Field
import java.lang.reflect.Modifier
import java.util.function.Consumer

/**
 * @author Mark Vainomaa
 */
class RouterImpl: Router {
    private val childRouters = HashMap<String, Router>()

    override fun handle(method: String, path: String, handler: RequestHandler) {
        TODO("not implemented")
    }

    override fun handle(path: String, otherRouter: Router) {
        //childRouters[path] = otherRouter
        TODO("not implemented")
    }

    override fun childRouter(initializer: Consumer<Router>): Router {
        return RouterImpl().apply {
            initializer.accept(this)
        }
    }

    companion object {
        private var initialized = false
        private val rootRouter by lazy { RouterImpl() }

        fun initialize() {
            if (initialized) {
                throw IllegalStateException("Root router is already initialized")
            }

            try {
                val rootRouterField = Router::class.java.getField("ROOT")
                val modifiersField = Field::class.java.getDeclaredField("modifiers").apply { isAccessible = true }
                modifiersField.setInt(rootRouterField, modifiersField.getInt(rootRouterField) and Modifier.FINAL.inv())

                rootRouterField.set(null, rootRouter)
                initialized = true
            } catch (e: Exception) {
                throw RuntimeException("Failed to set up root router", e)
            }
        }
    }
}