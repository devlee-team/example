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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.devlee.myapplication.foo.Callback;
import org.devlee.myapplication.foo.FooController;
import org.devlee.myapplication.foo.FooRepository;
import org.devlee.myapplication.R;

public class MainActivity extends AppCompatActivity {

    private final Callback progressCallback = new ProgressCallback();

    private FooController fooController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fooController = new FooControllerImpl(this);

        doSomething();
    }

    private void doSomething() {
        showProgress();
        fooController.openFoo("some_id", progressCallback);
    }

    private void showProgress() {
        // this activity hasn`t progress
    }

    private void hideProgress() {
        // this activity hasn`t progress
    }

    private final class ProgressCallback extends FinishCallback {
        @Override public void onFinished(Object result, Throwable error) {
            hideProgress();
        }
    }
}
