package com.example.examApp.ui.home.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.examApp.R;
import com.example.examApp.data.DataManager;
import com.example.examApp.data.database.AppExecutors;
import com.example.examApp.data.database.DatabaseHero;
import com.example.examApp.data.network.JsonHero;
import com.example.examApp.ui.base.MvpView;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Chen.
 * @version 1 at 30/5/2019.
 *RecyclerView Adapter to set up all the heroes
 */
public class HeroesAdapter extends RecyclerView.Adapter<HeroesAdapter.HeroViewHolder> {

    private static final String TAG = "HeroesAdapter";
    private HeroesPresenter mHeroPresenter;
    private final listItemClickListener mOnClickListener;
    private static List<DatabaseHero> mHeroEntries;
    private List<JsonHero> mJsonHeroes;



    public interface listItemClickListener {
        void onListItemClick(int position, String title);
    }

    public HeroesAdapter(listItemClickListener mOnClickListener, final Context context) {
        mHeroPresenter = new HeroesPresenter(context);
        this.mOnClickListener = mOnClickListener;
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {

                mHeroEntries =DataManager.getInstance(context).getAllHeroes();


            }
        });

    }


    @NonNull
    @Override
    public HeroViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.single_view_item_recycler;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);
        return new HeroViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull HeroViewHolder heroViewHolder, int i) {
        mHeroPresenter.insertToDb(mJsonHeroes, i);
        mHeroPresenter.onBind(heroViewHolder, i, mHeroEntries);

    }

    public void setHeroEntries(List<DatabaseHero> taskEntries) {
        mHeroEntries = taskEntries;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return 11;
    }

    void putList(List<JsonHero> repos) {
        mJsonHeroes = repos;
        Log.i(TAG, mJsonHeroes.toString());
    }

    public class HeroViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, MvpView {

        @BindView(R.id.hero_name)
        TextView mHeroName;

        @BindView(R.id.hero_abilities)
        TextView mHeroAbilities;

        @BindView(R.id.hero_image)
        ImageView mHeroImage;

        @BindView(R.id.favorite_icon_image_view)
        ImageView mFavoriteView;

        private HeroViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            String title = mHeroName.getText().toString();
            mOnClickListener.onListItemClick(position, title);
        }
        @OnClick(R.id.hero_image)
        void onClickImage(View v) {
            mHeroPresenter.imageToFull
                    (mHeroEntries.get(getAdapterPosition())
                            .getImageUrl(), mHeroName.getText().toString());
        }


    }
}
