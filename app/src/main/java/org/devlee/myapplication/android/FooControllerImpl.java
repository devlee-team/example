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
import android.content.Intent;
import org.devlee.myapplication.foo.Callback;
import org.devlee.myapplication.foo.Foo;
import org.devlee.myapplication.foo.FooController;
import org.devlee.myapplication.foo.FooRepository;

/**
 * Created by Paul on 1/27/2017.
 */

public class FooControllerImpl implements FooController {

    private final Activity activity;
    private final FooRepository fooRepository;


    public FooControllerImpl(MainActivity activity) {
        this.activity = activity;
        this.fooRepository = new FooRepositoryImpl(activity);
    }

    @Override public FooRepository.Task openFoo(String some_id, final Callback finishCallback) {
        return fooRepository.loadFoo(new Callback<Foo>() {
            @Override public void onSuccess(Foo foo) {
                finishCallback.onSuccess(foo);
            }

            @Override public void onFailed(Throwable error) {
                finishCallback.onFailed(error);
            }
        });
    }

    private Intent createFooActivityIntent(Foo foo) {
        Intent intent = new Intent();
        // Bla-bla-bla ....
        return intent;
    }
}
