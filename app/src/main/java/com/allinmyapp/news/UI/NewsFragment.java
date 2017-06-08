package com.allinmyapp.news.UI;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allinmyapp.news.Model.NewsEntity;
import com.allinmyapp.news.Model.NewsModel;
import com.allinmyapp.news.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class NewsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    // TODO: Customize parameter argument names
    private static final String NEWS_TYPE = "news-type";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private List<NewsEntity> newsEntities = new ArrayList<>();
    private String mNewsType;
    private NewsRecyclerViewAdapter adapter;
    private ProgressDialog mProgressDialog;
    private SwipeRefreshLayout mSwipeContainer;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NewsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static NewsFragment newInstance(String topic) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString(NEWS_TYPE, topic);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNewsType = getArguments().getString(NEWS_TYPE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        mSwipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        mSwipeContainer.setOnRefreshListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new NewsRecyclerViewAdapter(newsEntities, mListener);
        recyclerView.setAdapter(adapter);

        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setMessage(getString(R.string.Loading));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(adapter.getItemCount()!=0){
            return;
        }
        fetchData();
    }

    private void fetchData() {
        mProgressDialog.show();
        NewsModel.getInstance(Locale.ENGLISH).getNews(getContext(), mNewsType, new NewsModel.Callback() {
            @Override
            public void response(List<NewsEntity> newsEntityList) {
                newsEntities.clear();
                newsEntities.addAll(newsEntityList);
                adapter.notifyDataSetChanged();
                mProgressDialog.dismiss();
                mSwipeContainer.setRefreshing(false);
                mSwipeContainer.setEnabled(true);

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onRefresh() {
        mSwipeContainer.setEnabled(false);
        fetchData();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(NewsEntity item);
    }
}
