package zoker.demo.music.view.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zekers.ui.view.recycler.DividerItemDecoration;
import com.zekers.ui.view.recycler.VisitableTypeControl;
import com.zekers.utils.rx.EventBus.RxBus;

import zoker.demo.music.Constants;
import zoker.demo.music.MusicApp;
import zoker.demo.music.R;
import zoker.demo.music.control.injector.Component.ApplicationComponent;
import zoker.demo.music.control.injector.Component.DaggerApplicationComponent;
import zoker.demo.music.control.injector.Component.SongsComponent;
import zoker.demo.music.databinding.FragmentRecyclerviewBinding;
import zoker.demo.music.view.MusicGridTypeFactory;
import zoker.demo.music.view.MusicListTypeFactory;
import zoker.demo.music.view.adapter.AlbumAdapter;

/**
 * Created by Zoker on 2017/2/21.
 */

public class AlbumFragment extends Fragment{

    Constants.PLAYLIST_TYPE action;
    FragmentRecyclerviewBinding binding;
    private GridLayoutManager layoutManager;
    private RecyclerView.ItemDecoration itemDecoration;
    private boolean isGrid=false;
    AlbumAdapter albumAdapter;
    VisitableTypeControl.TypeFactory typeFactory;
    public static Fragment newInstance(Constants.PLAYLIST_TYPE  action){
        Bundle args=new Bundle();
        args.putSerializable(Constants.LIST_TYPE,action);
        AlbumFragment fragment=new AlbumFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependences();

        if (isGrid) {
            layoutManager = new GridLayoutManager(getActivity(), 2);
            typeFactory=MusicGridTypeFactory.getInstance();
        } else {
            layoutManager = new GridLayoutManager(getActivity(), 1);
            typeFactory=MusicListTypeFactory.getInstance();
        }
        if (getArguments() != null) {
            action = (Constants.PLAYLIST_TYPE)getArguments().getSerializable(Constants.LIST_TYPE);
        }
        albumAdapter=new AlbumAdapter(typeFactory);
    }

    public void injectDependences(){
        ApplicationComponent applicationComponent= ((MusicApp)getContext().getApplicationContext()).getApplicationComponent();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=FragmentRecyclerviewBinding.inflate(inflater);
        return binding.rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recyclerview.setLayoutManager(layoutManager);
        binding.recyclerview.setAdapter(albumAdapter);
        setItemDecoration();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().unSubscribe(this);
    }

    private void setItemDecoration() {
        if (isGrid) {
            int spacingInPixels = getActivity().getResources().getDimensionPixelSize(R.dimen.spacing_card_album_grid);
            itemDecoration = new SpacesItemDecoration(spacingInPixels);
        } else {
            itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST, true);
        }
        binding.recyclerview.addItemDecoration(itemDecoration);
    }

    public static class SpacesItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            if (position % 2 == 0) {
                outRect.left = 0;
                outRect.top = space;
                outRect.right = space / 2;
            } else {
                outRect.left = space / 2;
                outRect.top = space;
                outRect.right = 0;
            }
        }
    }
}
