package com.quran.labs.androidquran.ui.helpers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.quran.labs.androidquran.ui.fragment.WrapperFragment;

public class QuranDualPageAdapter extends QuranPageAdapter {
   private static String TAG = "QuranDualPageAdapter";

   public QuranDualPageAdapter(FragmentManager fm){
      super(fm);
   }

   public QuranDualPageAdapter(FragmentManager fm,
                               boolean isShowingTranslation){
      super(fm, isShowingTranslation);
   }

   @Override
   public int getCount(){ return 302; }

   @Override
   public Fragment getItem(int position){
      android.util.Log.d(TAG, "getting dual page: " + (302-position));
      return WrapperFragment.newInstance(302 - position,
              mIsShowingTranslation? 1 : 0);
   }
}
