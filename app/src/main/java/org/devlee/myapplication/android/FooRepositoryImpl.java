/*
 *  Copyright (c) 2017 Pavel Lee.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.devlee.myapplication.android;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import org.devlee.myapplication.foo.Callback;
import org.devlee.myapplication.foo.Foo;
import org.devlee.myapplication.foo.FooController;
import org.devlee.myapplication.foo.FooRepository;

/**
 * Created by Paul on 1/27/2017.
 */

public class FooRepositoryImpl implements FooRepository {
    private static final int LOADER_ID = 10001;

    private final FooLoader fooLoader;
    private final LoaderManager loaderManager;

    public FooRepositoryImpl(Activity activity) {
        this.loaderManager = activity.getLoaderManager();
        this.fooLoader = new FooLoader(activity);
    }

    @Override public Task loadFoo(final Callback<Foo> callback) {
        loaderManager.initLoader(LOADER_ID, null, new LoaderCallbacks(callback));
        return new Task() {
            @Override public void cancel() {
                loaderManager.getLoader(LOADER_ID).cancelLoad();
            }
        };
    }

    private final class LoaderCallbacks implements LoaderManager.LoaderCallbacks<Foo> {
        private final Callback<Foo> callback;

        private LoaderCallbacks(Callback<Foo> callback) {
            this.callback = callback;
        }

        @Override public Loader<Foo> onCreateLoader(int i, Bundle bundle) {
            return fooLoader;
        }

        @Override public void onLoadFinished(Loader<Foo> loader, Foo foo) {
            callback.onSuccess(foo);
        }

        @Override public void onLoaderReset(Loader<Foo> loader) {

        }
    }
}
