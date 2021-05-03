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
import androidx.viewbinding.ViewBinding;
import com.synnapps.carouselview.CarouselView;
import fr.isen.sebastien_SILVANO.androiderestaurant.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class LyoMealBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button addToCart;

  @NonNull
  public final TextView mealContentText;

  @NonNull
  public final ConstraintLayout mealGlobal;

  @NonNull
  public final CarouselView mealPict;

  @NonNull
  public final TextView mealPricePerUnit;

  @NonNull
  public final TextView mealPriceTotal;

  @NonNull
  public final TextView mealTitle;

  @NonNull
  public final Button removeFromCart;

  private LyoMealBinding(@NonNull ConstraintLayout rootView, @NonNull Button addToCart,
      @NonNull TextView mealContentText, @NonNull ConstraintLayout mealGlobal,
      @NonNull CarouselView mealPict, @NonNull TextView mealPricePerUnit,
      @NonNull TextView mealPriceTotal, @NonNull TextView mealTitle,
      @NonNull Button removeFromCart) {
    this.rootView = rootView;
    this.addToCart = addToCart;
    this.mealContentText = mealContentText;
    this.mealGlobal = mealGlobal;
    this.mealPict = mealPict;
    this.mealPricePerUnit = mealPricePerUnit;
    this.mealPriceTotal = mealPriceTotal;
    this.mealTitle = mealTitle;
    this.removeFromCart = removeFromCart;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static LyoMealBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static LyoMealBinding inflate(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent,
      boolean attachToParent) {
    View root = inflater.inflate(R.layout.lyo_meal, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static LyoMealBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.add_to_cart;
      Button addToCart = rootView.findViewById(id);
      if (addToCart == null) {
        break missingId;
      }

      id = R.id.meal_content_text;
      TextView mealContentText = rootView.findViewById(id);
      if (mealContentText == null) {
        break missingId;
      }

      ConstraintLayout mealGlobal = (ConstraintLayout) rootView;

      id = R.id.meal_pict;
      CarouselView mealPict = rootView.findViewById(id);
      if (mealPict == null) {
        break missingId;
      }

      id = R.id.meal_price_per_unit;
      TextView mealPricePerUnit = rootView.findViewById(id);
      if (mealPricePerUnit == null) {
        break missingId;
      }

      id = R.id.meal_price_total;
      TextView mealPriceTotal = rootView.findViewById(id);
      if (mealPriceTotal == null) {
        break missingId;
      }

      id = R.id.meal_title;
      TextView mealTitle = rootView.findViewById(id);
      if (mealTitle == null) {
        break missingId;
      }

      id = R.id.remove_from_cart;
      Button removeFromCart = rootView.findViewById(id);
      if (removeFromCart == null) {
        break missingId;
      }

      return new LyoMealBinding((ConstraintLayout) rootView, addToCart, mealContentText, mealGlobal,
          mealPict, mealPricePerUnit, mealPriceTotal, mealTitle, removeFromCart);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}