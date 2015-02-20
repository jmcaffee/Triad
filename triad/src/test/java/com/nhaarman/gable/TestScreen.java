package com.nhaarman.triad;

import com.nhaarman.triad.screen.Screen;
import org.jetbrains.annotations.NotNull;

public class TestScreen extends Screen<TestPresenter, TestRelativeLayoutContainer, TestComponent> {

  @Override
  protected int getLayoutResId() {
    throw new UnsupportedOperationException("Not yet implemented"); // TODO: Implement getLayoutResId.
  }

  @NotNull
  @Override
  protected TestPresenter createPresenter(@NotNull final TestComponent testComponent) {
    return new TestPresenter();
  }
}