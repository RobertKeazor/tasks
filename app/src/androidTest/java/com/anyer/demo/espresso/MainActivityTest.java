package com.anyer.demo.espresso;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.anyer.demo.R;
import com.anyer.demo.activity.MainActivity;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.anyer.demo.espresso.OrientationChangeAction.orientationLandscape;
import static com.anyer.demo.espresso.OrientationChangeAction.orientationPortrait;

/**
 * Created by anyer on 8/16/15.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private final String ITEM_TEXT = "demo";

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        getActivity();
    }

    public void testNewItemIsAddedToTheList() {
        onView(ViewMatchers.withId(R.id.fragment_item_form_text)).perform(typeText(ITEM_TEXT));
        onView(withId(R.id.fragment_item_form_button)).perform(click());

        onView(withId(R.id.fragment_list_recyclerview)).perform(RecyclerViewActions.actionOnItem
                (hasDescendant(withText(ITEM_TEXT)), click()));
    }

    public void testAfterRotateScreenTheListKeepItemsState() {
        onView(isRoot()).perform(orientationPortrait());

        onView(ViewMatchers.withId(R.id.fragment_item_form_text)).perform(typeText(ITEM_TEXT));
        onView(withId(R.id.fragment_item_form_button)).perform(click());
        onView(withId(R.id.fragment_list_recyclerview)).perform(RecyclerViewActions.actionOnItem
                (hasDescendant(withText(ITEM_TEXT)), click()));

        onView(isRoot()).perform(orientationLandscape());

        onView(withId(R.id.fragment_list_recyclerview)).perform(RecyclerViewActions.actionOnItem
                (hasDescendant(withText(ITEM_TEXT)), click()));

        onView(withId(R.id.fragment_list_recyclerview)).perform(RecyclerViewActions.actionOnItem
                (hasDescendant(withImageView(true)), click()));
    }

    public void testItemChangeIconAfterClick() {
        onView(ViewMatchers.withId(R.id.fragment_item_form_text)).perform(typeText(ITEM_TEXT));
        onView(withId(R.id.fragment_item_form_button)).perform(click());

        // Tap on non-visible image is handled by the layout
        onView(withId(R.id.fragment_list_recyclerview)).perform(RecyclerViewActions.actionOnItem
                (hasDescendant(withImageView(false)), click()));

        getInstrumentation().waitForIdleSync();

        onView(withId(R.id.fragment_list_recyclerview)).perform(RecyclerViewActions.actionOnItem
                (hasDescendant(withImageView(true)), click()));
    }

    private Matcher<View> withImageView(final boolean visibility) {
        return new BoundedMatcher<View, ImageView>(ImageView.class) {
            @Override
            public boolean matchesSafely(ImageView imageView) {
                if (visibility) {
                    return imageView.getVisibility() == View.VISIBLE;
                } else {
                    return imageView.getVisibility() == View.GONE;
                }
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with image view: ");
            }
        };
    }
}
