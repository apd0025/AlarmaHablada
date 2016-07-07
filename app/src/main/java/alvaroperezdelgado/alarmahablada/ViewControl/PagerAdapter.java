package alvaroperezdelgado.alarmahablada.ViewControl;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Clase que sirve para adaptar los fragments que va a haber en mainActivity y en el menú principal y
 * establecer el orden en el que aparecen y que clase es cada una.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TabFragment1Time tab1 = new TabFragment1Time();
                return tab1;
            case 1:
                TabFragment2Alarm tab2 = new TabFragment2Alarm();
                return tab2;
            case 2:
                TabFragment3Options tab3 = new TabFragment3Options();
                return tab3;
            case 3:
                TabFragment4Talk tab4 = new TabFragment4Talk();
                return tab4;
            case 4:
                TabFragment5Help tab5 = new TabFragment5Help();
                return tab5;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}