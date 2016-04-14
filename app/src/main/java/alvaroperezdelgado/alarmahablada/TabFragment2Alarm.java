package alvaroperezdelgado.alarmahablada;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Esta clase es la que controla el layout que el TabLayout mostrará
 * En este caso muestra el fragment layout de la alarma
 */
public class TabFragment2Alarm extends Fragment {

    private Button btNewAlarm;
    private Button btAccessMain;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //desde aqui llamamos al layout que queremos que muestre
        return inflater.inflate(R.layout.tab_fragment_2alarm, container, false);
        
    }
}