package zoker.demo.music.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.zekers.ui.view.recycler.DividerItemDecoration;
import com.zekers.utils.logger.Logger;
import com.zekers.utils.rx.EventBus.Events;
import com.zekers.utils.rx.EventBus.RxBus;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import zoker.demo.music.Constants;
import zoker.demo.music.MusicApp;
import zoker.demo.music.control.contract.SongsContract;
import zoker.demo.music.control.events.MetaChangedEvent;
import zoker.demo.music.control.injector.Component.ApplicationComponent;
import zoker.demo.music.control.injector.Component.DaggerSongsComponent;
import zoker.demo.music.control.injector.Component.SongsComponent;
import zoker.demo.music.control.injector.module.ActivityModule;
import zoker.demo.music.control.injector.module.DbModule;
import zoker.demo.music.control.injector.module.KuGouModule;
import zoker.demo.music.control.injector.module.SongsModule;
import zoker.demo.music.databinding.FragmentRecyclerviewBinding;
import zoker.demo.music.model.Song;
import zoker.demo.music.utils.PreferencesUtility;
import zoker.demo.music.view.MusicListTypeFactory;
import zoker.demo.music.view.adapter.SongsListAdapter;

/**
 * Created by Zoker on 2017/2/21.
 */

public class SongsFragment extends Fragment implements SongsContract.View{
    @Inject
    SongsContract.Presenter mPresenter;
    private PreferencesUtility mPreferences;

    private Constants.PLAYLIST_TYPE action;

    private SongsListAdapter adapter;
    private FragmentRecyclerviewBinding binding;

    public static Fragment newInstance(Constants.PLAYLIST_TYPE action) {
        Bundle args = new Bundle();
        args.putSerializable(Constants.LIST_TYPE, action);
        SongsFragment fragment = new SongsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependences();
        mPresenter.attachView(this);
        if (getArguments() != null) {
            action = (Constants.PLAYLIST_TYPE) getArguments().getSerializable(Constants.LIST_TYPE);
        }
        mPreferences=PreferencesUtility.getInstance(getContext());
        adapter = new SongsListAdapter(MusicListTypeFactory.getInstance());
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRecyclerviewBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerview.setAdapter(adapter);
        binding.recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST, true));

        mPresenter.loadSongs(action);

        switch (action){
            case FAVOURATE:
                subscribeFavourateSongEvent();
                break;
            case RECENTPLAY:
                subscribeRecentlyPlayEvent();
                break;
            default:
                subscribeMediaUpdateEvent();
                break;
        }
        subscribeMetaChangedEvent();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().unSubscribe(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void injectDependences(){
        ApplicationComponent applicationComponent= ((MusicApp)getActivity().getApplication()).getApplicationComponent();
        SongsComponent songsComponent= DaggerSongsComponent.builder()
                .applicationComponent(applicationComponent)
                .activityModule(new ActivityModule(getActivity()))
                .songsModule(new SongsModule())
                .kuGouModule(new KuGouModule())
                .dbModule(new DbModule(getContext()))
                .build();
        songsComponent.inject(this);
    }

    @Override
    public void showSongs(List<Song> songList) {
        Logger.d(new Gson().toJson(songList));
        binding.emptyView.setVisibility(View.GONE);
        binding.recyclerview.setVisibility(View.VISIBLE);
        adapter.setSongs(songList);
    }

    @Override
    public void showEmptyView() {
        binding.emptyView.setVisibility(View.VISIBLE);
        binding.recyclerview.setVisibility(View.INVISIBLE);
    }

    private void subscribeMediaUpdateEvent() {
        Subscription subscription = RxBus.getSubscriber()
                .setEvent(Events.MediaUpdate)
                .onNext(new Action1<Events<?>>() {
                    @Override
                    public void call(Events<?> events) {
                        mPresenter.loadSongs(action);
                    }
                })
                .create();
        RxBus.getInstance().addSubscription(this, subscription);
    }

    private void subscribeFavourateSongEvent() {
        Subscription subscription = RxBus.getSubscriber()
                .setEvent(Events.Favour)
                .onNext(new Action1<Events<?>>() {
                    @Override
                    public void call(Events<?> events) {
                        mPresenter.loadSongs(action);
                    }
                })
                .create();
        RxBus.getInstance().addSubscription(this, subscription);
    }

    private void subscribeRecentlyPlayEvent() {
        Subscription subscription = RxBus.getSubscriber()
                .setEvent(Events.Recently)
                .onNext(new Action1<Events<?>>() {
                    @Override
                    public void call(Events<?> events) {
                        mPresenter.loadSongs(action);
                    }
                })
                .create();
        RxBus.getInstance().addSubscription(this, subscription);
    }

    private void subscribeMetaChangedEvent() {
        Subscription subscription=RxBus.getSubscriber()
                .setEvent(Events.Meta)
                .onNext(new Action1<Events<?>>() {
                    @Override
                    public void call(Events<?> events) {
                        MetaChangedEvent event=events.getContent();
                        adapter.notifyDataSetChanged();
                    }
                })
                .create();

        RxBus.getInstance().addSubscription(this, subscription);
    }
}
