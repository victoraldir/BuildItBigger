package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by victoraldir on 08/02/2017.
 */
@RunWith(AndroidJUnit4.class)
public class FetchJokeAsyncTaskTest {

    private Context appContext;

    @Before
    public void setup(){
        appContext = InstrumentationRegistry.getContext();
    }

    @Test
    public void shouldFetchJoke() throws Exception {

        FetchJokeAsyncTask.OnJokeAsyncTaskListener callBack = new FetchJokeAsyncTask.OnJokeAsyncTaskListener() {
            @Override
            public void onJokeReceived(String joke) {
                assertNotNull(joke);
                assertFalse(joke.isEmpty());
            }
        };

        new FetchJokeAsyncTask(callBack).execute(appContext);

        synchronized (callBack){
            callBack.wait();
        }

    }

}