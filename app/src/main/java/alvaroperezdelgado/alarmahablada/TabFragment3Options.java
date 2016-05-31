package alvaroperezdelgado.alarmahablada;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import alvaroperezdelgado.alarmahablada.Model.User;
import alvaroperezdelgado.alarmahablada.Options.SetCityOptions;
import alvaroperezdelgado.alarmahablada.Options.SetMailOptions;
import alvaroperezdelgado.alarmahablada.Options.SetNameOptions;
import alvaroperezdelgado.alarmahablada.Options.SetTitleOptions;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Esta clase es la que controla el layout que el TabLayout mostrará
 * En este caso muestra el fragment layout de las opciones
 */
public class TabFragment3Options extends Fragment {
    @Bind(R.id.tvNameOptions)
    TextView name;
    @Bind(R.id.tvCityOptions)
    TextView city;
    @Bind(R.id.tvMailOptions)
    TextView mail;
    @Bind(R.id.tvTitleOptions)
    TextView title;
    @Bind(R.id.tvNameOptionsDisplay)
    TextView nameDisplay;
    @Bind(R.id.tvCityOptionsDisplay)
    TextView cityDisplay;
    @Bind(R.id.tvMailOptionsDisplay)
    TextView mailDisplay;
    @Bind(R.id.tvTitleOptionsDisplay)
    TextView titleDisplay;
    User user= User.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //desde aqui llamamos al layout que queremos que muestre
        View view= inflater.inflate(R.layout.tab_fragment_3options, container, false);
        ButterKnife.bind(this, view);
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SetNameOptions.class));
            }
        });
        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SetCityOptions.class));
            }
        });
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SetMailOptions.class));
            }
        });
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SetTitleOptions.class));
            }
        });
        nameDisplay.setText(user.getName().toString());
        cityDisplay.setText(user.getCity().toString());
        mailDisplay.setText(user.getMailUser().toString());

        return view;

    }
}