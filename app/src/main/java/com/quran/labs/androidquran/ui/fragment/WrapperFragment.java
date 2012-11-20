package com.quran.labs.androidquran.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.actionbarsherlock.app.SherlockFragment;
import com.quran.labs.androidquran.R;

public class WrapperFragment extends SherlockFragment {
   private static final String TAG = "WrapperFragment";
   private static final String PAGE_NUMBER_EXTRA = "pageNumber";
   private static final String MODE = "mode";

   public static final int MODE_ARABIC = 0;
   public static final int MODE_TRANSLATION = 1;

   private int mPage;
   private int mMode;

   public static WrapperFragment newInstance(int page, int mode){
      final WrapperFragment f = new WrapperFragment();
      final Bundle args = new Bundle();
      args.putInt(PAGE_NUMBER_EXTRA, page);
      args.putInt(MODE, mode);
      f.setArguments(args);
      return f;
   }

   @Override
   public void onCreate(Bundle savedInstanceState){
      super.onCreate(savedInstanceState);
      Bundle arguments = getArguments();
      mPage = arguments != null?
              arguments.getInt(PAGE_NUMBER_EXTRA) : -1;
      mMode = arguments != null?
              arguments.getInt(MODE) : MODE_ARABIC;

   }

   @Override
   public View onCreateView(LayoutInflater inflater,
                            ViewGroup container, Bundle savedInstanceState){
      final View view = inflater.inflate(R.layout.wrapper_fragment,
              container, false);
      FragmentManager cFm = getChildFragmentManager();
      Fragment leftFragment = cFm.findFragmentByTag("left_" + mPage);
      Fragment rightFragment = cFm.findFragmentByTag("right_" + mPage);

      if (leftFragment == null || rightFragment == null){
         FragmentTransaction transaction = cFm.beginTransaction();
         if (leftFragment == null){
            leftFragment = getPageFragment(mPage * 2);
            transaction.add(R.id.left_page, leftFragment, "left_" + mPage);
         }

         if (rightFragment == null){
            rightFragment = getPageFragment((mPage * 2) - 1);
            transaction.add(R.id.right_page, rightFragment,  "right_" + mPage);
         }
         transaction.commit();
      }
      return view;
   }

   private Fragment getPageFragment(int page){
      if (mMode == MODE_TRANSLATION){
         return TranslationFragment.newInstance(page);
      }
      return QuranPageFragment.newInstance(page);
   }
}
