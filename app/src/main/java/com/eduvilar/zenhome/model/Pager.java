package com.eduvilar.zenhome.model;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.view.menu.MenuBuilder;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import com.bonopark.bicimad.HomeActivity;
import com.bonopark.bicimad.R;
import com.bonopark.bicimad.adapters.FragmentAdapter;
import com.bonopark.bicimad.asynctask.ShowRoutesTask;
import com.bonopark.bicimad.fragments.ContactDataFragment;
import com.bonopark.bicimad.fragments.DataTrafficFragment;
import com.bonopark.bicimad.fragments.FavouritesFragment;
import com.bonopark.bicimad.fragments.FleetManagementFragment;
import com.bonopark.bicimad.fragments.IssueFragment;
import com.bonopark.bicimad.fragments.LicensesFragment;
import com.bonopark.bicimad.fragments.ListDocksFragment;
import com.bonopark.bicimad.fragments.ListStationsFragment;
import com.bonopark.bicimad.fragments.LoadingFragment;
import com.bonopark.bicimad.fragments.MainPINFragment;
import com.bonopark.bicimad.fragments.MapFragment;
import com.bonopark.bicimad.fragments.NotificationsFragment;
import com.bonopark.bicimad.fragments.PINFragment;
import com.bonopark.bicimad.fragments.PasswordFragment;
import com.bonopark.bicimad.fragments.PersonalDataFragment;
import com.bonopark.bicimad.fragments.SettingsFragment;
import com.bonopark.bicimad.fragments.TicketFragment;
import com.bonopark.bicimad.fragments.TripsFragment;
import com.kennyc.bottomsheet.BottomSheet;
import com.kennyc.bottomsheet.BottomSheetListener;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;

import java.lang.reflect.Field;

/**
 * Created by Efra on 14/04/2016.
 */
public class Pager extends ViewPager implements ViewPager.PageTransformer {

    public HomeActivity ha;
    private FixedSpeedScroller mScroller = null;

    public Pager(Context context) {
        super(context);
        init(context);
        setPageTransformer(false, this);
    }

    public Pager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        setPageTransformer(false, this);
    }

    public void setParent(HomeActivity ha) {
        this.ha = ha;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        // Never allow swiping to switch between pages
        //if (ha != null && ha.viewPager.getCurrentItem() != ha.getFragmentPosition(new ListDocksFragment())) return false;
        // return super.onInterceptTouchEvent(event);
        return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Never allow swiping to switch between pages
        // return super.onTouchEvent(event);
        return false;
    }

    public void setCurrentItem(Fragment fragment) {
        setCurrentItem(fragment, true);
    }

    public void setCurrentItem(String fragment_name) {
        Fragment fragment = null;
        switch (fragment_name) {
            case "LoadingFragment":
                fragment = new LoadingFragment();
                break;
            case "MainPINFragment":
                fragment = new MainPINFragment();
                break;
            case "DataTrafficFragment":
                fragment = new DataTrafficFragment();
                break;
            case "NotificationsFragment":
                fragment = new NotificationsFragment();
                break;
            case "PersonalDataFragment":
                fragment = new PersonalDataFragment();
                break;
            case "ContactDataFragment":
                fragment = new ContactDataFragment();
                break;
            case "FleetManagementFragment":
                fragment = new FleetManagementFragment();
                break;
            case "PINFragment":
                fragment = new PINFragment();
                break;
            case "PasswordFragment":
                fragment = new PasswordFragment();
                break;
            case "MapFragment":
                fragment = new MapFragment();
                break;
            case "LicensesFragment":
                fragment = new LicensesFragment();
                break;
            case "SettingsFragment":
                fragment = new SettingsFragment();
                break;
            case "TicketFragment":
                fragment = new TicketFragment();
                break;
            case "TripsFragment":
                fragment = new TripsFragment();
                break;
            case "FavouritesFragment":
                fragment = new FavouritesFragment();
                break;
            case "ListStationsFragment":
                fragment = new ListStationsFragment();
                break;
            case "ListDocksFragment":
                fragment = new ListDocksFragment();
                break;
            case "IssueFragment":
                fragment = new IssueFragment();
                break;
        }
        setCurrentItem(fragment, true);
    }


    public void setCurrentItem(final Fragment fragment, boolean smoothScroll) {
        int item = ha.getFragmentPosition(fragment);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP &&
                getCurrentItem() == ha.getFragmentPosition(new ListDocksFragment()) && getCurrentItem() != item) {
            Window window = ha.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ha.getResources().getColor(R.color.colorPrimaryDark));
            ha.toolbar.setBackgroundColor(ha.getResources().getColor(R.color.colorPrimary));
        }

        if (!ha.viewPager.getCurrentFragment().getClass().getSimpleName().equalsIgnoreCase("MainPINFragment") && !ha.viewPager.getCurrentFragment().getClass().getSimpleName().equalsIgnoreCase("LoadingFragment"))
            ha.auth.lastFragment(ha.viewPager.getCurrentFragment().getClass().getSimpleName());

        switch (fragment.getClass().getSimpleName()) {
            case "LoadingFragment":
                ha.indicator_nav.setVisibility(GONE);

                ha.fav_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.map_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.ticket_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.trips_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));

                ha.indicator_menu.setVisibility(GONE);
                ha.indicator_menu.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

                break;
            case "PersonalDataFragment":
                ha.indicator_nav.setVisibility(VISIBLE);

                ha.fav_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.map_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.ticket_i.setTextColor(ha.getResources().getColor(R.color.nav_item_selected));
                ha.trips_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));

                ha.indicator_menu.setVisibility(GONE);
                ha.indicator_menu.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

                break;
            case "ContactDataFragment":
                ha.indicator_nav.setVisibility(VISIBLE);

                ha.fav_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.map_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.ticket_i.setTextColor(ha.getResources().getColor(R.color.nav_item_selected));
                ha.trips_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));

                ha.indicator_menu.setVisibility(GONE);
                ha.indicator_menu.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

                break;
            case "MainPINFragment":
                ha.indicator_nav.setVisibility(GONE);

                ha.fav_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.map_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.ticket_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.trips_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));

                ha.indicator_menu.setVisibility(GONE);
                ha.indicator_menu.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

                break;
            case "DataTrafficFragment":
                ha.indicator_nav.setVisibility(VISIBLE);

                ha.fav_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.map_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.ticket_i.setTextColor(ha.getResources().getColor(R.color.nav_item_selected));
                ha.trips_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));

                ha.indicator_menu.setVisibility(GONE);
                ha.indicator_menu.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

                break;
            case "NotificationsFragment":
                ha.indicator_nav.setVisibility(VISIBLE);

                ha.fav_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.map_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.ticket_i.setTextColor(ha.getResources().getColor(R.color.nav_item_selected));
                ha.trips_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));

                ha.indicator_menu.setVisibility(GONE);
                ha.indicator_menu.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

                break;
            case "PINFragment":
                ha.indicator_nav.setVisibility(VISIBLE);

                ha.fav_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.map_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.ticket_i.setTextColor(ha.getResources().getColor(R.color.nav_item_selected));
                ha.trips_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));

                ha.indicator_menu.setVisibility(GONE);
                ha.indicator_menu.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

                break;
            case "PasswordFragment":
                ha.indicator_nav.setVisibility(VISIBLE);

                ha.fav_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.map_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.ticket_i.setTextColor(ha.getResources().getColor(R.color.nav_item_selected));
                ha.trips_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));

                ha.indicator_menu.setVisibility(GONE);
                ha.indicator_menu.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

                break;
            case "FleetManagementFragment":
                ha.indicator_nav.setVisibility(VISIBLE);

                ha.fav_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.map_i.setTextColor(ha.getResources().getColor(R.color.nav_item_selected));
                ha.ticket_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.trips_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));

                ha.indicator_menu.setVisibility(GONE);
                ha.indicator_menu.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

                break;
            case "MapFragment":
                ha.indicator_nav.setVisibility(GONE);

                ha.fav_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.map_i.setTextColor(ha.getResources().getColor(R.color.nav_item_selected));
                ha.ticket_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.trips_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));

                ha.indicator_menu.setVisibility(VISIBLE);
                ha.indicator_menu.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        BottomSheet.Builder bsb = new BottomSheet.Builder(ha)
                                .setTitle("Mapa")
                                .setListener(new BottomSheetListener() {

                                    @Override
                                    public void onSheetShown(@NonNull BottomSheet bottomSheet) {

                                    }

                                    @Override
                                    public void onSheetItemSelected(@NonNull BottomSheet bottomSheet, MenuItem menuItem) {
                                        switch (menuItem.getItemId()) {
                                            case R.id.action_issue:
                                                if (ha.user.getActiveTicket() == null) {
                                                    if (ha.viewPager.getCurrentFragment().getClass().getSimpleName().equalsIgnoreCase(TicketFragment.class.getSimpleName()))
                                                        Snackbar.make(ha.findViewById(android.R.id.content), "Necesitas recoger la tarjeta para registrar incidencias", Snackbar.LENGTH_LONG)
                                                                .setAction("Activar", null).show();
                                                    else Snackbar.make(ha.findViewById(android.R.id.content), "Necesitas recoger la tarjeta para registrar incidencias", Snackbar.LENGTH_LONG)
                                                            .setAction("Recogida", new OnClickListener() {
                                                                @Override
                                                                public void onClick(View view) {
                                                                    ha.viewPager.setCurrentItem(TicketFragment.class.getSimpleName());
                                                                }
                                                            }).show();
                                                } else ha.viewPager.setCurrentItem(new IssueFragment());
                                                break;

                                            case R.id.action_show_list:
                                                ha.viewPager.setCurrentItem(new ListStationsFragment());
                                                break;

                                            case R.id.action_show_fleet_management:
                                                ha.viewPager.setCurrentItem(new FleetManagementFragment());
                                                break;

                                            case R.id.action_show_traffic:
                                                ha.auth.setTraffictEnabled(!ha.auth.isTrafficEnabled());
                                                ha.map = null;
                                                ha.updateUI("MapFragment");
                                                break;

                                            case R.id.action_show_3d:
                                                ha.auth.set3DEnabled(!ha.auth.is3DEnabled());
                                                ha.map = null;
                                                ha.updateUI("MapFragment");
                                                break;

                                            case R.id.action_show_route:

                                                new ShowRoutesTask(ha).execute();
                                                break;

                                            case R.id.action_hide_route:
                                                ha.kmlLayer = null;
                                                ha.map.clear();
                                                ha.map = null;
                                                ha.auth.set_pending_kml(null);
                                                ha.updateUI("MapFragment");
                                                break;
                                        }
                                    }

                                    @Override
                                    public void onSheetDismissed(@NonNull BottomSheet bottomSheet, @DismissEvent int i) {

                                    }

                                });


                        /* items */

                        MenuItem issue_ = getMenuItem(R.id.action_issue);
                        issue_.setTitle(CommunityMaterial.Icon.cmd_alert.getFormattedName() + "\u00A0\u00A0\u00A0\u00A0Registrar una incidencia");
                        bsb.addMenuItem(issue_);

                        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
                            MenuItem traffic_ = getMenuItem(R.id.action_show_traffic);
                            String msg_traffic = CommunityMaterial.Icon.cmd_car.getFormattedName() + "\u00A0\u00A0\u00A0\u00A0Ver tráfico";
                            if (ha.auth.isTrafficEnabled()) msg_traffic = CommunityMaterial.Icon.cmd_car.getFormattedName() + "\u00A0\u00A0\u00A0\u00A0Quitar tráfico";
                            traffic_.setTitle(msg_traffic);
                            bsb.addMenuItem(traffic_);

                            MenuItem _3d = getMenuItem(R.id.action_show_3d);
                            String msg_3d = CommunityMaterial.Icon.cmd_home_modern.getFormattedName() + "\u00A0\u00A0\u00A0\u00A0Ver edificios";
                            if (ha.auth.is3DEnabled()) msg_3d = CommunityMaterial.Icon.cmd_home_modern.getFormattedName() + "\u00A0\u00A0\u00A0\u00A0Quitar edificios";
                            _3d.setTitle(msg_3d);
                            bsb.addMenuItem(_3d);
                        }

                        if (ha.isVIP()) {

                            String msg = "";
                            boolean show_del = false;
                            if (ha.auth.get_pending_kml() == null) msg = "Ver ruta";
                            else  {
                                show_del = true;
                                msg = "Cambiar ruta";
                            }

                            MenuItem route_ = getMenuItem(R.id.action_show_route);
                            route_.setTitle(CommunityMaterial.Icon.cmd_vector_polyline.getFormattedName() + "\u00A0\u00A0\u00A0\u00A0" + msg);
                            bsb.addMenuItem(route_);

                            if (show_del) {
                                MenuItem del_ = getMenuItem(R.id.action_hide_route);
                                del_.setTitle(CommunityMaterial.Icon.cmd_delete.getFormattedName() + "\u00A0\u00A0\u00A0\u00A0Eliminar ruta");
                                bsb.addMenuItem(del_);
                            }

                            MenuItem list_ = getMenuItem(R.id.action_show_list);
                            list_.setTitle(CommunityMaterial.Icon.cmd_format_list_numbers.getFormattedName() + "\u00A0\u00A0\u00A0\u00A0Listado estaciones");
                            bsb.addMenuItem(list_);

                            MenuItem fleet = getMenuItem(R.id.action_show_fleet_management);
                            fleet.setTitle(CommunityMaterial.Icon.cmd_car_connected.getFormattedName() + "\u00A0\u00A0\u00A0\u00A0Control de flota");
                            bsb.addMenuItem(fleet);
                        }

                        try {
                            bsb.show();
                        } catch (Exception e) {
                            Snackbar.make(ha.findViewById(android.R.id.content), "Error abriendo menú", Snackbar.LENGTH_LONG)
                                    .setAction("a", null).show();
                        }

                    }
                });

                break;
            case "LicensesFragment":
                ha.indicator_nav.setVisibility(VISIBLE);

                ha.fav_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.map_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.ticket_i.setTextColor(ha.getResources().getColor(R.color.nav_item_selected));
                ha.trips_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));

                ha.indicator_menu.setVisibility(GONE);
                ha.indicator_menu.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

                break;
            case "SettingsFragment":
                ha.indicator_nav.setVisibility(VISIBLE);

                ha.fav_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.map_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.ticket_i.setTextColor(ha.getResources().getColor(R.color.nav_item_selected));
                ha.trips_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));

                ha.indicator_menu.setVisibility(GONE);
                ha.indicator_menu.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

                break;
            case "TicketFragment":
                ha.indicator_nav.setVisibility(GONE);

                ha.fav_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.map_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.ticket_i.setTextColor(ha.getResources().getColor(R.color.nav_item_selected));
                ha.trips_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));

                ha.indicator_menu.setVisibility(VISIBLE);
                ha.indicator_menu.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        BottomSheet.Builder bsb = new BottomSheet.Builder(ha)
                                .setTitle("Abono")
                                .setListener(new BottomSheetListener() {

                                    @Override
                                    public void onSheetShown(@NonNull BottomSheet bottomSheet) {

                                    }

                                    @Override
                                    public void onSheetItemSelected(@NonNull BottomSheet bottomSheet, MenuItem menuItem) {
                                        switch (menuItem.getItemId()) {
                                            case R.id.action_issue:
                                                if (ha.user.getActiveTicket() == null) {
                                                    if (ha.viewPager.getCurrentFragment().getClass().getSimpleName().equalsIgnoreCase(TicketFragment.class.getSimpleName()))
                                                    Snackbar.make(ha.findViewById(android.R.id.content), "Necesitas recoger la tarjeta para registrar incidencias", Snackbar.LENGTH_LONG)
                                                            .setAction("Activar", null).show();
                                                    else Snackbar.make(ha.findViewById(android.R.id.content), "Necesitas recoger la tarjeta para registrar incidencias", Snackbar.LENGTH_LONG)
                                                            .setAction("Recogida", new OnClickListener() {
                                                                @Override
                                                                public void onClick(View view) {
                                                                    ha.viewPager.setCurrentItem(TicketFragment.class.getSimpleName());
                                                                }
                                                            }).show();
                                                } else ha.viewPager.setCurrentItem(new IssueFragment());
                                                break;

                                            case R.id.action_config:
                                                ha.viewPager.setCurrentItem(new SettingsFragment());
                                                break;

                                            case R.id.action_help:
                                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.bicimad.com/index.php?s=preguntas"));
                                                ha.startActivity(browserIntent);
                                                break;

                                        }
                                    }

                                    @Override
                                    public void onSheetDismissed(@NonNull BottomSheet bottomSheet, @DismissEvent int i) {

                                    }
                                });


                        /* items */

                        MenuItem issue_ = getMenuItem(R.id.action_issue);
                        issue_.setTitle(CommunityMaterial.Icon.cmd_alert.getFormattedName() + "\u00A0\u00A0\u00A0\u00A0Registrar una incidencia");
                        bsb.addMenuItem(issue_);

                        MenuItem config_ = getMenuItem(R.id.action_config);
                        config_.setTitle(CommunityMaterial.Icon.cmd_settings.getFormattedName() + "\u00A0\u00A0\u00A0\u00A0Opciones");
                        bsb.addMenuItem(config_);

                        MenuItem help_ = getMenuItem(R.id.action_help);
                        help_.setTitle(CommunityMaterial.Icon.cmd_help_circle.getFormattedName() + "\u00A0\u00A0\u00A0\u00A0FAQ");
                        bsb.addMenuItem(help_);

                        try {
                            bsb.show();
                        } catch (Exception e) {
                            Snackbar.make(findViewById(android.R.id.content), "Error abriendo menú", Snackbar.LENGTH_LONG)
                                    .setAction("a", null).show();
                        }
                    }
                });

                break;
            case "TripsFragment":
                ha.indicator_nav.setVisibility(GONE);

                ha.fav_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.map_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.ticket_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.trips_i.setTextColor(ha.getResources().getColor(R.color.nav_item_selected));

                ha.indicator_menu.setVisibility(GONE);
                ha.indicator_menu.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

                break;
            case "FavouritesFragment":
                ha.indicator_nav.setVisibility(GONE);

                ha.fav_i.setTextColor(ha.getResources().getColor(R.color.nav_item_selected));
                ha.map_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.ticket_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.trips_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));

                ha.indicator_menu.setVisibility(GONE);
                ha.indicator_menu.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

                break;
            case "ListStationsFragment":
                ha.indicator_nav.setVisibility(VISIBLE);

                ha.fav_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.map_i.setTextColor(ha.getResources().getColor(R.color.nav_item_selected));
                ha.ticket_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.trips_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));

                ha.indicator_menu.setVisibility(GONE);
                ha.indicator_menu.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

                break;
            case "ListDocksFragment":
                ha.indicator_nav.setVisibility(VISIBLE);

                ha.fav_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.map_i.setTextColor(ha.getResources().getColor(R.color.nav_item_selected));
                ha.ticket_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.trips_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));

                ha.indicator_menu.setVisibility(GONE);
                ha.indicator_menu.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

                break;
            case "IssueFragment":
                ha.indicator_nav.setVisibility(VISIBLE);

                ha.fav_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.map_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.ticket_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));
                ha.trips_i.setTextColor(ha.getResources().getColor(R.color.nav_item_default));

                ha.indicator_menu.setVisibility(GONE);
                ha.indicator_menu.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

                break;
        }

        super.setCurrentItem(item, smoothScroll);

        ha.updateUI(fragment.getClass().getSimpleName());
    }

    @Override
    public void transformPage(View view, float position) {
        if ((int) view.getTag() == getCurrentItem()) {
            if (position <= -1.0F || position >= 1.0F) {
                view.setTranslationX(view.getWidth() * position);
                view.setAlpha(0.0F);
            } else if (position == 0.0F) {
                view.setTranslationX(view.getWidth() * position);
                view.setAlpha(1.0F);
            } else {
                // position is between -1.0F & 0.0F OR 0.0F & 1.0F
                view.setTranslationX(view.getWidth() * -position);
                view.setAlpha(1.0F - Math.abs(position));
            }
        } else {
            view.setAlpha(0.0F);
            if (position <= -1.0F || position >= 1.0F) {
                view.setTranslationX(view.getWidth() * position);
            }
        }
    }

    /*
     * Set the factor by which the duration will change
     */
    public void setScrollDuration(int duration) {
        mScroller.setScrollDuration(duration);
    }

    public Fragment getCurrentFragment() {
        int current = getCurrentItem();
        FragmentAdapter fragmentAdapter = (FragmentAdapter) getAdapter();
        return fragmentAdapter.fragments.get(current);
    }


    /*
     * Override the Scroller instance with our own class so we can change the
     * duration
     */
    private void init(Context context) {
        try {
            Class<?> viewpager = ViewPager.class;
            Field scroller = viewpager.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            mScroller = new FixedSpeedScroller(context, new DecelerateInterpolator());
            setScrollDuration(500);
            scroller.set(this, mScroller);
        } catch (Exception ignored) {
        }
    }

    private class FixedSpeedScroller extends Scroller {

        private int mDuration = 200;

        public FixedSpeedScroller(Context context) {
            super(context);
        }

        public FixedSpeedScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        public FixedSpeedScroller(Context context, Interpolator interpolator, boolean flywheel) {
            super(context, interpolator, flywheel);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        public void setScrollDuration(int duration) {
            mDuration = duration;
        }
    }

    private MenuItem getMenuItem(int id) {
        MenuItem menuItem = null;
        Menu menu = new MenuBuilder(ha);
        new MenuInflater(ha).inflate(R.menu.menu, menu);

        for (int i = 0; i < menu.size(); i++) {
            menuItem = menu.getItem(i);
            if (menuItem.getItemId() == id) break;
        }

        return menuItem;
    }
}