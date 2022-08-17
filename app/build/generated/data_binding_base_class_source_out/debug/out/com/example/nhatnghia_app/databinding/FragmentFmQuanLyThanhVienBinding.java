// Generated by view binder compiler. Do not edit!
package com.example.nhatnghia_app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.nhatnghia_app.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentFmQuanLyThanhVienBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final RelativeLayout kcRelativeLayout;

  @NonNull
  public final RecyclerView recycleViewThanhvien;

  @NonNull
  public final SearchView thanhvienSearchview;

  @NonNull
  public final FloatingActionButton tvFab;

  private FragmentFmQuanLyThanhVienBinding(@NonNull FrameLayout rootView,
      @NonNull RelativeLayout kcRelativeLayout, @NonNull RecyclerView recycleViewThanhvien,
      @NonNull SearchView thanhvienSearchview, @NonNull FloatingActionButton tvFab) {
    this.rootView = rootView;
    this.kcRelativeLayout = kcRelativeLayout;
    this.recycleViewThanhvien = recycleViewThanhvien;
    this.thanhvienSearchview = thanhvienSearchview;
    this.tvFab = tvFab;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentFmQuanLyThanhVienBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentFmQuanLyThanhVienBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_fm__quan_ly_thanh_vien, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentFmQuanLyThanhVienBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.kc_relative_layout;
      RelativeLayout kcRelativeLayout = ViewBindings.findChildViewById(rootView, id);
      if (kcRelativeLayout == null) {
        break missingId;
      }

      id = R.id.recycle_view_thanhvien;
      RecyclerView recycleViewThanhvien = ViewBindings.findChildViewById(rootView, id);
      if (recycleViewThanhvien == null) {
        break missingId;
      }

      id = R.id.thanhvien_searchview;
      SearchView thanhvienSearchview = ViewBindings.findChildViewById(rootView, id);
      if (thanhvienSearchview == null) {
        break missingId;
      }

      id = R.id.tv_fab;
      FloatingActionButton tvFab = ViewBindings.findChildViewById(rootView, id);
      if (tvFab == null) {
        break missingId;
      }

      return new FragmentFmQuanLyThanhVienBinding((FrameLayout) rootView, kcRelativeLayout,
          recycleViewThanhvien, thanhvienSearchview, tvFab);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}