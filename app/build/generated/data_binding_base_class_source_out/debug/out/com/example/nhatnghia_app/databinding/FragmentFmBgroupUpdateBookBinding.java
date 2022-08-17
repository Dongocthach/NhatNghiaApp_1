// Generated by view binder compiler. Do not edit!
package com.example.nhatnghia_app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.nhatnghia_app.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentFmBgroupUpdateBookBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final FrameLayout frameLayout3;

  @NonNull
  public final SearchView searchView;

  @NonNull
  public final RecyclerView updateBookRcv;

  private FragmentFmBgroupUpdateBookBinding(@NonNull FrameLayout rootView,
      @NonNull FrameLayout frameLayout3, @NonNull SearchView searchView,
      @NonNull RecyclerView updateBookRcv) {
    this.rootView = rootView;
    this.frameLayout3 = frameLayout3;
    this.searchView = searchView;
    this.updateBookRcv = updateBookRcv;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentFmBgroupUpdateBookBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentFmBgroupUpdateBookBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_fm_bgroup__update_book, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentFmBgroupUpdateBookBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      FrameLayout frameLayout3 = (FrameLayout) rootView;

      id = R.id.search_view;
      SearchView searchView = ViewBindings.findChildViewById(rootView, id);
      if (searchView == null) {
        break missingId;
      }

      id = R.id.update_book_rcv;
      RecyclerView updateBookRcv = ViewBindings.findChildViewById(rootView, id);
      if (updateBookRcv == null) {
        break missingId;
      }

      return new FragmentFmBgroupUpdateBookBinding((FrameLayout) rootView, frameLayout3, searchView,
          updateBookRcv);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}