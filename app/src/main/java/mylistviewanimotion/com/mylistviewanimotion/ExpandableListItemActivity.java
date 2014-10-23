package mylistviewanimotion.com.mylistviewanimotion;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.expandablelistitem.ExpandableListItemAdapter;

public class ExpandableListItemActivity extends MyListActivity {

    private static final int INITIAL_DELAY_MILLIS = 500;
    private MyExpandableListItemAdapter mExpandableListItemAdapter;

    private boolean mLimited;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mExpandableListItemAdapter = new MyExpandableListItemAdapter(this);

        mExpandableListItemAdapter.setExpandCollapseListener(new ExpandableListItemAdapter.ExpandCollapseListener() {
            @Override
            public void onItemExpanded(int i) {

            }

            @Override
            public void onItemCollapsed(int i) {

            }
        });

        SwingBottomInAnimationAdapter swingBottomInAnimationAdapter = new SwingBottomInAnimationAdapter(mExpandableListItemAdapter);
        AlphaInAnimationAdapter alphaInAnimationAdapter = new AlphaInAnimationAdapter(swingBottomInAnimationAdapter);
        alphaInAnimationAdapter.setAbsListView(getListView());

        assert alphaInAnimationAdapter.getViewAnimator() != null;
        alphaInAnimationAdapter.getViewAnimator().setInitialDelayMillis(INITIAL_DELAY_MILLIS);

        getListView().setAdapter(alphaInAnimationAdapter);

        Toast.makeText(this, R.string.explainexpand, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_expandablelistitem, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_expandable_limit:
                mLimited = !mLimited;
                item.setChecked(mLimited);
                mExpandableListItemAdapter.setLimit(mLimited ? 2 : 0);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
