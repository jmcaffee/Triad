package com.nhaarman.triad.presenter;

import com.nhaarman.triad.TestRelativeLayoutContainer;
import com.nhaarman.triad.TestPresenter;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;

@SuppressWarnings("AccessingNonPublicFieldOfAnotherObject")
public class PresenterTest {

  private TestPresenter mPresenter;

  @Before
  public void setUp() {
    mPresenter = new TestPresenter();
  }

  @Test
  public void initially_theContainerIsNull() {
    assertThat(mPresenter.getContainer(), is(nullValue()));
  }

  @Test
  public void afterAcquiringContainer_theContainerIsNotNull() {
    /* Given */
    TestRelativeLayoutContainer container = mock(TestRelativeLayoutContainer.class);

    /* When */
    mPresenter.acquire(container);

    /* Then */
    assertThat(mPresenter.getContainer(), is(not(nullValue())));
  }

  @Test
  public void afterReleasingContainer_theContainerIsNull() {
    /* Given */
    TestRelativeLayoutContainer container = mock(TestRelativeLayoutContainer.class);
    mPresenter.acquire(container);

    /* When */
    mPresenter.releaseContainer();

    /* Then */
    assertThat(mPresenter.getContainer(), is(nullValue()));
  }

  @Test
  public void afterAcquiringContainer_onControlGainedIsCalled() {
    /* Given */
    TestRelativeLayoutContainer container = mock(TestRelativeLayoutContainer.class);

    /* When */
    mPresenter.acquire(container);

    /* Then */
    assertThat(mPresenter.onControlGainedCalled, is(true));
    assertThat(mPresenter.onControlLostCalled, is(false));
  }

  @Test
  public void afterReleasingContainer_onControlLostIsCalled() {
    /* Given */
    TestRelativeLayoutContainer container = mock(TestRelativeLayoutContainer.class);
    mPresenter.acquire(container);
    mPresenter.onControlGainedCalled = false;

    /* When */
    mPresenter.releaseContainer();

    /* Then */
    assertThat(mPresenter.onControlLostCalled, is(true));
    assertThat(mPresenter.onControlGainedCalled, is(false));
  }
}