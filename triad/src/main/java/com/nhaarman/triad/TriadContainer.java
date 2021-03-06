package com.nhaarman.triad;

import android.view.View;
import com.nhaarman.triad.container.Container;
import com.nhaarman.triad.screen.Screen;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A {@link Container} which hosts all {@link View}s belonging to {@link Screen}s in the application.
 *
 * @param <M> The main module in the application. See {@link TriadPresenter}.
 */
interface TriadContainer<M> extends Container<TriadPresenter<M>, TriadContainer<M>> {

  /**
   * Transitions from {@code oldView} to {@code newView}.
   * If both {@link View}s are @link null}, nothing happens.
   *
   * @param oldView The old {@link View} to display an exit animation for.
   * @param newView The new {@link View} to display an entering animation for.
   */
  void transition(@Nullable View oldView, @Nullable View newView);

  /**
   * Adds given {@link View} as a dialog.
   *
   * @param dialogView The {@link View} to show.
   */
  void showDialog(@NotNull View dialogView);

  void dismissDialog(@NotNull View dialogView);
}
