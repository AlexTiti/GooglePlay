/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.administrator.googleplay.custom.lib;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


public abstract class TabHelper {

    protected FragmentActivity mActivity;

    protected TabHelper(FragmentActivity activity) {
        mActivity = activity;
    }

    public static TabHelper createInstance(FragmentActivity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            return new TabHelperHoneycomb(activity);
        } else {
            return new TabHelperEclair(activity);
        }
    }

    public CompatTab newTab(String tag) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            return new CompatTabHoneycomb(mActivity, tag);
        } else {
            return new CompatTabEclair(mActivity, tag);
        }
    }

    public abstract void addTab(CompatTab tab);

    protected abstract void onSaveInstanceState(Bundle outState);

    protected abstract void onRestoreInstanceState(Bundle savedInstanceState);

    protected abstract void setUp();
}
