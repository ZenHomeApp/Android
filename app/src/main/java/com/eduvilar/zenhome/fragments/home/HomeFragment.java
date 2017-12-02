package com.eduvilar.zenhome.fragments.home;

import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.eduvilar.zenhome.R;
import com.eduvilar.zenhome.base.BaseFragment;
import com.eduvilar.zenhome.callback.DialogCallback;
import com.eduvilar.zenhome.model.House;
import com.eduvilar.zenhome.model.User;
import com.eduvilar.zenhome.utils.DialogUtils;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.tapadoo.alerter.Alerter;

/**
 * Created by eduardovilar10 on 25/11/2017.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    private HomeContract.Presenter presenter;

    private LinearLayout loadingView;
    private LinearLayout contentView;
    private RecyclerView list;

    @Override
    public int layout() {
        return R.layout.fragment_home;
    }

    @Override
    public void view(View view) {
        loadingView = (LinearLayout) view.findViewById(R.id.loading_view);
        contentView = (LinearLayout) view.findViewById(R.id.content_view);
        list = (RecyclerView) view.findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(activity()));
        list.setAdapter(new HomesAdapter() {
            @Override
            public void onClick(House house) {
                Alerter.create(activity())
                        .setTitle("GENIAL")
                        .setText("voy a la casa " + house.getName())
                        .show();
                // TODO ir a otro fragment (fragment de vivienda, con listado de estancias)
            }
        });
    }

    @Override
    public void onBackPressed() {
        activity().finish();
    }

    @Override
    public void init() {
        if (presenter == null) {
            presenter = new HomePresenter(this);
        }
        presenter.init();
    }

    @Override
    public void onViewLoading() {
        loadingView.setVisibility(View.VISIBLE);
        contentView.setVisibility(View.GONE);
    }

    @Override
    public void onViewReady() {
        loadingView.setVisibility(View.GONE);
        contentView.setVisibility(View.VISIBLE);
    }

    @Override
    public void refreshList(User user) {
        ((HomesAdapter) list.getAdapter()).refresh(user.getHouses());
    }

    @Override
    protected IDrawerItem[] getActionItems() {
        return new IDrawerItem[] {
                new PrimaryDrawerItem().withIdentifier(1).withName("Añadir vivienda").withIcon(CommunityMaterial.Icon.cmd_home_assistant).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        DialogUtils.dialog(activity(), "Crear nueva vivienda", R.layout.dialog_flat, new DialogCallback() {

                            @Override
                            public void accept(DialogInterface dialog, View view) {
                                EditText name = (EditText) view.findViewById(R.id.home_name);
                                if (name.getText().toString().length() == 0) {
                                    // TODO alertar de que no han puesto un nombre a la casa
                                } else {
                                    presenter.createFlat(new House(name.getText().toString()));
                                }

                            }

                            @Override
                            public void cancel(DialogInterface dialog, View view) {

                            }

                            @Override
                            public View view(View view) {
                                return null;
                            }
                        });
                        return false;
                    }
                })
        };
    }
}
