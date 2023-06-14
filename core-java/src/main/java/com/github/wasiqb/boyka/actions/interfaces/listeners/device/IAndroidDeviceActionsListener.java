/*
 * MIT License
 *
 * Copyright (c) 2023, Wasiq Bhamla
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.github.wasiqb.boyka.actions.interfaces.listeners.device;

import io.appium.java_client.android.nativekey.AndroidKey;

/**
 * Handles all Android device specific events.
 *
 * @author Wasiq Bhamla
 * @since 03-Jun-2023
 */
public interface IAndroidDeviceActionsListener extends IDeviceActionsListener {
    /**
     * Handle key press event.
     *
     * @param key Android key
     */
    default void onPressKey (final AndroidKey key) {
        // not implemented.
    }
}
