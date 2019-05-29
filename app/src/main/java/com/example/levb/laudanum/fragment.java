package com.example.levb.laudanum;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.app.ListFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class fragment extends ListFragment  implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int CM_DELETE_ID = 1;
    ListView list;
    DB db;
    SimpleCursorAdapter  scAdapter;
    Cursor cursor;
    String data_spiner [] =new String[]{"Contacts","Address","Action","About"};
    Spinner spn1;
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

}

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        db=new DB(getActivity());
        db.open();
        cursor = db.getDataAll();

        String from[]=new String[]{DB.COLUMN_TITLE,DB.COLUMN_IMG};
        int to[]=new int[]{R.id.tvTitle,R.id.ivProd};
        SimpleCursorAdapter adapter=new SimpleCursorAdapter(getActivity(),R.layout.item,cursor,from,to);
        setListAdapter(adapter);





    }
    

    public void onListItemClick(ListView l,View v,int position,long id){
        super.onListItemClick(l,v,position,id);
            Intent intent =new Intent();
    intent.setAction(Intent.ACTION_SEND);
    intent.putExtra(Intent.EXTRA_TEXT,"Подари жизнь");
    intent.setType("text/plain");
    startActivity(intent);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bndl) {
        return new MyCursorLoader(getActivity(), db);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        scAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
    }

    static class MyCursorLoader extends CursorLoader {

        DB db;

        public MyCursorLoader(Context context, DB db) {
            super(context);
            this.db = db;
        }

        @Override
        public Cursor loadInBackground() {
            Cursor cursor = db.getDataAll();

            return cursor;
        }
    }


}
