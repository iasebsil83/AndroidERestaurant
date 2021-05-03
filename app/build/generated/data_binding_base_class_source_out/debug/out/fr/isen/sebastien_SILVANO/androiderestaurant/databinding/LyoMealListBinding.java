// Generated by view binder compiler. Do not edit!
package fr.isen.sebastien_SILVANO.androiderestaurant.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import fr.isen.sebastien_SILVANO.androiderestaurant.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class LyoMealListBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button mealListButton;

  @NonNull
  public final View mealListContent;

  @NonNull
  public final ConstraintLayout mealListGlobal;

  @NonNull
  public final RecyclerView mealListRecView;

  @NonNull
  public final View mealListTitle;

  @NonNull
  public final TextView mealListTitleText;

  private LyoMealListBinding(@NonNull ConstraintLayout rootView, @NonNull Button mealListButton,
      @NonNull View mealListContent, @NonNull ConstraintLayout mealListGlobal,
      @NonNull RecyclerView mealListRecView, @NonNull View mealListTitle,
      @NonNull TextView mealListTitleText) {
    this.rootView = rootView;
    this.mealListButton = mealListButton;
    this.mealListContent = mealListContent;
    this.mealListGlobal = mealListGlobal;
    this.mealListRecView = mealListRecView;
    this.mealListTitle = mealListTitle;
    this.mealListTitleText = mealListTitleText;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static LyoMealListBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static LyoMealListBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.lyo_meal_list, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static LyoMealListBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.meal_list_button;
      Button mealListButton = rootView.findViewById(id);
      if (mealListButton == null) {
        break missingId;
      }

      id = R.id.meal_list_content;
      View mealListContent = rootView.findViewById(id);
      if (mealListContent == null) {
        break missingId;
      }

      ConstraintLayout mealListGlobal = (ConstraintLayout) rootView;

      id = R.id.meal_list_rec_view;
      RecyclerView mealListRecView = rootView.findViewById(id);
      if (mealListRecView == null) {
        break missingId;
      }

      id = R.id.meal_list_title;
      View mealListTitle = rootView.findViewById(id);
      if (mealListTitle == null) {
        break missingId;
      }

      id = R.id.meal_list_title_text;
      TextView mealListTitleText = rootView.findViewById(id);
      if (mealListTitleText == null) {
        break missingId;
      }

      return new LyoMealListBinding((ConstraintLayout) rootView, mealListButton, mealListContent,
          mealListGlobal, mealListRecView, mealListTitle, mealListTitleText);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
