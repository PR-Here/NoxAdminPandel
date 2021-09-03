package com.pankaj.adminnox.Adapter;


import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.pankaj.adminnox.Fragment.Arcade_join_player_fragment;
import com.pankaj.adminnox.Fragment.ClassicDuo_join_player_fragment;
import com.pankaj.adminnox.Fragment.ClassicSolo_join_player_fragment;
import com.pankaj.adminnox.Fragment.Classic_join_player_fragment;
import com.pankaj.adminnox.Fragment.LiteDuo_join_player_fragment;
import com.pankaj.adminnox.Fragment.LiteSolo_join_player_fragment;
import com.pankaj.adminnox.Fragment.LiteSquad_join_player_fragment;
import com.pankaj.adminnox.Fragment.LiteTDM_join_player_fragment;
import com.pankaj.adminnox.Fragment.Signup_fragment;

public class FragmentAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public FragmentAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Signup_fragment classicMode = new Signup_fragment();
                return classicMode;
//            case 1:
//                Classic_join_player_fragment arcadeMode = new Classic_join_player_fragment();
//                return arcadeMode;
//            case 2:
//                ClassicDuo_join_player_fragment requestFragment = new ClassicDuo_join_player_fragment();
//                return requestFragment;
//            case 3:
//                ClassicSolo_join_player_fragment classicSolo_join_player_fragment=new ClassicSolo_join_player_fragment();
//                return classicSolo_join_player_fragment;
//            case 4:
//                Arcade_join_player_fragment arcade_join_player_fragment=new Arcade_join_player_fragment();
//                return arcade_join_player_fragment;
            case 1:
                LiteSolo_join_player_fragment liteSolo_join_player_fragment=new LiteSolo_join_player_fragment();
                return liteSolo_join_player_fragment;
            case 2:
                LiteDuo_join_player_fragment liteDuo_join_player_fragment=new LiteDuo_join_player_fragment();
                return liteDuo_join_player_fragment;
            case 3:
                LiteSquad_join_player_fragment liteSquad_join_player_fragment=new LiteSquad_join_player_fragment();
                return liteSquad_join_player_fragment;
            case 4:
                LiteTDM_join_player_fragment liteTDM_join_player_fragment=new LiteTDM_join_player_fragment();
                return liteTDM_join_player_fragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
