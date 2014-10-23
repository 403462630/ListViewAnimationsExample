package mylistviewanimotion.com.mylistviewanimotion;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.ListView;

import com.nhaarman.listviewanimations.appearance.simple.SwingLeftInAnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.SwingRightInAnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.OnDismissCallback;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.SwipeDismissAdapter;
import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;

public class GoogleCardsActivity extends BaseActivity implements OnDismissCallback {

    private static final int INITIAL_DELAY_MILLIS = 300;

    private GoogleCardsAdapter mGoogleCardsAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_googlecards);

        ListView listView = (ListView) findViewById(R.id.activity_googlecards_listview);

        mGoogleCardsAdapter = new GoogleCardsAdapter(this);
        SwingRightInAnimationAdapter swingBottomInAnimationAdapter = new SwingRightInAnimationAdapter(new SwipeDismissAdapter(mGoogleCardsAdapter, this));
        swingBottomInAnimationAdapter.setAbsListView(listView);

        assert swingBottomInAnimationAdapter.getViewAnimator() != null;
        swingBottomInAnimationAdapter.getViewAnimator().setInitialDelayMillis(INITIAL_DELAY_MILLIS);

        listView.setAdapter(swingBottomInAnimationAdapter);

        for (int i = 0; i < 100; i++) {
            mGoogleCardsAdapter.add(i);
        }
    }

    @Override
    public void onDismiss(@NonNull final ViewGroup listView, @NonNull final int[] reverseSortedPositions) {
        for (int position : reverseSortedPositions) {
            mGoogleCardsAdapter.remove(position);
        }
    }
}
