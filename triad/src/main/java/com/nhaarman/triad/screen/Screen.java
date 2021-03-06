package com.nhaarman.triad.screen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.nhaarman.triad.container.ScreenContainer;
import com.nhaarman.triad.presenter.Presenter;
import com.nhaarman.triad.presenter.ScreenPresenter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A class that creates the {@link ScreenContainer} and {@link ScreenPresenter} for a screen in the application.
 *
 * Since the creation of a {@link Presenter} may need additional dependencies, a {@code main component} is supplied
 * when requesting the {@link Presenter} instance. This {@code main component} should contain all dependencies necessary
 * for the {@link Presenter}, and is to be supplied by the implementer.
 *
 * @param <P> The specialized {@link ScreenPresenter} type.
 * @param <C> The specialized {@link ScreenContainer} type.
 * @param <M> The type of the {@code main component}.
 */
public abstract class Screen<P extends ScreenPresenter<P, C>, C extends ScreenContainer<P, C>, M> {

  /**
   * The {@link P} that is tied to this {@link Screen} instance.
   */
  @Nullable
  private P mPresenter;

  /**
   * Returns the layout resource id for this {@code Screen}.
   *
   * The root of this resource should be an implementation of {@link C}.
   */
  protected abstract int getLayoutResId();

  /**
   * Creates a {@link P} for this {@code Screen}.
   *
   * @param m The {@code main component} that can be used to create the {@link P}.
   *
   * @return The created {@link P}.
   */
  @NotNull
  protected abstract P createPresenter(@NotNull final M m);

  /**
   * Returns whether this screen should be presented as a dialog.
   */
  public boolean isDialog() {
    return false;
  }

  /**
   * Inflates the layout resource id returned by {@link #getLayoutResId()}, and returns the {@link C} instance.
   * Does not attach the layout to {@code parent}.
   *
   * @param parent The parent {@link ViewGroup} the created {@link View} will be attached to.
   *
   * @return The created {@link C}.
   */
  @NotNull
  public final C createView(@NotNull final ViewGroup parent) {
    return (C) LayoutInflater.from(parent.getContext()).inflate(getLayoutResId(), parent, false);
  }

  /**
   * Returns the {@link P} that is tied to this {@code Screen} instance.
   * This instance is lazily instantiated.
   *
   * @param component The {@code main component} to retrieve dependencies from.
   *
   * @return The {@link P}.
   */
  @NotNull
  public final P getPresenter(@NotNull final M component) {
    if (mPresenter == null) {
      mPresenter = createPresenter(component);
    }
    return mPresenter;
  }
}
