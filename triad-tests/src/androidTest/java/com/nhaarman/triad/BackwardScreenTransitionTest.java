package com.nhaarman.triad;

import com.nhaarman.triad.tests.secondscreen.SecondScreen;

import static com.nhaarman.triad.utils.ViewWaiter.viewNotPresent;
import static com.nhaarman.triad.utils.ViewWaiter.waitUntil;
import static org.assertj.android.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;

public class BackwardScreenTransitionTest extends TestActivityInstrumentationTestCase {

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    getInstrumentation().runOnMainSync(new Runnable() {
      @Override
      public void run() {
        mFlow.goTo(new SecondScreen());
      }
    });

    waitUntil(viewNotPresent(mScreenHolder, com.nhaarman.triad.tests.R.id.view_screen_first));

    getInstrumentation().runOnMainSync(new Runnable() {
      @Override
      public void run() {
        mFlow.goBack();
      }
    });

    waitUntil(viewNotPresent(mScreenHolder, com.nhaarman.triad.tests.R.id.view_screen_second));
  }

  public void test_afterTransition_viewsHaveBeenSwitched() throws InterruptedException {
    assertThat(mScreenHolder.findViewById(com.nhaarman.triad.tests.R.id.view_screen_first), is(not(nullValue())));
    assertThat(mScreenHolder.findViewById(com.nhaarman.triad.tests.R.id.view_screen_second), is(nullValue()));
  }

  public void test_afterTransition_dimmerView_isVisible() {
    assertThat(mDimmerView).isVisible();
  }

  public void test_afterTransition_dimmerView_isFullyTranslucent() {
    assertThat(mDimmerView).hasAlpha(0f);
  }

  public void test_afterTransition_dimmerView_isNotClickable() {
    assertThat(mDimmerView).isNotClickable();
  }

  public void test_afterTransition_dialogHolder_isVisible() {
    assertThat(mDialogHolder).isVisible();
  }

  public void test_afterTransition_dialogHolderContainsNoChildren() {
    assertThat(mDialogHolder).hasChildCount(0);
  }
}
