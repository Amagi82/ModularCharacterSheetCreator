package amagi82.modularcharactersheetcreator.presenters;

import android.os.Bundle;
import android.support.annotation.NonNull;

import amagi82.modularcharactersheetcreator.activities.MainActivity;

/*
Best practices
Save your request arguments inside Presenter

The rule is simple: the presenter's main purpose is to manage requests. So View should not handle or restart requests itself.
From a View's perspective, background tasks are something that never disappear and will always return a result or an error without any callbacks.

Place all methods that are connected with background tasks in the presenter. It just remembers which requests it should execute, and if a
process restarts during execution, presenter will execute it again.
 */
public class MainPresenter extends BasePresenter<MainActivity> {

    public static final String NAME_1 = "Chuck Norris";
    public static final String NAME_2 = "Jackie Chan";
    public static final String DEFAULT_NAME = NAME_1;
    private static final int REQUEST_ITEMS = 1;

    private static final String NAME_KEY = MainPresenter.class.getName() + "#name";

    private String name = DEFAULT_NAME;

    @Override
    public void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        if (savedState != null) name = savedState.getString(NAME_KEY);

//        restartableLatestCache(REQUEST_ITEMS,
//                new Func0<Observable<ServerAPI.Response>>() {
//                    @Override
//                    public Observable<ServerAPI.Response> call() {
//                        return App.getServerAPI()
//                                .getItems(name.split("\\s+")[0], name.split("\\s+")[1])
//                                .observeOn(AndroidSchedulers.mainThread());
//                    }
//                },
//                new Action2<MainActivity, ServerAPI.Response>() {
//                    @Override
//                    public void call(MainActivity activity, ServerAPI.Response response) {
//                        activity.onItems(response.items, name);
//                    }
//                },
//                new Action2<MainActivity, Throwable>() {
//                    @Override
//                    public void call(MainActivity activity, Throwable throwable) {
//                        activity.onNetworkError(throwable);
//                    }
//                });

        if (savedState == null) start(REQUEST_ITEMS);
    }

    @Override
    public void onSave(@NonNull Bundle state) {
        super.onSave(state);
        state.putString(NAME_KEY, name);
    }

    public void request(String name) {
        this.name = name;
        start(REQUEST_ITEMS);
    }
}