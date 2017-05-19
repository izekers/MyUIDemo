package zoker.demo.music.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.zekers.ui.utils.DensityUtils;
import com.zekers.ui.view.widget.SimpleFragmentPagerAdapter;
import com.zekers.utils.logger.Logger;

import java.util.List;

import zoker.demo.music.Constants;
import zoker.demo.music.R;
import zoker.demo.music.databinding.FragmentMainBinding;

/**
 * Created by Zoker on 2017/2/21.
 */

public class MainFragment extends Fragment {

    private Constants.PLAYLIST_TYPE action;
    private FragmentMainBinding binding;
    private SimpleFragmentPagerAdapter adapter;


    public static MainFragment newInstance(Constants.PLAYLIST_TYPE playlist_type) {
        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.LIST_TYPE,playlist_type);
        mainFragment.setArguments(bundle);
        return mainFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            action=(Constants.PLAYLIST_TYPE) getArguments().getSerializable(Constants.LIST_TYPE);
            Logger.d(new Gson().toJson(action));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setToolBar();

        binding.tabs.setupWithViewPager(binding.viewpager);

        if (binding.viewpager !=null){
            setupViewPager(binding.viewpager);
            binding.viewpager.setOffscreenPageLimit(2);
//            binding.viewpager.setCurrentItem();
        }
    }

    public void setToolBar(){
        //一个浸入式方案,后面尝试改成databinding式
        if (Build.VERSION.SDK_INT < 21 && binding.statusBar != null && binding.statusBar.statusBar != null) {
            binding.statusBar.statusBar.setVisibility(View.GONE);
            int statusBarHeight = DensityUtils.getStatusBarHeight(getContext());
            binding.toolbar.setPadding(0, statusBarHeight, 0, 0);
            AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) binding.toolbar.getLayoutParams();
            params.setScrollFlags(0);
            binding.toolbar.setLayoutParams(params);
        }

        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.toolbar);
        final ActionBar ab = ((AppCompatActivity) getActivity()).getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        ab.setTitle(action.getTitle());
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new SimpleFragmentPagerAdapter(getChildFragmentManager());
        adapter.addFragment(SongsFragment.newInstance(action), this.getString(R.string.songs));
        adapter.addFragment(ArtistFragment.newInstance(action), this.getString(R.string.artists));
        adapter.addFragment(AlbumFragment.newInstance(action), this.getString(R.string.albums));
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
