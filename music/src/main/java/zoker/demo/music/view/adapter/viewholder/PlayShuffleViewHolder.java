package zoker.demo.music.view.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.zekers.ui.view.recycler.VisitableViewHolder;

import zoker.demo.music.databinding.ItemPlayShuffleBinding;
import zoker.demo.music.model.Shuffe;

/**
 * Created by Zoker on 2017/2/22.
 */

public class PlayShuffleViewHolder extends VisitableViewHolder<Shuffe> {
    private ItemPlayShuffleBinding binding;
    public PlayShuffleViewHolder(View itemView) {
        super(itemView);
    }

    public PlayShuffleViewHolder setBinding(ItemPlayShuffleBinding binding) {
        this.binding = binding;
        return this;
    }

    @Override
    public void setUpView(Shuffe model, int position, RecyclerView.Adapter adapter) {

    }
}
