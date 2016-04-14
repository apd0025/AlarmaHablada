package alvaroperezdelgado.alarmahablada;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TabFragment5Help extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //desde aqui llamamos al layout que queremos que muestre
        return inflater.inflate(R.layout.tab_fragment_5help, container, false);
    }
}