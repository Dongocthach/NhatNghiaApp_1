// Generated by view binder compiler. Do not edit!
package com.example.nhatnghia_app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.nhatnghia_app.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class LaDialogUpdatePmBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button dialogUpdatePm1;

  @NonNull
  public final Button dialogUpdatePm2;

  @NonNull
  public final Button dialogUpdatePmNgaytra;

  @NonNull
  public final Spinner dialogUpdatePmStatus;

  @NonNull
  public final TextView dialogUpdatePmTv0;

  @NonNull
  public final TextView dialogUpdatePmTv1;

  private LaDialogUpdatePmBinding(@NonNull LinearLayout rootView, @NonNull Button dialogUpdatePm1,
      @NonNull Button dialogUpdatePm2, @NonNull Button dialogUpdatePmNgaytra,
      @NonNull Spinner dialogUpdatePmStatus, @NonNull TextView dialogUpdatePmTv0,
      @NonNull TextView dialogUpdatePmTv1) {
    this.rootView = rootView;
    this.dialogUpdatePm1 = dialogUpdatePm1;
    this.dialogUpdatePm2 = dialogUpdatePm2;
    this.dialogUpdatePmNgaytra = dialogUpdatePmNgaytra;
    this.dialogUpdatePmStatus = dialogUpdatePmStatus;
    this.dialogUpdatePmTv0 = dialogUpdatePmTv0;
    this.dialogUpdatePmTv1 = dialogUpdatePmTv1;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static LaDialogUpdatePmBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static LaDialogUpdatePmBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.la_dialog_update_pm, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static LaDialogUpdatePmBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.dialog_update_pm_1;
      Button dialogUpdatePm1 = ViewBindings.findChildViewById(rootView, id);
      if (dialogUpdatePm1 == null) {
        break missingId;
      }

      id = R.id.dialog_update_pm_2;
      Button dialogUpdatePm2 = ViewBindings.findChildViewById(rootView, id);
      if (dialogUpdatePm2 == null) {
        break missingId;
      }

      id = R.id.dialog_update_pm_ngaytra;
      Button dialogUpdatePmNgaytra = ViewBindings.findChildViewById(rootView, id);
      if (dialogUpdatePmNgaytra == null) {
        break missingId;
      }

      id = R.id.dialog_update_pm_status;
      Spinner dialogUpdatePmStatus = ViewBindings.findChildViewById(rootView, id);
      if (dialogUpdatePmStatus == null) {
        break missingId;
      }

      id = R.id.dialog_update_pm_tv0;
      TextView dialogUpdatePmTv0 = ViewBindings.findChildViewById(rootView, id);
      if (dialogUpdatePmTv0 == null) {
        break missingId;
      }

      id = R.id.dialog_update_pm_tv1;
      TextView dialogUpdatePmTv1 = ViewBindings.findChildViewById(rootView, id);
      if (dialogUpdatePmTv1 == null) {
        break missingId;
      }

      return new LaDialogUpdatePmBinding((LinearLayout) rootView, dialogUpdatePm1, dialogUpdatePm2,
          dialogUpdatePmNgaytra, dialogUpdatePmStatus, dialogUpdatePmTv0, dialogUpdatePmTv1);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
