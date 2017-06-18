package com.allinmyapp.news;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailNewsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailNewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailNewsFragment extends Fragment {

    private static final String PARAM_URL = "param1";
    public static final String FragmentTAG = "ProjectDetailTAG";
    private static final String TAG = "ProjectDetailsFragment";

    private String mURL;
    private OnFragmentInteractionListener mListener;
    private WebView mWebView;
    private ProgressDialog mProgressDialog;

    public DetailNewsFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param url link for the webView.
     * @return A new instance of fragment ProjectDetailFragment.
     */
    public static DetailNewsFragment newInstance(String url) {
        DetailNewsFragment fragment = new DetailNewsFragment();
        Bundle args = new Bundle();
        args.putString(PARAM_URL, url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "OnCreate");
        if (getArguments() != null) {
            mURL = getArguments().getString(PARAM_URL);
        }
        mListener.hideActionBar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "OnCreateView");
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_news_details, container, false);
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed();
            }
        });
        mWebView = (WebView) v.findViewById(R.id.webView);

        // Disable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(false);

        // Force links and redirects to open in the WebView instead of in a browser
        mProgressDialog = new ProgressDialog(this.getContext());
        mProgressDialog.setMessage(getString(R.string.loading_text));
        mProgressDialog.setCanceledOnTouchOutside(false);
        mWebView.setWebViewClient(new FragWebViewClient(mProgressDialog));
        if (isOnline()) {
            mWebView.loadUrl(mURL);
        }else{
            mProgressDialog.dismiss();
            new AlertDialog.Builder(this.getActivity())
                    .setTitle(getResources().getString(R.string.app_name))
                    .setMessage(
                            getResources().getString(R.string.networkerror))
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            mListener.onBackPress();
                        }
                    }).show();
        }
        return v;
    }

    public void onButtonPressed() {
        if (mListener != null) {
            mListener.onBackPress();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG, "OnAttach");
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "OnDetach");
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onBackPress();
        void hideActionBar();
    }

    private boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }


    private class FragWebViewClient extends WebViewClient {
        private ProgressDialog progressDialog;

        FragWebViewClient(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progressDialog.show();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressDialog.dismiss();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "OnDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "OnDestroyView");
    }
}
