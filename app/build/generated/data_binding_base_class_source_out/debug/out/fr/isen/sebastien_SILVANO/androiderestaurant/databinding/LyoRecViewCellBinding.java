// Generated by view binder compiler. Do not edit!
package fr.isen.sebastien_SILVANO.androiderestaurant.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import fr.isen.sebastien_SILVANO.androiderestaurant.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class LyoRecViewCellBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final View cellBg;

  @NonNull
  public final TextView cellContent;

  @NonNull
  public final ImageView cellPict;

  @NonNull
  public final TextView cellTitle;

  @NonNull
  public final ConstraintLayout recViewCellGlobal;

  private LyoRecViewCellBinding(@NonNull ConstraintLayout rootView, @NonNull View cellBg,
      @NonNull TextView cellContent, @NonNull ImageView cellPict, @NonNull TextView cellTitle,
      @NonNull ConstraintLayout recViewCellGlobal) {
    this.rootView = rootView;
    this.cellBg = cellBg;
    this.cellContent = cellContent;
    this.cellPict = cellPict;
    this.cellTitle = cellTitle;
    this.recViewCellGlobal = recViewCellGlobal;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static LyoRecViewCellBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static LyoRecViewCellBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.lyo_rec_view_cell, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static LyoRecViewCellBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cell_bg;
      View cellBg = rootView.findViewById(id);
      if (cellBg == null) {
        break missingId;
      }

      id = R.id.cell_content;
      TextView cellContent = rootView.findViewById(id);
      if (cellContent == null) {
        break missingId;
      }

      id = R.id.cell_pict;
      ImageView cellPict = rootView.findViewById(id);
      if (cellPict == null) {
        break missingId;
      }

      id = R.id.cell_title;
      TextView cellTitle = rootView.findViewById(id);
      if (cellTitle == null) {
        break missingId;
      }

      ConstraintLayout recViewCellGlobal = (ConstraintLayout) rootView;

      return new LyoRecViewCellBinding((ConstraintLayout) rootView, cellBg, cellContent, cellPict,
          cellTitle, recViewCellGlobal);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
